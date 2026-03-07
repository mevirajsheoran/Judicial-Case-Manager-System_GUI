package main.gui.panels;

import main.dao.CourtStaffDAO;
import main.exception.DatabaseException;
import main.model.CourtStaff;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourtStaffPanel extends JPanel {
    private final CourtStaffDAO courtStaffDAO = new CourtStaffDAO();
    private final JTable table;
    private final TableBuilder tableBuilder;
    private final JTextField staffIdField, nameField, roleField, contactField, courtIdField;

    public CourtStaffPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Court Staff Management"));

        // Form Panel
        FormBuilder form = new FormBuilder(5, 2);
        staffIdField = form.addLabeledTextField("Staff ID:");
        nameField = form.addLabeledTextField("Name:");
        roleField = form.addLabeledTextField("Role:");
        contactField = form.addLabeledTextField("Contact:");
        courtIdField = form.addLabeledTextField("Court ID:");

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = ButtonFactory.createPrimaryButton("Add");
        JButton updateBtn = ButtonFactory.createSecondaryButton("Update");
        JButton deleteBtn = ButtonFactory.createDangerButton("Delete");
        JButton clearBtn = ButtonFactory.createDefaultButton("Clear");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);

        // Table Panel
        tableBuilder = new TableBuilder(new String[]{"Staff ID", "Name", "Role", "Contact", "Court ID"});
        table = tableBuilder.getTable();
        refreshTable();

        // Add listeners
        addBtn.addActionListener(e -> addCourtStaff());
        updateBtn.addActionListener(e -> updateCourtStaff());
        deleteBtn.addActionListener(e -> deleteCourtStaff());
        clearBtn.addActionListener(e -> clearForm());
        table.getSelectionModel().addListSelectionListener(e -> populateFormFromTable());

        // Add to Panel
        add(form.getPanel(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addCourtStaff() {
        try {
            CourtStaff staff = getFormData();
            courtStaffDAO.addCourtStaff(staff);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Court staff added successfully!");
        } catch (DatabaseException | NumberFormatException ex) {
            showError(ex);
        }
    }

    private void updateCourtStaff() {
        try {
            CourtStaff staff = getFormData();
            courtStaffDAO.updateCourtStaff(staff);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Court staff updated successfully!");
        } catch (DatabaseException | NumberFormatException ex) {
            showError(ex);
        }
    }

    private void deleteCourtStaff() {
        try {
            int staffId = Integer.parseInt(staffIdField.getText());
            courtStaffDAO.deleteCourtStaff(staffId);
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Court staff deleted successfully!");
        } catch (DatabaseException | NumberFormatException ex) {
            showError(ex);
        }
    }

    private void refreshTable() {
        try {
            List<CourtStaff> staffList = courtStaffDAO.getAllCourtStaff();
            tableBuilder.clearRows();
            for (CourtStaff s : staffList) {
                tableBuilder.addRow(new Object[]{
                        s.getStaffId(),
                        s.getName(),
                        s.getRole(),
                        s.getContact(),
                        s.getCourtId()
                });
            }
        } catch (DatabaseException ex) {
            showError(ex);
        }
    }

    private void populateFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            staffIdField.setText(table.getValueAt(row, 0).toString());
            nameField.setText(table.getValueAt(row, 1).toString());
            roleField.setText(table.getValueAt(row, 2).toString());
            contactField.setText(table.getValueAt(row, 3).toString());
            courtIdField.setText(table.getValueAt(row, 4).toString());
        }
    }

    private void clearForm() {
        staffIdField.setText("");
        nameField.setText("");
        roleField.setText("");
        contactField.setText("");
        courtIdField.setText("");
        table.clearSelection();
    }

    private CourtStaff getFormData() {
        int staffId = Integer.parseInt(staffIdField.getText().trim());
        String name = nameField.getText().trim();
        String role = roleField.getText().trim();
        String contact = contactField.getText().trim();
        int courtId = Integer.parseInt(courtIdField.getText().trim());

        return new CourtStaff(staffId, name, role, contact, courtId);
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
