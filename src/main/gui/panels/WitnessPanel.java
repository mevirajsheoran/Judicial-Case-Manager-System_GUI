package main.gui.panels;

import main.dao.WitnessDAO;
import main.exception.DatabaseException;
import main.model.person.Witness;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WitnessPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private WitnessDAO witnessDAO;

    public WitnessPanel() {
        witnessDAO = new WitnessDAO();
        setLayout(new BorderLayout());
        initTable();
        initButtons();
        loadWitnesses();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Name", "Contact", "Address", "Testimony Date", "Type of Witness"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> openForm(null));

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                int id = (int) tableModel.getValueAt(selected, 0);
                try {
                    Witness w = witnessDAO.getWitnessById(id);
                    openForm(w);
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Please select a witness to update.");
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                int id = (int) tableModel.getValueAt(selected, 0);
                try {
                    witnessDAO.deleteWitness(id);
                    loadWitnesses();
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Please select a witness to delete.");
            }
        });

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void openForm(Witness witness) {
        JTextField nameField = new JTextField(witness != null ? witness.getName() : "");
        JTextField contactField = new JTextField(witness != null ? witness.getContact() : "");
        JTextField addressField = new JTextField(witness != null ? witness.getAddress() : "");
        JTextField testimonyDateField = new JTextField(witness != null ?
                new SimpleDateFormat("yyyy-MM-dd").format(witness.getTestimonyDate()) : "");
        JTextField typeOfWitnessField = new JTextField(witness != null ? witness.getTypeOfWitness() : "");

        JPanel form = new JPanel(new GridLayout(0, 2));
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Contact:")); form.add(contactField);
        form.add(new JLabel("Address:")); form.add(addressField);
        form.add(new JLabel("Testimony Date (yyyy-MM-dd):")); form.add(testimonyDateField);
        form.add(new JLabel("Type of Witness:")); form.add(typeOfWitnessField);

        int result = JOptionPane.showConfirmDialog(this, form,
                witness == null ? "Add Witness" : "Update Witness",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                if (witness == null) {
                    witness = new Witness();
                }
                witness.setName(nameField.getText());
                witness.setContact(contactField.getText());
                witness.setAddress(addressField.getText());
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(testimonyDateField.getText());
                witness.setTestimonyDate(parsedDate);
                witness.setTypeOfWitness(typeOfWitnessField.getText());

                if (witness.getPersonId() == 0) {
                    witnessDAO.addWitness(witness);
                } else {
                    witnessDAO.updateWitness(witness);
                }

                loadWitnesses();
            } catch (Exception ex) {
                showError("Invalid input: " + ex.getMessage());
            }
        }
    }

    private void loadWitnesses() {
        try {
            List<Witness> list = witnessDAO.getAllWitnesses();
            tableModel.setRowCount(0);
            for (Witness w : list) {
                tableModel.addRow(new Object[]{
                        w.getPersonId(),
                        w.getName(),
                        w.getContact(),
                        w.getAddress(),
                        new SimpleDateFormat("yyyy-MM-dd").format(w.getTestimonyDate()),
                        w.getTypeOfWitness()
                });
            }
        } catch (DatabaseException e) {
            showError("Failed to load witnesses: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
