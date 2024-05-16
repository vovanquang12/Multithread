package Clock;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

 public class ClockThreaded extends JPanel {
  
	private static final long serialVersionUID = 1L;
	private JLabel timeLabel;
    private JTextField timeZoneField;

    public ClockThreaded(String defaultTimeZone) {
        setLayout(new FlowLayout());

        timeLabel = new JLabel();
        add(timeLabel);

        timeZoneField = new JTextField(defaultTimeZone, 4);
        add(timeZoneField);

        timeZoneField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTime();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTime();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTime();
            }
        });

        Thread clockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clockThread.start();
    }

    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String timeZone = timeZoneField.getText();
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT+" + timeZone));
        timeLabel.setText(dateFormat.format(new Date()));
    }
    
    public String getTimeZone() {
        return timeZoneField.getText();
    }
}