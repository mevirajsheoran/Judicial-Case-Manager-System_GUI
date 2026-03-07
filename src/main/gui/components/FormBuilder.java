package main.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormBuilder {
    private final JPanel panel;
    private final Map<String, JComponent> fields;
    private int currentRow;

    public FormBuilder() {
        panel = new JPanel(new GridBagLayout());
        fields = new LinkedHashMap<>();
        currentRow = 0;
    }

    public FormBuilder(int rows, int cols) {
        panel = new JPanel(new GridLayout(rows, cols, 10, 10));
        fields = new LinkedHashMap<>();
    }

    public FormBuilder addLabelAndField(String label, JComponent field) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = currentRow;
        panel.add(new JLabel(label + ":"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);

        fields.put(label, field);
        currentRow++;
        return this;
    }


    public static JPanel createFormPanel(String[] labels, JComponent[] fields) {
        if (labels.length != fields.length) {
            throw new IllegalArgumentException("Labels and fields must have the same length.");
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            panel.add(fields[i]);
        }

        return panel;
    }

    public static JPanel createForm(String[] labels, JTextField[] fields) {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i] + ":"), gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            fields[i].setPreferredSize(new Dimension(200, 25));
            formPanel.add(fields[i], gbc);
        }

        return formPanel;
    }

    public static void addFormRow(JPanel formPanel, String label, JTextField textField) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        int row = formPanel.getComponentCount() / 2;

        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel(label + ":"), gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        textField.setPreferredSize(new Dimension(200, 25));
        formPanel.add(textField, gbc);
    }


    public static JPanel createFormPanel(int rows, int cols) {
        return new JPanel(new GridLayout(rows, cols, 10, 10));
    }


//    public FormBuilder addTextField(String label) {
//        JTextField field = new JTextField();
//        panel.add(new JLabel(label));
//        panel.add(field);
//        fields.put(label, field);
//        return this;
//    }
//
//    public FormBuilder addDateField(String label) {
//        JTextField field = new JTextField("yyyy-mm-dd");
//        panel.add(new JLabel(label));
//        panel.add(field);
//        fields.put(label, field);
//        return this;
//    }


    public FormBuilder addTextField(String label) {
        return addLabelAndField(label, new JTextField(20));
    }

    public FormBuilder addDateField(String label) {
        JTextField dateField = new JTextField("yyyy-mm-dd");
        return addLabelAndField(label, dateField);
    }


    public FormBuilder addCheckBox(String label) {
        JCheckBox checkBox = new JCheckBox();
        return addLabelAndField(label, checkBox);
    }

    public FormBuilder addComboBox(String label, String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        return addLabelAndField(label, comboBox);
    }


    public FormBuilder addField(String label, String type) {
        if ("date".equalsIgnoreCase(type)) {
            return addDateField(label);
        } else if ("combo".equalsIgnoreCase(type)) {
            throw new UnsupportedOperationException("Use addComboBox() for combo fields.");
        } else {
            return addTextField(label);
        }
    }

    public JTextField addLabeledTextField(String label) {
        JTextField field = new JTextField();
        panel.add(new JLabel(label));
        panel.add(field);
        fields.put(label, field);
        return field;
    }

    public JTextArea addLabeledTextArea(String label) {
        JTextArea area = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(area);
        panel.add(new JLabel(label));
        panel.add(scrollPane);
        fields.put(label, area);
        return area;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getFormPanel() {
        return panel;
    }

    public String getText(String label) {
        JComponent component = fields.get(label);
        if (component instanceof JTextField) {
            return ((JTextField) component).getText().trim();
        }
        return "";
    }

    public void setValue(String label, String value) {
        JComponent field = fields.get(label);
        if (field instanceof JTextField) {
            ((JTextField) field).setText(value);
        } else if (field instanceof JComboBox<?>) {
            ((JComboBox<String>) field).setSelectedItem(value);
        } else if (field instanceof JTextArea) {
            ((JTextArea) field).setText(value);
        } else if (field instanceof JCheckBox) {
            ((JCheckBox) field).setSelected(Boolean.parseBoolean(value));
        }
    }

    public void setFieldValue(String label, String value) {
        setValue(label, value);
    }

    public String getFieldValue(String label) {
        JComponent component = fields.get(label);
        if (component instanceof JTextField) {
            return ((JTextField) component).getText().trim();
        } else if (component instanceof JComboBox<?>) {
            return (String) ((JComboBox<?>) component).getSelectedItem();
        } else if (component instanceof JTextArea) {
            return ((JTextArea) component).getText().trim();
        } else if (component instanceof JCheckBox) {
            return String.valueOf(((JCheckBox) component).isSelected());
        }
        return "";
    }

    public void clearForm() {
        for (Map.Entry<String, JComponent> entry : fields.entrySet()) {
            JComponent field = entry.getValue();
            if (field instanceof JTextField) {
                ((JTextField) field).setText("");
            } else if (field instanceof JComboBox<?>) {
                ((JComboBox<?>) field).setSelectedIndex(0);
            } else if (field instanceof JTextArea) {
                ((JTextArea) field).setText("");
            } else if (field instanceof JCheckBox) {
                ((JCheckBox) field).setSelected(false);
            }
        }
    }

    public void clearFields() {
        clearForm();
    }

    // === ✅ Completed missing methods below ===

    public void add(String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
        fields.put(label, field);
    }

    public void add(String label, JScrollPane scrollPane) {
        panel.add(new JLabel(label));
        panel.add(scrollPane);
        fields.put(label, scrollPane);
    }

    public void add(String label, JCheckBox checkBox) {
        panel.add(new JLabel(label));
        panel.add(checkBox);
        fields.put(label, checkBox);
    }

    public void addLabeledField(String label, JTextField field) {
        add(label, field);
    }

}