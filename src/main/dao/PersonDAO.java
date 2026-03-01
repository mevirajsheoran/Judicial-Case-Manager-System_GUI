package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    // Add a new person
    public boolean addPerson(Person person) throws DatabaseException {
        String sql = "INSERT INTO Person (Person_ID, Name, Date_of_Birth, Contact, Address, National_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, person.getPersonId());
            stmt.setString(2, person.getName());
            stmt.setDate(3, person.getDateOfBirth());
            stmt.setString(4, person.getContact());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getNationalId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // ✅ Return true if the insert is successful

        } catch (SQLException e) {
            throw new DatabaseException("Error adding person: " + e.getMessage(), e);
        }
    }


    // Get all persons
    public List<Person> getAllPersons() throws DatabaseException {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM Person";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Person p = new Person() {
                    @Override
                    public String getRole() {
                        return "Person";
                    }
                };
                p.setPersonId(rs.getInt("Person_ID"));
                p.setName(rs.getString("Name"));
                p.setDateOfBirth(rs.getDate("Date_of_Birth"));
                p.setContact(rs.getString("Contact"));
                p.setAddress(rs.getString("Address"));
                p.setNationalId(rs.getString("National_ID"));

                persons.add(p);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching persons: " + e.getMessage());
        }

        return persons;
    }

    // Get person by ID
    public Person getPersonById(int personId) throws DatabaseException {
        String sql = "SELECT * FROM Person WHERE Person_ID = ?";
        Person person = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    person = new Person() {
                        @Override
                        public String getRole() {
                            return "Person";
                        }
                    };
                    person.setPersonId(rs.getInt("Person_ID"));
                    person.setName(rs.getString("Name"));
                    person.setDateOfBirth(rs.getDate("Date_of_Birth"));
                    person.setContact(rs.getString("Contact"));
                    person.setAddress(rs.getString("Address"));
                    person.setNationalId(rs.getString("National_ID"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching person: " + e.getMessage());
        }

        return person;
    }

    // Update person
    public boolean updatePerson(Person person) throws DatabaseException {
        String sql = "UPDATE Person SET Name = ?, Date_of_Birth = ?, Contact = ?, Address = ?, National_ID = ? WHERE Person_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, person.getName());
            stmt.setDate(2, person.getDateOfBirth());
            stmt.setString(3, person.getContact());
            stmt.setString(4, person.getAddress());
            stmt.setString(5, person.getNationalId());
            stmt.setInt(6, person.getPersonId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // ✅ Return true if the update is successful

        } catch (SQLException e) {
            throw new DatabaseException("Error updating person: " + e.getMessage(), e);
        }
    }


    // Delete person
    public boolean deletePerson(int personId) throws DatabaseException {
        String sql = "DELETE FROM Person WHERE Person_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting person: " + e.getMessage());
        }
    }
}
