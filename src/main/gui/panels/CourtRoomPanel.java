package main.gui.panels;

import main.dao.CourtRoomDAO;
import main.exception.DatabaseException;
import main.model.CourtRoom;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CourtRoomPanel extends JPanel {
    private final CourtRoomDAO courtRoomDAO = new CourtRoomDAO();
    private final JTable table;
    private final DefaultTableModel tableModel;

    private final JTextField roomIdField = new JTextField();
    private final JTextField courtIdField = new JTextField();
    private final JTextField roomNumberField = new JTextField();
    private final JTextField capacityField = new JTextField();
    private final JTextField availabilityStatusField = new JTextField();

    public CourtRoomPanel() {
        setLayout(new BorderLayout());

        // Table
        String[] columns = {"Room ID", "Court ID", "Room Number", "Capacity", "Availability Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = TableBuilder.createTable(tableModel);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form
        JPanel form = FormBuilder.createForm(
                new String[]{"Room ID", "Court ID", "Room Number", "Capacity", "Availability Status"},
                new JTextField[]{roomIdField, courtIdField, roomNumberField, capacityField, availabilityStatusField}
        );

        // Buttons
        JButton addButton = ButtonFactory.createButton("Add", e -> addCourtRoom());
        JButton updateButton = ButtonFactory.createButton("Update", e -> updateCourtRoom());
        JButton deleteButton = ButtonFactory.createButton("Delete", e -> deleteCourtRoom());
        JButton clearButton = ButtonFactory.createButton("Clear", e -> clearForm());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(form, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
    }

    private void addCourtRoom() {
        try {
            CourtRoom room = getFormData();
            courtRoomDAO.addCourtRoom(room);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void updateCourtRoom() {
        try {
            CourtRoom room = getFormData();
            courtRoomDAO.updateCourtRoom(room);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void deleteCourtRoom() {
        try {
            int roomId = Integer.parseInt(roomIdField.getText().trim());
            courtRoomDAO.deleteCourtRoom(roomId);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private CourtRoom getFormData() {
        CourtRoom room = new CourtRoom();
        room.setRoomId(Integer.parseInt(roomIdField.getText().trim()));
        room.setCourtId(Integer.parseInt(courtIdField.getText().trim()));
        room.setRoomNumber(roomNumberField.getText().trim());
        room.setCapacity(Integer.parseInt(capacityField.getText().trim()));
        room.setAvailabilityStatus(availabilityStatusField.getText().trim());
        return room;
    }

    private void fillFormFromTable() {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            roomIdField.setText(tableModel.getValueAt(selected, 0).toString());
            courtIdField.setText(tableModel.getValueAt(selected, 1).toString());
            roomNumberField.setText(tableModel.getValueAt(selected, 2).toString());
            capacityField.setText(tableModel.getValueAt(selected, 3).toString());
            availabilityStatusField.setText(tableModel.getValueAt(selected, 4).toString());
        }
    }

    private void refreshTable() {
        try {
            tableModel.setRowCount(0);
            List<CourtRoom> list = courtRoomDAO.getAllCourtRooms();
            for (CourtRoom room : list) {
                tableModel.addRow(new Object[]{
                        room.getRoomId(),
                        room.getCourtId(),
                        room.getRoomNumber(),
                        room.getCapacity(),
                        room.getAvailabilityStatus()
                });
            }
        } catch (DatabaseException e) {
            showError(e);
        }
    }

    private void clearForm() {
        roomIdField.setText("");
        courtIdField.setText("");
        roomNumberField.setText("");
        capacityField.setText("");
        availabilityStatusField.setText("");
        table.clearSelection();
    }

    private void showError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
