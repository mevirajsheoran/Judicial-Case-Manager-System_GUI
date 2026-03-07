package main.gui.panels;

import main.dao.RespondentDAO;
import main.exception.DatabaseException;
import main.model.person.Respondent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RespondentPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private RespondentDAO respondentDAO;

    public RespondentPanel() {
        respondentDAO = new RespondentDAO();
        setLayout(new BorderLayout());
        initTable();
        initButtons();
        loadRespondents();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Name", "Contact", "Address", "Response Date", "Legal Rep ID"}, 0);
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
                    Respondent r = respondentDAO.getRespondentById(id);
                    openForm(r);
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Please select a respondent to update.");
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                int id = (int) tableModel.getValueAt(selected, 0);
                try {
                    respondentDAO.deleteRespondent(id);
                    loadRespondents();
                } catch (DatabaseException ex) {
                    showError(ex.getMessage());
                }
            } else {
                showError("Please select a respondent to delete.");
            }
        });

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void openForm(Respondent respondent) {
        JTextField nameField = new JTextField(respondent != null ? respondent.getName() : "");
        JTextField contactField = new JTextField(respondent != null ? respondent.getContact() : "");
        JTextField addressField = new JTextField(respondent != null ? respondent.getAddress() : "");
        JTextField responseDateField = new JTextField(respondent != null ? 
            new SimpleDateFormat("yyyy-MM-dd").format(respondent.getResponseSubmittedDate()) : "");
        JTextField legalRepField = new JTextField(respondent != null ? 
            String.valueOf(respondent.getLegalRepresentativeId()) : "");

        JPanel form = new JPanel(new GridLayout(0, 2));
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Contact:")); form.add(contactField);
        form.add(new JLabel("Address:")); form.add(addressField);
        form.add(new JLabel("Response Date (yyyy-MM-dd):")); form.add(responseDateField);
        form.add(new JLabel("Legal Rep ID:")); form.add(legalRepField);

        int result = JOptionPane.showConfirmDialog(this, form, respondent == null ? "Add Respondent" : "Update Respondent", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                if (respondent == null) {
                    respondent = new Respondent();
                }
                respondent.setName(nameField.getText());
                respondent.setContact(contactField.getText());
                respondent.setAddress(addressField.getText());
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(responseDateField.getText());
                respondent.setResponseSubmittedDate(parsedDate);
                respondent.setLegalRepresentativeId(Integer.parseInt(legalRepField.getText()));

                if (respondent.getPersonId() == 0) {
                    respondentDAO.addRespondent(respondent);
                } else {
                    respondentDAO.updateRespondent(respondent);
                }

                loadRespondents();
            } catch (Exception ex) {
                showError("Invalid input: " + ex.getMessage());
            }
        }
    }

    private void loadRespondents() {
        try {
            List<Respondent> list = respondentDAO.getAllRespondents();
            tableModel.setRowCount(0);
            for (Respondent r : list) {
                tableModel.addRow(new Object[]{
                        r.getPersonId(),
                        r.getName(),
                        r.getContact(),
                        r.getAddress(),
                        new SimpleDateFormat("yyyy-MM-dd").format(r.getResponseSubmittedDate()),
                        r.getLegalRepresentativeId()
                });
            }
        } catch (DatabaseException e) {
            showError("Failed to load respondents: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
