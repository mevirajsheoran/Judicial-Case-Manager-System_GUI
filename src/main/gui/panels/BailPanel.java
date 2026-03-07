package main.gui.panels;

import main.dao.BailRequestDAO;
import main.exception.DatabaseException;
import main.model.BailRequest;
import main.utils.IDGenerator;
import main.gui.components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BailPanel extends JPanel {

    private JTable table;
    private BailRequestDAO bailDAO;
    private DefaultTableModel tableModel;

    // Form fields
    private JTextField txtBailId, txtCaseId, txtPersonId, txtDate, txtConditions, txtAmount, txtStatus;

    public BailPanel() {
        bailDAO = new BailRequestDAO();
        setLayout(new BorderLayout());
        initComponents();
        loadBailData();
    }

    private void initComponents() {
        // --- Table setup ---
        String[] columns = {"Bail ID", "Case ID", "Person ID", "Granted Date", "Conditions", "Bail Amount", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = TableBuilder.buildTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // --- Form setup ---
        JPanel formPanel = FormBuilder.createFormPanel(7, 2);
        txtBailId = new JTextField(IDGenerator.generateId("B")); // auto-generate
        txtCaseId = new JTextField();
        txtPersonId = new JTextField();
        txtDate = new JTextField(LocalDate.now().toString());
        txtConditions = new JTextField();
        txtAmount = new JTextField();
        txtStatus = new JTextField();

        FormBuilder.addFormRow(formPanel, "Bail ID:", txtBailId);
        FormBuilder.addFormRow(formPanel, "Case ID:", txtCaseId);
        FormBuilder.addFormRow(formPanel, "Person ID:", txtPersonId);
        FormBuilder.addFormRow(formPanel, "Granted Date (YYYY-MM-DD):", txtDate);
        FormBuilder.addFormRow(formPanel, "Conditions:", txtConditions);
        FormBuilder.addFormRow(formPanel, "Bail Amount:", txtAmount);
        FormBuilder.addFormRow(formPanel, "Status:", txtStatus);

        // --- Buttons ---
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = ButtonFactory.createPrimaryButton("Add");
        JButton btnUpdate = ButtonFactory.createSecondaryButton("Update");
        JButton btnDelete = ButtonFactory.createDangerButton("Delete");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        btnAdd.addActionListener(e -> addBail());
        btnUpdate.addActionListener(e -> updateBail());
        btnDelete.addActionListener(e -> deleteBail());

        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        // --- Assemble main panel ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadBailData() {
        try {
            tableModel.setRowCount(0);
            List<BailRequest> bailList = bailDAO.getAllBailRequests();
            for (BailRequest b : bailList) {
                tableModel.addRow(new Object[]{
                        b.getBailId(),
                        b.getCaseId(),
                        b.getPersonId(),
                        b.getGrantedDate(),
                        b.getConditions(),
                        b.getBailAmount(),
                        b.getStatus()
                });
            }
        } catch (DatabaseException e) {
            showError("Failed to load bail data: " + e.getMessage());
        }
    }

    private void addBail() {
        try {
            BailRequest bail = getBailFromForm();
            bailDAO.addBailRequest(bail);
            showSuccess("Bail Request added!");
            loadBailData();
        } catch (Exception e) {
            showError("Add failed: " + e.getMessage());
        }
    }

    private void updateBail() {
        try {
            BailRequest bail = getBailFromForm();
            bailDAO.updateBailRequest(bail);
            showSuccess("Bail Request updated!");
            loadBailData();
        } catch (Exception e) {
            showError("Update failed: " + e.getMessage());
        }
    }

    private void deleteBail() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int bailId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                bailDAO.deleteBailRequest(bailId);
                showSuccess("Bail Request deleted!");
                loadBailData();
            } else {
                showError("Please select a row to delete.");
            }
        } catch (Exception e) {
            showError("Delete failed: " + e.getMessage());
        }
    }

    private void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtBailId.setText(tableModel.getValueAt(row, 0).toString());
            txtCaseId.setText(tableModel.getValueAt(row, 1).toString());
            txtPersonId.setText(tableModel.getValueAt(row, 2).toString());
            txtDate.setText(tableModel.getValueAt(row, 3).toString());
            txtConditions.setText(tableModel.getValueAt(row, 4).toString());
            txtAmount.setText(tableModel.getValueAt(row, 5).toString());
            txtStatus.setText(tableModel.getValueAt(row, 6).toString());
        }
    }

    private BailRequest getBailFromForm() {
        return new BailRequest(
                Integer.parseInt(txtBailId.getText().trim()),
                Integer.parseInt(txtCaseId.getText().trim()),
                Integer.parseInt(txtPersonId.getText().trim()),
                LocalDate.parse(txtDate.getText().trim()),
                txtConditions.getText().trim(),
                new BigDecimal(txtAmount.getText().trim()),
                txtStatus.getText().trim()
        );
    }

    private void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
