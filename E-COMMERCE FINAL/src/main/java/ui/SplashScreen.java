package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    public SplashScreen() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));
        JLabel title = new JLabel("MyShop"); title.setFont(title.getFont().deriveFont(28f));
        p.add(title, BorderLayout.CENTER);
        JProgressBar pb = new JProgressBar(); pb.setIndeterminate(true);
        p.add(pb, BorderLayout.SOUTH);
        getContentPane().add(p);
        pack();
        setLocationRelativeTo(null);
    }

    public void showSplashAndStart() {
        setVisible(true);

        Timer t = new Timer(1200, e -> {
            setVisible(false);
            dispose();
            new LoginFrame().setVisible(true);
            ((Timer)e.getSource()).stop(); // <--- STOP THE TIMER
        });

        t.setRepeats(false);   // <---- VERY IMPORTANT
        t.start();
    }

}
