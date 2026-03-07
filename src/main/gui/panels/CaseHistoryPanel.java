package main.gui.panels;

import main.dao.CaseHistoryDAO;
import main.exception.DatabaseException;
import main.model.CaseHistory;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CaseHistoryPanel extends JPanel {

    private final CaseHistoryDAO dao;
    private final JTable table;
    private final TableBuilder tableBuilder; //Removed generic type

    private final JTextField txtHistoryId = new JTextField(10);
    private final JTextField txtCaseId = new JTextField(10);
    private final JTextField txtDate = new JTextField(10); // Format: YYYY-MM-DD
    private final JTextField txtStatusUpdate = new JTextField(20);
    private final JTextField txtNotes = new JTextField(20);
    private final JTextField txtUpdatedBy = new JTextField(10);

    public CaseHistoryPanel() {
        this.dao = new CaseHistoryDAO();
        this.setLayout(new BorderLayout());

        // ✅ Table
        String[] columns = {"History ID", "Case ID", "Date", "Status Update", "Notes", "Updated By"};
        tableBuilder = new TableBuilder(columns);
        table = tableBuilder.getTable();

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        // ✅ Form Panel
        FormBuilder formBuilder = new FormBuilder();
        formBuilder.add("History ID:", txtHistoryId);
        formBuilder.add("Case ID:", txtCaseId);
        formBuilder.add("Date (YYYY-MM-DD):", txtDate);
        formBuilder.add("Status Update:", txtStatusUpdate);
        formBuilder.add("Notes:", txtNotes);
        formBuilder.add("Updated By (Staff ID):", txtUpdatedBy);
        this.add(formBuilder.getPanel(), BorderLayout.NORTH);

        // ✅ Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = ButtonFactory.createButton("Add");
        JButton btnUpdate = ButtonFactory.createButton("Update");
        JButton btnDelete = ButtonFactory.createButton("Delete");
        JButton btnRefresh = ButtonFactory.createButton("Refresh");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // ✅ Load Data
        refreshTable();

        // ✅ Action Listeners
        btnAdd.addActionListener(e -> addCaseHistory());
        btnUpdate.addActionListener(e -> updateCaseHistory());
        btnDelete.addActionListener(e -> deleteCaseHistory());
        btnRefresh.addActionListener(e -> refreshTable());

        // ✅ Populate form on row click
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && row < table.getRowCount()) {
                txtHistoryId.setText(table.getValueAt(row, 0).toString());
                txtCaseId.setText(table.getValueAt(row, 1).toString());
                txtDate.setText(table.getValueAt(row, 2).toString());
                txtStatusUpdate.setText(table.getValueAt(row, 3).toString());
                txtNotes.setText(table.getValueAt(row, 4).toString());
                txtUpdatedBy.setText(table.getValueAt(row, 5).toString());
            }
        });
    }

    private void refreshTable() {
        try {
            List<CaseHistory> histories = dao.getAllCaseHistories();
            tableBuilder.clearRows();
            for (CaseHistory h : histories) {
                tableBuilder.addRow(
                        h.getHistoryId(),
                        h.getCaseId(),
                        h.getDate(),
                        h.getStatusUpdate(),
                        h.getNotes(),
                        h.getUpdatedBy()
                );
            }
        } catch (DatabaseException e) {
            showError(e.getMessage());
        }
    }

    private void addCaseHistory() {
        try {
            CaseHistory history = getFormData();
            dao.addCaseHistory(history);
            refreshTable();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void updateCaseHistory() {
        try {
            CaseHistory history = getFormData();
            dao.updateCaseHistory(history);
            refreshTable();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void deleteCaseHistory() {
        try {
            int historyId = Integer.parseInt(txtHistoryId.getText());
            dao.deleteCaseHistory(historyId);
            refreshTable();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private CaseHistory getFormData() {
        int historyId = Integer.parseInt(txtHistoryId.getText());
        int caseId = Integer.parseInt(txtCaseId.getText());
        LocalDate date = LocalDate.parse(txtDate.getText());
        String statusUpdate = txtStatusUpdate.getText();
        String notes = txtNotes.getText();
        int updatedBy = Integer.parseInt(txtUpdatedBy.getText());

        return new CaseHistory(historyId, caseId, date, statusUpdate, notes, updatedBy);
    }

    private void clearForm() {
        txtHistoryId.setText("");
        txtCaseId.setText("");
        txtDate.setText("");
        txtStatusUpdate.setText("");
        txtNotes.setText("");
        txtUpdatedBy.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
