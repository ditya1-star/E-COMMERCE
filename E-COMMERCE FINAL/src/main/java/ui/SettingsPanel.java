package ui;

import utils.ThemeManager;
import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JDialog {
    public SettingsPanel() {
        setTitle("Settings"); setModal(true); setSize(320,200); setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); GridBagConstraints gbc=new GridBagConstraints(); gbc.insets=new Insets(8,8,8,8);
        JButton toggle = new JButton("Toggle Theme"); add(toggle, gbc);
        toggle.addActionListener(e -> { ThemeManager.toggle(); SwingUtilities.updateComponentTreeUI(this); });
    }
}
