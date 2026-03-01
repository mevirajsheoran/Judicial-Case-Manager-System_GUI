package main;

import main.db.DatabaseConnection;
import main.gui.LoginFrame;

import javax.swing.*;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try {
            // Step 1: Test DB Connection
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("✅ Connected to database successfully from App.java");
                DatabaseConnection.closeConnection();

                // Step 2: Launch LoginFrame
                SwingUtilities.invokeLater(() -> {
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                });
            } else {
                System.out.println("❌ Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Database connection failed");
            JOptionPane.showMessageDialog(null, "Unable to connect to the database. Please check your configuration.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
