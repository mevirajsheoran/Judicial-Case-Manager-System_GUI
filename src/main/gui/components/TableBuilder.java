package main.gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class TableBuilder {
    private JTable table;
    private DefaultTableModel model;

    // Constructor: Initializes with column names
    public TableBuilder(String[] columnNames) {
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
    }

    // ======= STATIC UTILITIES =======

    // Build a simple non-editable table from column names
    public static JTable buildTable(String[] columnNames) {
        DefaultTableModel staticModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable staticTable = new JTable(staticModel);
        staticTable.setAutoCreateRowSorter(true);
        return staticTable;
    }

    // Build a non-editable JTable from an existing DefaultTableModel
    public static JTable buildTable(DefaultTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setDefaultEditor(Object.class, null); // Disable editing
        return table;
    }

    public static JTable createTable(DefaultTableModel tableModel) {
        return buildTable(tableModel);
    }

    // Update an existing table with 2D String data
    public static void updateTable(JTable table, String[][] data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (String[] row : data) {
            model.addRow(row);
        }
    }



    // ======= INSTANCE METHODS =======

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return new JScrollPane(table);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public void clear() {
        clearTable();
        table.clearSelection();
    }

    public void clearRows() {
        model.setRowCount(0);
    }

    // Optional: Add typed data row (custom usage)
    public void addRow(int historyId, int caseId, LocalDate date, String statusUpdate, String notes, int updatedBy) {
        addRow(new Object[]{
                historyId,
                caseId,
                date.toString(),
                statusUpdate,
                notes,
                updatedBy
        });
    }
}
