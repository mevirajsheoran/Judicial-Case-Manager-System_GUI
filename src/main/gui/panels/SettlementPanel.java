package main.gui.panels;

import main.dao.SettlementDAO;
import main.exception.DatabaseException;
import main.model.Settlement;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

public class SettlementPanel extends JPanel {

    private final JTable table;
    private final TableBuilder tableBuilder;
    private final SettlementDAO settlementDAO;

    private final JTextField idField = new JTextField();
    private final JTextField caseIdField = new JTextField();
    private final JTextArea termsArea = new JTextArea(3, 20);
    private final JTextField dateField = new JTextField("yyyy-mm-dd");
    private final JCheckBox agreementCheck = new JCheckBox("Agreement Signed");

    public SettlementPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Settlement Management"));

        settlementDAO = new SettlementDAO();
        tableBuilder = new TableBuilder(new String[]{"ID", "Case ID", "Terms", "Date", "Signed"});
        table = tableBuilder.getTable();

        refreshTable();

        // Form
        FormBuilder formBuilder = new FormBuilder();
        formBuilder.add("Settlement ID:", idField);
        formBuilder.add("Case ID:", caseIdField);
        formBuilder.add("Terms:", new JScrollPane(termsArea));
        formBuilder.add("Date (yyyy-mm-dd):", dateField);
        formBuilder.add("Agreement Signed:", agreementCheck);

        // Buttons
        JButton addBtn = ButtonFactory.createPrimaryButton("Add");
        JButton updateBtn = ButtonFactory.createSecondaryButton("Update");
        JButton deleteBtn = ButtonFactory.createDangerButton("Delete");
        JButton clearBtn = ButtonFactory.createSecondaryButton("Clear");

        addBtn.addActionListener(this::handleAdd);
        updateBtn.addActionListener(this::handleUpdate);
        deleteBtn.addActionListener(this::handleDelete);
        clearBtn.addActionListener(e -> clearFields());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);

        this.add(formBuilder.getPanel(), BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> populateFieldsFromTable());
    }

    private void refreshTable() {
        try {
            List<Settlement> settlements = settlementDAO.getAllSettlements();
            tableBuilder.clear();
            for (Settlement s : settlements) {
                tableBuilder.addRow(new Object[]{
                        s.getSettlementId(),
                        s.getCaseId(),
                        s.getTerms(),
                        s.getDate(),
                        s.isAgreementSigned()
                });
            }
        } catch (DatabaseException e) {
            showError("Failed to load settlements: " + e.getMessage());
        }
    }

    private void handleAdd(ActionEvent e) {
        try {
            Settlement s = getSettlementFromFields();
            settlementDAO.addSettlement(s);
            refreshTable();
            clearFields();
            showInfo("Settlement added successfully.");
        } catch (Exception ex) {
            showError("Failed to add: " + ex.getMessage());
        }
    }

    private void handleUpdate(ActionEvent e) {
        try {
            Settlement s = getSettlementFromFields();
            settlementDAO.updateSettlement(s);
            refreshTable();
            clearFields();
            showInfo("Settlement updated successfully.");
        } catch (Exception ex) {
            showError("Failed to update: " + ex.getMessage());
        }
    }

    private void handleDelete(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            if (settlementDAO.deleteSettlement(id)) {
                refreshTable();
                clearFields();
                showInfo("Settlement deleted successfully.");
            } else {
                showError("Settlement not found.");
            }
        } catch (Exception ex) {
            showError("Failed to delete: " + ex.getMessage());
        }
    }

    private Settlement getSettlementFromFields() {
        int id = Integer.parseInt(idField.getText().trim());
        int caseId = Integer.parseInt(caseIdField.getText().trim());
        String terms = termsArea.getText().trim();
        Date date = Date.valueOf(dateField.getText().trim());
        boolean signed = agreementCheck.isSelected();

        return new Settlement();
    }

    private void populateFieldsFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            idField.setText(table.getValueAt(row, 0).toString());
            caseIdField.setText(table.getValueAt(row, 1).toString());
            termsArea.setText(table.getValueAt(row, 2).toString());
            dateField.setText(table.getValueAt(row, 3).toString());
            agreementCheck.setSelected(Boolean.parseBoolean(table.getValueAt(row, 4).toString()));
        }
    }

    private void clearFields() {
        idField.setText("");
        caseIdField.setText("");
        termsArea.setText("");
        dateField.setText("");
        agreementCheck.setSelected(false);
        table.clearSelection();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
