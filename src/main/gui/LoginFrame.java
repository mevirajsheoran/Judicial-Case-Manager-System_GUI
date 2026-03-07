package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private final JTextField txtUsername = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField(15);

    public LoginFrame() {
        setTitle("Judicial Case Manager - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        formPanel.add(new JLabel("Username:"));
        formPanel.add(txtUsername);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(this::handleLogin);

        formPanel.add(new JLabel()); // empty for spacing
        formPanel.add(btnLogin);

        add(formPanel, BorderLayout.CENTER);
    }

    private void handleLogin(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Hardcoded check (can be replaced with DB auth logic)
        if (username.equals("admin") && password.equals("admin123")) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new AdminDashboard().setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); // Close login window
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
