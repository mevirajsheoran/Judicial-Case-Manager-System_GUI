package main.gui.panels;

import main.dao.EvidenceDAO;
import main.exception.DatabaseException;
import main.model.Evidence;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

public class EvidencePanel extends JPanel {
    private final EvidenceDAO evidenceDAO;
    private final JTable evidenceTable;
    private final JTextField idField, caseIdField, typeField, collectedByField, dateField;
    private final JTextArea descriptionArea;

    public EvidencePanel() {
        this.evidenceDAO = new EvidenceDAO();
        setLayout(new BorderLayout());

        // ---------- Table ----------
        evidenceTable = TableBuilder.buildTable(new String[]{"ID", "Case ID", "Type", "Description", "Collected By", "Submission Date"});
        refreshEvidenceTable();
        add(new JScrollPane(evidenceTable), BorderLayout.CENTER);

        // ---------- Form Panel ----------
        FormBuilder form = new FormBuilder();
        idField = form.addLabeledTextField("Evidence ID:");
        caseIdField = form.addLabeledTextField("Case ID:");
        typeField = form.addLabeledTextField("Type:");
        descriptionArea = form.addLabeledTextArea("Description:");
        collectedByField = form.addLabeledTextField("Collected By (Officer ID):");
        dateField = form.addLabeledTextField("Submission Date (yyyy-mm-dd):");

        add(form.getPanel(), BorderLayout.EAST);

        // ---------- Button Panel ----------
        JPanel buttonPanel = new JPanel();
        JButton addBtn = ButtonFactory.createPrimaryButton("Add");
        JButton updateBtn = ButtonFactory.createSecondaryButton("Update");
        JButton deleteBtn = ButtonFactory.createDangerButton("Delete");

        addBtn.addActionListener(this::handleAdd);
        updateBtn.addActionListener(this::handleUpdate);
        deleteBtn.addActionListener(this::handleDelete);

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshEvidenceTable() {
        try {
            List<Evidence> list = evidenceDAO.getAllEvidence();
            String[][] data = new String[list.size()][6];
            for (int i = 0; i < list.size(); i++) {
                Evidence e = list.get(i);
                data[i][0] = String.valueOf(e.getEvidenceId());
                data[i][1] = String.valueOf(e.getCaseId());
                data[i][2] = e.getType();
                data[i][3] = e.getDescription();
                data[i][4] = String.valueOf(e.getCollectedBy());
                data[i][5] = e.getSubmissionDate().toString();
            }
            TableBuilder.updateTable(evidenceTable, data);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAdd(ActionEvent event) {
        try {
            Evidence e = extractEvidenceFromForm();
            evidenceDAO.addEvidence(e);
            refreshEvidenceTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }


    private void handleUpdate(ActionEvent event) {
        try {
            Evidence e = extractEvidenceFromForm();
            evidenceDAO.updateEvidence(e);
            refreshEvidenceTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void handleDelete(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            evidenceDAO.deleteEvidence(id);
            refreshEvidenceTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private Evidence extractEvidenceFromForm() {
        return new Evidence(
                Integer.parseInt(idField.getText()),
                Integer.parseInt(caseIdField.getText()),
                typeField.getText(),
                descriptionArea.getText(),
                Integer.parseInt(collectedByField.getText()),
                Date.valueOf(dateField.getText())
        );
    }

    private void clearForm() {
        idField.setText("");
        caseIdField.setText("");
        typeField.setText("");
        descriptionArea.setText("");
        collectedByField.setText("");
        dateField.setText("");
    }

    private void showError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
