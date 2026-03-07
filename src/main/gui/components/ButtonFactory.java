package main.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {

    // Base method with ActionListener
    public static JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        stylePrimary(button);
        button.addActionListener(action);
        return button;
    }

    // Overloaded method without ActionListener
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        stylePrimary(button);
        return button;
    }

    // Generic styling method for primary button
    private static void stylePrimary(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 120, 180)); // Blue-ish
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    public static JButton createDangerButton(String text, ActionListener action) {
        JButton button = createButton(text, action);
        button.setBackground(new Color(200, 50, 50)); // Red-ish
        return button;
    }

    public static JButton createSuccessButton(String text, ActionListener action) {
        JButton button = createButton(text, action);
        button.setBackground(new Color(40, 180, 99)); // Green
        return button;
    }

    public static JButton createNeutralButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 100, 100)); // Gray
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(33, 150, 243)); // Material blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(189, 189, 189)); // Light gray
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    public static JButton createDangerButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(200, 50, 50)); // Red-ish
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    public static JButton createSuccessButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(40, 180, 99)); // Green
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    public static JButton createDefaultButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

}
