package main.gui.panels;

import main.dao.HearingDAO;
import main.exception.DatabaseException;
import main.model.Hearing;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class HearingPanel extends JPanel {

    private JTable hearingTable;
    private TableBuilder tableBuilder;
    private FormBuilder formBuilder;
    private HearingDAO hearingDAO;

    public HearingPanel() {
        this.hearingDAO = new HearingDAO();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Hearing Management"));

        initComponents();
        loadHearingData();
    }

    private void initComponents() {
        String[] columns = {"Hearing ID", "Case ID", "Judge ID", "Date", "Time", "Description", "Outcome"};
        tableBuilder = new TableBuilder(columns);
        hearingTable = tableBuilder.getTable();

        JScrollPane tableScrollPane = new JScrollPane(hearingTable);

        formBuilder = new FormBuilder();
        formBuilder.addField("Hearing_ID", "Hearing ID");
        formBuilder.addField("Case_ID", "Case ID");
        formBuilder.addField("Judge_ID", "Judge ID");
        formBuilder.addField("Date", "Date (YYYY-MM-DD)");
        formBuilder.addField("Time", "Time (HH:MM:SS)");
        formBuilder.addField("Description", "Description");
        formBuilder.addField("Outcome", "Outcome");

        JPanel formPanel = formBuilder.getFormPanel();

        JButton addButton = ButtonFactory.createPrimaryButton("Add");
        JButton editButton = ButtonFactory.createSecondaryButton("Edit");
        JButton deleteButton = ButtonFactory.createDangerButton("Delete");

        addButton.addActionListener(e -> addHearing());
        editButton.addActionListener(e -> updateHearing());
        deleteButton.addActionListener(e -> deleteHearing());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(tableScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        hearingTable.getSelectionModel().addListSelectionListener(e -> fillFormFromSelectedRow());
    }

    private void loadHearingData() {
        try {
            List<Hearing> hearingList = hearingDAO.getAllHearings();
            tableBuilder.clearTable();
            for (Hearing h : hearingList) {
                tableBuilder.addRow(new Object[]{
                        h.getHearingId(),
                        h.getCaseId(),
                        h.getJudgeId(),
                        h.getDate(),
                        h.getTime(),
                        h.getDescription(),
                        h.getOutcome()
                });
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Failed to load hearings: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addHearing() {
        try {
            Hearing hearing = getHearingFromForm();
            hearingDAO.addHearing(hearing);
            JOptionPane.showMessageDialog(this, "Hearing added successfully.");
            loadHearingData();
            formBuilder.clearFields();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database error while adding hearing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateHearing() {
        try {
            Hearing hearing = getHearingFromForm();
            hearingDAO.updateHearing(hearing);
            JOptionPane.showMessageDialog(this, "Hearing updated successfully.");
            loadHearingData();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database error while updating hearing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteHearing() {
        int row = hearingTable.getSelectedRow();
        if (row >= 0) {
            int hearingId = (int) hearingTable.getValueAt(row, 0);
            try {
                boolean deleted = hearingDAO.deleteHearing(hearingId);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Hearing deleted successfully.");
                    loadHearingData();
                    formBuilder.clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Hearing not found or could not be deleted.");
                }
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, "Database error while deleting hearing: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a hearing to delete.");
        }
    }

    private Hearing getHearingFromForm() throws Exception {
        Hearing hearing = new Hearing();
        try {
            hearing.setHearingId(Integer.parseInt(formBuilder.getFieldValue("Hearing_ID")));
            hearing.setCaseId(Integer.parseInt(formBuilder.getFieldValue("Case_ID")));
            hearing.setJudgeId(Integer.parseInt(formBuilder.getFieldValue("Judge_ID")));
            hearing.setDate(Date.valueOf(formBuilder.getFieldValue("Date")));
            hearing.setTime(Time.valueOf(formBuilder.getFieldValue("Time")));
            hearing.setDescription(formBuilder.getFieldValue("Description"));
            hearing.setOutcome(formBuilder.getFieldValue("Outcome"));
        } catch (Exception e) {
            throw new Exception("Invalid input format: " + e.getMessage());
        }
        return hearing;
    }

    private void fillFormFromSelectedRow() {
        int row = hearingTable.getSelectedRow();
        if (row >= 0) {
            formBuilder.setFieldValue("Hearing_ID", String.valueOf(hearingTable.getValueAt(row, 0)));
            formBuilder.setFieldValue("Case_ID", String.valueOf(hearingTable.getValueAt(row, 1)));
            formBuilder.setFieldValue("Judge_ID", String.valueOf(hearingTable.getValueAt(row, 2)));
            formBuilder.setFieldValue("Date", String.valueOf(hearingTable.getValueAt(row, 3)));
            formBuilder.setFieldValue("Time", String.valueOf(hearingTable.getValueAt(row, 4)));
            formBuilder.setFieldValue("Description", String.valueOf(hearingTable.getValueAt(row, 5)));
            formBuilder.setFieldValue("Outcome", String.valueOf(hearingTable.getValueAt(row, 6)));
        }
    }
}
