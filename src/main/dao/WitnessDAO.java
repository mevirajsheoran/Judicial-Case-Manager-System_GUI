package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.person.Witness;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WitnessDAO {

    // Add a new Witness
    public void addWitness(Witness witness) throws DatabaseException {
        String sql = "INSERT INTO Witness (name, contact, address, role, testimony_date, type_of_witness) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, witness.getName());
            stmt.setString(2, witness.getContact());
            stmt.setString(3, witness.getAddress());
            stmt.setString(4, witness.getRole());
            stmt.setDate(5, new java.sql.Date(witness.getTestimonyDate().getTime()));
            stmt.setString(6, witness.getTypeOfWitness());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding witness: " + e.getMessage());
        }
    }

    // Update Witness
    public void updateWitness(Witness witness) throws DatabaseException {
        String sql = "UPDATE Witness SET name = ?, contact = ?, address = ?, testimony_date = ?, type_of_witness = ? " +
                     "WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, witness.getName());
            stmt.setString(2, witness.getContact());
            stmt.setString(3, witness.getAddress());
            stmt.setDate(4, new java.sql.Date(witness.getTestimonyDate().getTime()));
            stmt.setString(5, witness.getTypeOfWitness());
            stmt.setInt(6, witness.getPersonId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating witness: " + e.getMessage());
        }
    }

    // Delete Witness
    public void deleteWitness(int personID) throws DatabaseException {
        String sql = "DELETE FROM Witness WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting witness: " + e.getMessage());
        }
    }

    // Get Witness by ID
    public Witness getWitnessById(int personID) throws DatabaseException {
        String sql = "SELECT * FROM Witness WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Witness witness = new Witness();
                witness.setPersonId(rs.getInt("person_id"));
                witness.setName(rs.getString("name"));
                witness.setContact(rs.getString("contact"));
                witness.setAddress(rs.getString("address"));
                witness.setTestimonyDate(rs.getDate("testimony_date"));
                witness.setTypeOfWitness(rs.getString("type_of_witness"));
                return witness;
            } else {
                throw new DatabaseException("Witness not found with ID: " + personID);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving witness: " + e.getMessage());
        }
    }

    // Get all Witnesses
    public List<Witness> getAllWitnesses() throws DatabaseException {
        String sql = "SELECT * FROM Witness";
        List<Witness> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Witness witness = new Witness();
                witness.setPersonId(rs.getInt("person_id"));
                witness.setName(rs.getString("name"));
                witness.setContact(rs.getString("contact"));
                witness.setAddress(rs.getString("address"));
                witness.setTestimonyDate(rs.getDate("testimony_date"));
                witness.setTypeOfWitness(rs.getString("type_of_witness"));
                list.add(witness);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error loading witnesses: " + e.getMessage());
        }

        return list;
    }
}
