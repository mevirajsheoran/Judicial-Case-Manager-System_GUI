package main.gui.panels;

import main.dao.PoliceOfficerDAO;
import main.exception.DatabaseException;
import main.model.person.PoliceOfficer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PoliceOfficerPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private PoliceOfficerDAO officerDAO;

    public PoliceOfficerPanel() {
        this.officerDAO = new PoliceOfficerDAO();

        setLayout(new BorderLayout());
        initTable();
        initButtons();
        loadOfficers();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Contact", "Address", "Station ID", "Badge #", "Rank", "Department"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> openOfficerForm(null));

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                int id = (int) tableModel.getValueAt(selected, 0);
                try {
                    PoliceOfficer officer = officerDAO.getPoliceOfficerById(id);
                    openOfficerForm(officer);
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Select an officer to update.");
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                int id = (int) tableModel.getValueAt(selected, 0);
                try {
                    officerDAO.deletePoliceOfficer(id);
                    loadOfficers();
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Select an officer to delete.");
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openOfficerForm(PoliceOfficer officer) {
        JTextField nameField = new JTextField(officer != null ? officer.getName() : "");
        JTextField contactField = new JTextField(officer != null ? officer.getContact() : "");
        JTextField addressField = new JTextField(officer != null ? officer.getAddress() : "");
        JTextField stationIdField = new JTextField(officer != null ? String.valueOf(officer.getPoliceStationId()) : "");
        JTextField badgeNumberField = new JTextField(officer != null ? officer.getBadgeNumber() : "");
        JTextField rankField = new JTextField(officer != null ? officer.getOfficerRank() : "");
        JTextField deptField = new JTextField(officer != null ? officer.getDepartment() : "");

        JPanel form = new JPanel(new GridLayout(0, 2));
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Contact:"));
        form.add(contactField);
        form.add(new JLabel("Address:"));
        form.add(addressField);
        form.add(new JLabel("Station ID:"));
        form.add(stationIdField);
        form.add(new JLabel("Badge #:"));
        form.add(badgeNumberField);
        form.add(new JLabel("Rank:"));
        form.add(rankField);
        form.add(new JLabel("Department:"));
        form.add(deptField);

        int result = JOptionPane.showConfirmDialog(this, form, officer == null ? "Add Officer" : "Update Officer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (officer == null) {
                    officer = new PoliceOfficer();
                }
                officer.setName(nameField.getText());
                officer.setContact(contactField.getText());
                officer.setAddress(addressField.getText());
                officer.setPoliceStationId(Integer.parseInt(stationIdField.getText()));
                officer.setBadgeNumber(badgeNumberField.getText());
                officer.setOfficerRank(rankField.getText());
                officer.setDepartment(deptField.getText());

                if (officer.getPersonId() == 0) {
                    officerDAO.addPoliceOfficer(officer);
                } else {
                    officerDAO.updatePoliceOfficer(officer);
                }

                loadOfficers();
            } catch (Exception e) {
                showError("Invalid input: " + e.getMessage());
            }
        }
    }

    private void loadOfficers() {
        try {
            List<PoliceOfficer> officers = officerDAO.getAllPoliceOfficers();
            tableModel.setRowCount(0);
            for (PoliceOfficer officer : officers) {
                tableModel.addRow(new Object[]{
                        officer.getPersonId(),
                        officer.getName(),
                        officer.getContact(),
                        officer.getAddress(),
                        officer.getPoliceStationId(),
                        officer.getBadgeNumber(),
                        officer.getOfficerRank(),
                        officer.getDepartment()
                });
            }
        } catch (DatabaseException e) {
            showError("Could not load officers: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
