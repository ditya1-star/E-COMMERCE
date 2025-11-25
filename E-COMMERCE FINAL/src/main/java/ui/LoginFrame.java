package ui;

import ecommerce.App;
import dao.UserDAO;
import utils.PasswordHasher;
import utils.Toast;
import utils.Validator;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField tfUser = new JTextField(16);
    private final JPasswordField pfPass = new JPasswordField(16);

    public LoginFrame() {
        super("Login");
        setSize(420,260); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel c = new JPanel(new GridBagLayout()); add(c);
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(6,6,6,6);
        gbc.gridx=0; gbc.gridy=0; c.add(new JLabel("Username:"), gbc); gbc.gridx=1; c.add(tfUser, gbc);
        gbc.gridx=0; gbc.gridy=1; c.add(new JLabel("Password:"), gbc); gbc.gridx=1; c.add(pfPass, gbc);

        JPanel south = new JPanel();
        JButton login = new JButton("Login"); JButton reg = new JButton("Register");
        south.add(login); south.add(reg); add(south, BorderLayout.SOUTH);

        login.setToolTipText("Login (Alt+L)");
        login.addActionListener(e -> doLogin());
        reg.addActionListener(e -> { new RegisterFrame().setVisible(true); dispose(); });

        // keyboard shortcut
        login.setMnemonic('L');
    }

    private void doLogin() {
        String u = tfUser.getText().trim(); String p = new String(pfPass.getPassword()).trim();
        if (!Validator.isNonEmpty(u) || !Validator.isNonEmpty(p)) { JOptionPane.showMessageDialog(this, "Fill fields"); return; }
        try {
            UserDAO dao = new UserDAO();
            String hash = PasswordHasher.sha256(p);
            var user = dao.validateUser(u, hash);
            if (user != null) {
                App.setCurrentUser(user);
                Toast.showToast(this, "Welcome " + user.getUsername());
                new MainFrame().setVisible(true);
                dispose();
            } else JOptionPane.showMessageDialog(this, "Invalid credentials");
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Login error: "+ex.getMessage()); }
    }
}
