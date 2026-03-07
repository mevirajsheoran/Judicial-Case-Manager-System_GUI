//package main.gui.panels;
//
//import main.dao.PetitionerDAO;
//import main.exception.DatabaseException;
//import main.gui.components.ButtonFactory;
//import main.gui.components.FormBuilder;
//import main.gui.components.TableBuilder;
//import main.model.person.Petitioner;
//
//import javax.swing.*;
//import java.awt.*;
//import java.time.LocalDate;
//import java.util.List;
//
//public class PetitionerPanel extends JPanel {
//
//    private JTable table;
//    private JTextField personIdField;
//    private JTextField petitionDateField;
//    private JTextField legalRepField;
//
//    private PetitionerDAO petitionerDAO;
//
//    public PetitionerPanel() {
//        this.petitionerDAO = new PetitionerDAO();
//        setLayout(new BorderLayout());
//
//        add(buildFormPanel(), BorderLayout.NORTH);
//        add(buildButtonPanel(), BorderLayout.CENTER);
//        add(buildTablePanel(), BorderLayout.SOUTH);
//
//        loadPetitionerData();
//    }
//
//    private JPanel buildFormPanel() {
//        FormBuilder formBuilder = new FormBuilder(3, 2);
//
//        personIdField = new JTextField(15);
//        petitionDateField = new JTextField(15);
//        legalRepField = new JTextField(15);
//
//        formBuilder.addLabeledField("Person ID:", personIdField);
//        formBuilder.addLabeledField("Petition Filed Date (yyyy-mm-dd):", petitionDateField);
//        formBuilder.addLabeledField("Legal Representative (Lawyer ID):", legalRepField);
//
//        return formBuilder.getPanel();
//    }
//
//    private JPanel buildButtonPanel() {
//        JPanel panel = new JPanel(new FlowLayout());
//
//        JButton addBtn = ButtonFactory.createPrimaryButton("Add");
//        JButton updateBtn = ButtonFactory.createSecondaryButton("Update");
//        JButton deleteBtn = ButtonFactory.createDangerButton("Delete");
//        JButton clearBtn = ButtonFactory.createNeutralButton("Clear");
//
//        addBtn.addActionListener(e -> addPetitioner());
//        updateBtn.addActionListener(e -> updatePetitioner());
//        deleteBtn.addActionListener(e -> deletePetitioner());
//        clearBtn.addActionListener(e -> clearFields());
//
//        panel.add(addBtn);
//        panel.add(updateBtn);
//        panel.add(deleteBtn);
//        panel.add(clearBtn);
//
//        return panel;
//    }
//
//    private JScrollPane buildTablePanel() {
//        table = TableBuilder.createTable(new String[]{"Person ID", "Petition Date", "Legal Representative"});
//        table.getSelectionModel().addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) fillFieldsFromTable();
//        });
//        return new JScrollPane(table);
//    }
//
//    private void loadPetitionerData() {
//        try {
//            List<Petitioner> petitioners = petitionerDAO.getAllPetitioners();
//            String[][] data = new String[petitioners.size()][3];
//
//            for (int i = 0; i < petitioners.size(); i++) {
//                Petitioner p = petitioners.get(i);
//                data[i][0] = String.valueOf(p.getPersonId());
//                data[i][1] = p.getPetitionFiledDate().toString();
//                data[i][2] = String.valueOf(p.getLegalRepresentativeId());
//            }
//
//            TableBuilder.updateTable(table, data);
//        } catch (DatabaseException e) {
//            showError(e.getMessage());
//        }
//    }
//
//    private void addPetitioner() {
//        try {
//            Petitioner p = getPetitionerFromFields();
//            petitionerDAO.addPetitioner(p);
//            loadPetitionerData();
//            clearFields();
//        } catch (Exception e) {
//            showError("Error adding petitioner: " + e.getMessage());
//        }
//    }
//
//    private void updatePetitioner() {
//        try {
//            Petitioner p = getPetitionerFromFields();
//            petitionerDAO.updatePetitioner(p);
//            loadPetitionerData();
//            clearFields();
//        } catch (Exception e) {
//            showError("Error updating petitioner: " + e.getMessage());
//        }
//    }
//
//    private void deletePetitioner() {
//        try {
//            int personID = Integer.parseInt(personIdField.getText());
//            petitionerDAO.deletePetitioner(personID);
//            loadPetitionerData();
//            clearFields();
//        } catch (Exception e) {
//            showError("Error deleting petitioner: " + e.getMessage());
//        }
//    }
//
//    private void fillFieldsFromTable() {
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow >= 0) {
//            personIdField.setText(table.getValueAt(selectedRow, 0).toString());
//            petitionDateField.setText(table.getValueAt(selectedRow, 1).toString());
//            legalRepField.setText(table.getValueAt(selectedRow, 2).toString());
//        }
//    }
//
//    private Petitioner getPetitionerFromFields() {
//        Petitioner p = new Petitioner();
//        p.setPersonId(Integer.parseInt(personIdField.getText()));
//        p.setPetitionFiledDate(LocalDate.parse(petitionDateField.getText()));
//        p.setLegalRepresentativeId(Integer.parseInt(legalRepField.getText()));
//        return p;
//    }
//
//    private void clearFields() {
//        personIdField.setText("");
//        petitionDateField.setText("");
//        legalRepField.setText("");
//        table.clearSelection();
//    }
//
//    private void showError(String msg) {
//        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}
