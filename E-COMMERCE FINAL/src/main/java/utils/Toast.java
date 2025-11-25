package utils;

import javax.swing.*;
import java.awt.*;

public class Toast {
    public static void showToast(Window parent, String message) {
        JWindow w = new JWindow();
        w.setLayout(new BorderLayout());
        JLabel lbl = new JLabel(message);
        lbl.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));
        w.add(lbl, BorderLayout.CENTER);
        w.pack();
        Point p = parent.getLocationOnScreen();
        w.setLocation(p.x + parent.getWidth()/2 - w.getWidth()/2, p.y + parent.getHeight() - 120);
        w.setVisible(true);
        new Timer(1600, e -> w.dispose()).start();
    }
}
