package Clock;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockWindow extends JFrame {
    private ClockThreaded clock;

    public ClockWindow(String defaultTimeZone) {
        setTitle("Clock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        clock = new ClockThreaded(defaultTimeZone);
        add(clock);

        JButton addButton = new JButton("Click Me");
        add(addButton, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClockWindow newWindow = new ClockWindow(clock.getTimeZone());
                newWindow.setLocationRelativeTo(null);
                newWindow.setVisible(true);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClockWindow clockWindow = new ClockWindow("7");
                clockWindow.setVisible(true);
            }
        });
    }
}