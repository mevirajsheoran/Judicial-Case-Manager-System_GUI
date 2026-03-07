package main.gui.panels;

import main.dao.AppealDAO;
import main.exception.DatabaseException;
import main.model.Appeal;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class AppealPanel extends JPanel {
    private final JTable appealTable;
    private final DefaultTableModel tableModel;

    private final JTextField txtAppealId = new JTextField(10);
    private final JTextField txtCaseId = new JTextField(10);
    private final JTextField txtFiledBy = new JTextField(10);
    private final JTextField txtDate = new JTextField(10);
    private final JTextArea txtReason = new JTextArea(3, 15);
    private final JTextField txtStatus = new JTextField(10);
    private final JTextField txtAppealLevel = new JTextField(10);

    private final AppealDAO appealDAO = new AppealDAO();

    public AppealPanel() {
        setLayout(new BorderLayout());

        // Table
        String[] columns = {"Appeal ID", "Case ID", "Filed By", "Date", "Reason", "Status", "Appeal Level"};
        tableModel = new DefaultTableModel(columns, 0);
        appealTable = TableBuilder.buildTable(tableModel);
        loadAppeals();

        // Form
        JPanel formPanel = FormBuilder.createFormPanel(
                new String[]{"Appeal ID", "Case ID", "Filed By", "Date (yyyy-mm-dd)", "Reason", "Status", "Appeal Level"},
                new JComponent[]{txtAppealId, txtCaseId, txtFiledBy, txtDate, new JScrollPane(txtReason), txtStatus, txtAppealLevel}
        );

        // Buttons
        JButton btnAdd = ButtonFactory.createPrimaryButton("Add");
        JButton btnUpdate = ButtonFactory.createSuccessButton("Update");
        JButton btnDelete = ButtonFactory.createDangerButton("Delete");
        JButton btnClear = ButtonFactory.createSecondaryButton("Clear");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Add listeners
        btnAdd.addActionListener(e -> addAppeal());
        btnUpdate.addActionListener(e -> updateAppeal());
        btnDelete.addActionListener(e -> deleteAppeal());
        btnClear.addActionListener(e -> clearForm());

        appealTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) populateFormFromTable();
        });

        add(new JScrollPane(appealTable), BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadAppeals() {
        try {
            List<Appeal> appeals = appealDAO.getAllAppeals();
            tableModel.setRowCount(0);
            for (Appeal appeal : appeals) {
                tableModel.addRow(new Object[]{
                        appeal.getAppealId(),
                        appeal.getCaseId(),
                        appeal.getFiledBy(),
                        appeal.getDate(),
                        appeal.getReason(),
                        appeal.getStatus(),
                        appeal.getAppealLevel()
                });
            }
        } catch (DatabaseException e) {
            showError(e.getMessage());
        }
    }

    private void addAppeal() {
        try {
            Appeal appeal = getAppealFromForm();
            appealDAO.addAppeal(appeal);
            showMessage("Appeal added successfully.");
            loadAppeals();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void updateAppeal() {
        try {
            Appeal appeal = getAppealFromForm();
            appealDAO.updateAppeal(appeal);
            showMessage("Appeal updated successfully.");
            loadAppeals();
            clearForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void deleteAppeal() {
        int selectedRow = appealTable.getSelectedRow();
        if (selectedRow >= 0) {
            int appealId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            try {
                appealDAO.deleteAppeal(appealId);
                showMessage("Appeal deleted successfully.");
                loadAppeals();
                clearForm();
            } catch (DatabaseException e) {
                showError(e.getMessage());
            }
        } else {
            showError("Please select an appeal to delete.");
        }
    }

    private Appeal getAppealFromForm() {
        Appeal appeal = new Appeal();
        appeal.setAppealId(Integer.parseInt(txtAppealId.getText()));
        appeal.setCaseId(Integer.parseInt(txtCaseId.getText()));
        appeal.setFiledBy(Integer.parseInt(txtFiledBy.getText()));
        appeal.setDate(Date.valueOf(txtDate.getText()));
        appeal.setReason(txtReason.getText());
        appeal.setStatus(txtStatus.getText());
        appeal.setAppealLevel(txtAppealLevel.getText());
        return appeal;
    }

    private void populateFormFromTable() {
        int selectedRow = appealTable.getSelectedRow();
        if (selectedRow >= 0) {
            txtAppealId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtCaseId.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtFiledBy.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtDate.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtReason.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtStatus.setText(tableModel.getValueAt(selectedRow, 5).toString());
            txtAppealLevel.setText(tableModel.getValueAt(selectedRow, 6).toString());
        }
    }

    private void clearForm() {
        txtAppealId.setText("");
        txtCaseId.setText("");
        txtFiledBy.setText("");
        txtDate.setText("");
        txtReason.setText("");
        txtStatus.setText("");
        txtAppealLevel.setText("");
        appealTable.clearSelection();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
