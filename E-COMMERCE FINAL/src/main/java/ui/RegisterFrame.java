package ui;

import dao.UserDAO;
import utils.PasswordHasher;
import utils.Validator;
import utils.Toast;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private final JTextField tfUser = new JTextField(16);
    private final JTextField tfEmail = new JTextField(16);
    private final JPasswordField pfPass = new JPasswordField(16);
    private final JPasswordField pfConfirm = new JPasswordField(16);

    public RegisterFrame() {
        super("Register");
        setSize(460, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(tfUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(tfEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(pfPass, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Confirm:"), gbc);
        gbc.gridx = 1;
        add(pfConfirm, gbc);

        JButton create = new JButton("Create Account");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(create, gbc);

        create.addActionListener(e -> doCreate());
    }

    private void doCreate() {
        String username = tfUser.getText().trim();
        String email = tfEmail.getText().trim();
        String pass = new String(pfPass.getPassword()).trim();
        String confirm = new String(pfConfirm.getPassword()).trim();

        // Validations
        if (!Validator.isNonEmpty(username) ||
                !Validator.isNonEmpty(email) ||
                !Validator.isNonEmpty(pass)) {
            JOptionPane.showMessageDialog(this, "Fill all fields");
            return;
        }

        if (!Validator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid Email");
            return;
        }

        if (!Validator.isStrongPassword(pass)) {
            JOptionPane.showMessageDialog(this, "Weak password");
            return;
        }

        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        try {
            UserDAO dao = new UserDAO();
            boolean ok = dao.createCustomer(username, email, PasswordHasher.sha256(pass));

            if (ok) {
                Toast.showToast(this, "Account Created Successfully!");
                dispose(); // CLOSE RegisterFrame
                new LoginFrame().setVisible(true); // OPEN LoginFrame only once
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed (duplicate?)");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
