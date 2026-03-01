package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.person.Respondent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RespondentDAO {

    // Add a new Respondent
    public void addRespondent(Respondent respondent) throws DatabaseException {
        String sql = "INSERT INTO Respondent (name, contact, address, role, response_submitted_date, legal_representative_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, respondent.getName());
            stmt.setString(2, respondent.getContact());
            stmt.setString(3, respondent.getAddress());
            stmt.setString(4, respondent.getRole());
            stmt.setDate(5, new java.sql.Date(respondent.getResponseSubmittedDate().getTime()));
            stmt.setInt(6, respondent.getLegalRepresentativeId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding respondent: " + e.getMessage());
        }
    }

    // Update Respondent
    public void updateRespondent(Respondent respondent) throws DatabaseException {
        String sql = "UPDATE Respondent SET name = ?, contact = ?, address = ?, response_submitted_date = ?, legal_representative_id = ? " +
                "WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, respondent.getName());
            stmt.setString(2, respondent.getContact());
            stmt.setString(3, respondent.getAddress());
            stmt.setDate(4, new java.sql.Date(respondent.getResponseSubmittedDate().getTime()));
            stmt.setInt(5, respondent.getLegalRepresentativeId());
            stmt.setInt(6, respondent.getPersonId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating respondent: " + e.getMessage());
        }
    }

    // Delete Respondent
    public void deleteRespondent(int personID) throws DatabaseException {
        String sql = "DELETE FROM Respondent WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting respondent: " + e.getMessage());
        }
    }

    // Get Respondent by ID
    public Respondent getRespondentById(int personID) throws DatabaseException {
        String sql = "SELECT * FROM Respondent WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Respondent respondent = new Respondent();
                respondent.setPersonId(rs.getInt("person_id"));
                respondent.setName(rs.getString("name"));
                respondent.setContact(rs.getString("contact"));
                respondent.setAddress(rs.getString("address"));
                respondent.setResponseSubmittedDate(rs.getDate("response_submitted_date"));
                respondent.setLegalRepresentativeId(rs.getInt("legal_representative_id"));
                return respondent;
            } else {
                throw new DatabaseException("Respondent not found with ID: " + personID);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving respondent: " + e.getMessage());
        }
    }

    // Get all Respondents
    public List<Respondent> getAllRespondents() throws DatabaseException {
        String sql = "SELECT * FROM Respondent";
        List<Respondent> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Respondent respondent = new Respondent();
                respondent.setPersonId(rs.getInt("person_id"));
                respondent.setName(rs.getString("name"));
                respondent.setContact(rs.getString("contact"));
                respondent.setAddress(rs.getString("address"));
                respondent.setResponseSubmittedDate(rs.getDate("response_submitted_date"));
                respondent.setLegalRepresentativeId(rs.getInt("legal_representative_id"));
                list.add(respondent);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error loading respondents: " + e.getMessage());
        }

        return list;
    }
}
