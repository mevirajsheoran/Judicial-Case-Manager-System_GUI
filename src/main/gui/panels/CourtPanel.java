package main.gui.panels;

import main.dao.CourtDAO;
import main.exception.DatabaseException;
import main.model.Court;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CourtPanel extends JPanel {
    private final CourtDAO courtDAO;
    private final JTable courtTable;
    private final DefaultTableModel tableModel;

    private final JTextField courtIdField = new JTextField();
    private final JTextField courtNameField = new JTextField();
    private final JTextField locationField = new JTextField();
    private final JTextField jurisdictionLevelField = new JTextField();

    public CourtPanel() {
        this.courtDAO = new CourtDAO();

        setLayout(new BorderLayout());

        // Table
        String[] columns = {"Court ID", "Court Name", "Location", "Jurisdiction Level"};
        tableModel = new DefaultTableModel(columns, 0);
        courtTable = TableBuilder.createTable(tableModel);
        add(new JScrollPane(courtTable), BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = FormBuilder.createFormPanel(
                new String[]{"Court ID", "Court Name", "Location", "Jurisdiction Level"},
                new JComponent[]{courtIdField, courtNameField, locationField, jurisdictionLevelField}
        );
        add(formPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = ButtonFactory.createButton("Add");
        JButton updateButton = ButtonFactory.createButton("Update");
        JButton deleteButton = ButtonFactory.createButton("Delete");
        JButton refreshButton = ButtonFactory.createButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addCourt());
        updateButton.addActionListener(e -> updateCourt());
        deleteButton.addActionListener(e -> deleteCourt());
        refreshButton.addActionListener(e -> loadCourts());

        // Row selection
        courtTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && courtTable.getSelectedRow() != -1) {
                int row = courtTable.getSelectedRow();
                courtIdField.setText(tableModel.getValueAt(row, 0).toString());
                courtNameField.setText(tableModel.getValueAt(row, 1).toString());
                locationField.setText(tableModel.getValueAt(row, 2).toString());
                jurisdictionLevelField.setText(tableModel.getValueAt(row, 3).toString());
                courtIdField.setEditable(false);
            }
        });

        // Load data
        loadCourts();
    }

    private void loadCourts() {
        tableModel.setRowCount(0);
        try {
            List<Court> courts = courtDAO.getAllCourts();
            for (Court c : courts) {
                tableModel.addRow(new Object[]{
                        c.getCourtId(), c.getCourtName(), c.getLocation(), c.getJurisdictionLevel()
                });
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCourt() {
        try {
            Court court = new Court();
            court.setCourtId(Integer.parseInt(courtIdField.getText()));
            court.setCourtName(courtNameField.getText());
            court.setLocation(locationField.getText());
            court.setJurisdictionLevel(jurisdictionLevelField.getText());

            courtDAO.addCourt(court);
            loadCourts();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to add court: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCourt() {
        try {
            Court court = new Court();
            court.setCourtId(Integer.parseInt(courtIdField.getText()));
            court.setCourtName(courtNameField.getText());
            court.setLocation(locationField.getText());
            court.setJurisdictionLevel(jurisdictionLevelField.getText());

            courtDAO.updateCourt(court);
            loadCourts();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update court: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourt() {
        try {
            int courtId = Integer.parseInt(courtIdField.getText());
            courtDAO.deleteCourt(courtId);
            loadCourts();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to delete court: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        courtIdField.setText("");
        courtNameField.setText("");
        locationField.setText("");
        jurisdictionLevelField.setText("");
        courtIdField.setEditable(true);
    }
}
