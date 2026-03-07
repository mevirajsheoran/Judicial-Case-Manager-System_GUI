package main.gui.panels;

import main.dao.PersonDAO;
import main.exception.DatabaseException;
import main.model.Person;
import main.gui.components.ButtonFactory;
import main.gui.components.FormBuilder;
import main.gui.components.TableBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PersonManagementPanel extends JPanel {

    private PersonDAO personDAO;
    private JTable personTable;
    private TableBuilder tableBuilder;
    private FormBuilder formBuilder;

    public PersonManagementPanel() throws SQLException {
        this.personDAO = new PersonDAO();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Person Management"));

        initComponents();
        loadPersonData();
    }

    private void initComponents() {
        // Table Setup
        String[] columns = {"ID", "Name", "DOB", "Contact", "Address", "National ID"};
        tableBuilder = new TableBuilder(columns);
        personTable = tableBuilder.getTable();

        JScrollPane tableScrollPane = new JScrollPane(personTable);

        // Form Setup
        formBuilder = new FormBuilder();
        formBuilder.addField("Person_ID", "ID");
        formBuilder.addField("Name", "Name");
        formBuilder.addField("Date_of_Birth", "Date of Birth (YYYY-MM-DD)");
        formBuilder.addField("Contact", "Contact");
        formBuilder.addField("Address", "Address");
        formBuilder.addField("National_ID", "National ID");

        JPanel formPanel = formBuilder.getFormPanel();

        // Buttons
        JButton addButton = ButtonFactory.createPrimaryButton("Add");
        JButton editButton = ButtonFactory.createSecondaryButton("Edit");
        JButton deleteButton = ButtonFactory.createDangerButton("Delete");
        JButton clearButton = ButtonFactory.createNeutralButton("Clear");

        addButton.addActionListener(e -> addPerson());
        editButton.addActionListener(e -> updatePerson());
        deleteButton.addActionListener(e -> deletePerson());
        clearButton.addActionListener(e -> formBuilder.clearFields());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Layout
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(tableScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Table click to fill form
        personTable.getSelectionModel().addListSelectionListener(e -> fillFormFromSelectedRow());
    }

    private void loadPersonData() {
        try {
            List<Person> personList = personDAO.getAllPersons();
            tableBuilder.clearTable();
            for (Person p : personList) {
                tableBuilder.addRow(new Object[]{
                        p.getPersonId(), p.getName(), p.getDateOfBirth(),
                        p.getContact(), p.getAddress(), p.getNationalId()
                });
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Failed to load persons: " + e.getMessage());
        }
    }

    private void addPerson() {
        try {
            Person person = getPersonFromForm();
            boolean success = personDAO.addPerson(person); // ✅ Ensure this method returns boolean
            if (success) {
                JOptionPane.showMessageDialog(this, "Person added successfully.");
                loadPersonData();
                formBuilder.clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add person to the database.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding person: " + e.getMessage());
        }
    }

    private void updatePerson() {
        try {
            Person person = getPersonFromForm();
            boolean success = personDAO.updatePerson(person);
            if (success) {
                JOptionPane.showMessageDialog(this, "Person updated successfully.");
                loadPersonData();
                formBuilder.clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update person.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating person: " + e.getMessage());
        }
    }

    private void deletePerson() {
        int selectedRow = personTable.getSelectedRow();
        if (selectedRow >= 0) {
            int personId = (int) personTable.getValueAt(selectedRow, 0);
            try {
                boolean success = personDAO.deletePerson(personId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Person deleted successfully.");
                    loadPersonData();
                    formBuilder.clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete person.");
                }
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, "Error deleting person: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a person to delete.");
        }
    }

    private void fillFormFromSelectedRow() {
        int row = personTable.getSelectedRow();
        if (row >= 0) {
            formBuilder.setFieldValue("Person_ID", String.valueOf(personTable.getValueAt(row, 0)));
            formBuilder.setFieldValue("Name", String.valueOf(personTable.getValueAt(row, 1)));
            formBuilder.setFieldValue("Date_of_Birth", String.valueOf(personTable.getValueAt(row, 2)));
            formBuilder.setFieldValue("Contact", String.valueOf(personTable.getValueAt(row, 3)));
            formBuilder.setFieldValue("Address", String.valueOf(personTable.getValueAt(row, 4)));
            formBuilder.setFieldValue("National_ID", String.valueOf(personTable.getValueAt(row, 5)));
        }
    }

    private Person getPersonFromForm() throws Exception {
        Person person = new Person() {
            @Override
            public String getRole() {
                return "Person";
            }
        };

        try {
            person.setPersonId(Integer.parseInt(formBuilder.getFieldValue("Person_ID")));
            person.setName(formBuilder.getFieldValue("Name"));
            person.setDateOfBirth(Date.valueOf(formBuilder.getFieldValue("Date_of_Birth")));
            person.setContact(formBuilder.getFieldValue("Contact"));
            person.setAddress(formBuilder.getFieldValue("Address"));
            person.setNationalId(formBuilder.getFieldValue("National_ID"));
        } catch (Exception e) {
            throw new Exception("Invalid form input: " + e.getMessage());
        }

        return person;
    }
}
