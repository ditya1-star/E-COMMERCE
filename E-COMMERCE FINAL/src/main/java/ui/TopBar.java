package ui;

import utils.ThemeManager;
import utils.Toast;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar(JFrame parent) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
        JLabel lbl = new JLabel("MyShop");
        add(lbl, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnTheme = new JButton("Theme");
        JButton btnSettings = new JButton("Settings");
        right.add(btnSettings); right.add(btnTheme);
        add(right, BorderLayout.EAST);

        btnTheme.setToolTipText("Toggle Dark/Light");
        btnTheme.addActionListener(e -> {
            ThemeManager.toggle();
            SwingUtilities.updateComponentTreeUI(parent);
            Toast.showToast(parent, "Theme toggled");
        });

        btnSettings.addActionListener(e -> {
            new SettingsPanel().setVisible(true);
        });
    }
}
