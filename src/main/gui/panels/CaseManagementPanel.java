package main.gui.panels;

import main.dao.CaseDAO;
import main.exception.DatabaseException;
import main.model.CaseDetails;
import main.gui.components.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class CaseManagementPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private CaseDAO caseDAO;

    public CaseManagementPanel() {
        this.caseDAO = new CaseDAO();
        setLayout(new BorderLayout());

        // Top Panel with buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = ButtonFactory.createSuccessButton("Add Case", e -> showForm(null));
        JButton editButton = ButtonFactory.createButton("Edit Case", e -> editSelectedCase());
        JButton deleteButton = ButtonFactory.createDangerButton("Delete Case", e -> deleteSelectedCase());

        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"Case ID", "Case Number", "Filing Date", "Type", "Status", "Category ID", "Court ID"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadCaseData();
    }

    private void loadCaseData() {
        try {
            model.setRowCount(0);
            List<CaseDetails> cases = caseDAO.getAllCases();
            for (CaseDetails c : cases) {
                model.addRow(new Object[]{
                        c.getCaseId(),
                        c.getCaseNumber(),
                        c.getFilingDate(),
                        c.getCaseType(),
                        c.getStatus(),
                        c.getCategoryId(),
                        c.getCourtId()
                });
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, "Error loading cases: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showForm(CaseDetails existingCase) {
        JTextField caseIdField = new JTextField();  // Added
        JTextField caseNumberField = new JTextField();
        JTextField filingDateField = new JTextField();
        JTextField caseTypeField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField categoryIdField = new JTextField();
        JTextField courtIdField = new JTextField();

        if (existingCase != null) {
            caseIdField.setText(String.valueOf(existingCase.getCaseId()));  // Show existing ID
            caseIdField.setEditable(false); // Prevent editing ID when updating
            caseNumberField.setText(existingCase.getCaseNumber());
            filingDateField.setText(existingCase.getFilingDate().toString());
            caseTypeField.setText(existingCase.getCaseType());
            statusField.setText(existingCase.getStatus());
            categoryIdField.setText(String.valueOf(existingCase.getCategoryId()));
            courtIdField.setText(String.valueOf(existingCase.getCourtId()));
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Case ID:"));
        panel.add(caseIdField);
        panel.add(new JLabel("Case Number:"));
        panel.add(caseNumberField);
        panel.add(new JLabel("Filing Date (yyyy-mm-dd):"));
        panel.add(filingDateField);
        panel.add(new JLabel("Case Type:"));
        panel.add(caseTypeField);
        panel.add(new JLabel("Status:"));
        panel.add(statusField);
        panel.add(new JLabel("Category ID:"));
        panel.add(categoryIdField);
        panel.add(new JLabel("Court ID:"));
        panel.add(courtIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, existingCase == null ? "Add Case" : "Edit Case",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                CaseDetails newCase = new CaseDetails();
                newCase.setCaseId(Integer.parseInt(caseIdField.getText().trim()));
                newCase.setCaseNumber(caseNumberField.getText().trim());
                newCase.setFilingDate(Date.valueOf(filingDateField.getText().trim()));
                newCase.setCaseType(caseTypeField.getText().trim());
                newCase.setStatus(statusField.getText().trim());
                newCase.setCategoryId(Integer.parseInt(categoryIdField.getText().trim()));
                newCase.setCourtId(Integer.parseInt(courtIdField.getText().trim()));

                if (existingCase == null) {
                    caseDAO.addCase(newCase);
                    JOptionPane.showMessageDialog(this, "Case added successfully!");
                } else {
                    caseDAO.updateCase(newCase);
                    JOptionPane.showMessageDialog(this, "Case updated successfully!");
                }
                loadCaseData();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving case: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editSelectedCase() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a case to edit.");
            return;
        }

        try {
            String caseIdStr = table.getValueAt(selectedRow, 0).toString();
            int caseId = Integer.parseInt(caseIdStr);
            CaseDetails existing = caseDAO.getCaseById(caseId);
            showForm(existing);
        } catch (DatabaseException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching case: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedCase() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a case to delete.");
            return;
        }

        try {
            String caseIdStr = table.getValueAt(selectedRow, 0).toString(); // Ensure it’s parsed correctly
            int caseId = Integer.parseInt(caseIdStr);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete Case ID: " + caseId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = caseDAO.deleteCase(caseId);
                if (deleted) {
                    loadCaseData(); // ✅ Refresh after delete
                    JOptionPane.showMessageDialog(this, "Case deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete case.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Case ID format.");
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }
}
