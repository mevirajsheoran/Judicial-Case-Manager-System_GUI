package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.person.PoliceOfficer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceOfficerDAO {

    // Add new Police Officer
    public void addPoliceOfficer(PoliceOfficer officer) throws DatabaseException {
        String sql = "INSERT INTO PoliceOfficer (name, contact, address, role, police_station_id, badge_number, officer_rank, department) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, officer.getName());
            stmt.setString(2, officer.getContact());
            stmt.setString(3, officer.getAddress());
            stmt.setString(4, officer.getRole());
            stmt.setInt(5, officer.getPoliceStationId());
            stmt.setString(6, officer.getBadgeNumber());
            stmt.setString(7, officer.getOfficerRank());
            stmt.setString(8, officer.getDepartment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding police officer: " + e.getMessage());
        }
    }

    // Update Police Officer
    public void updatePoliceOfficer(PoliceOfficer officer) throws DatabaseException {
        String sql = "UPDATE PoliceOfficer SET name = ?, contact = ?, address = ?, police_station_id = ?, badge_number = ?, officer_rank = ?, department = ? " +
                "WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, officer.getName());
            stmt.setString(2, officer.getContact());
            stmt.setString(3, officer.getAddress());
            stmt.setInt(4, officer.getPoliceStationId());
            stmt.setString(5, officer.getBadgeNumber());
            stmt.setString(6, officer.getOfficerRank());
            stmt.setString(7, officer.getDepartment());
            stmt.setInt(8, officer.getPersonId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating police officer: " + e.getMessage());
        }
    }

    // Delete Police Officer
    public void deletePoliceOfficer(int personID) throws DatabaseException {
        String sql = "DELETE FROM PoliceOfficer WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting police officer: " + e.getMessage());
        }
    }

    // Get Police Officer by ID
    public PoliceOfficer getPoliceOfficerById(int personID) throws DatabaseException {
        String sql = "SELECT * FROM PoliceOfficer WHERE person_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, personID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PoliceOfficer officer = new PoliceOfficer();
                officer.setPersonId(rs.getInt("person_id"));
                officer.setName(rs.getString("name"));
                officer.setContact(rs.getString("contact"));
                officer.setAddress(rs.getString("address"));
                officer.setPoliceStationId(rs.getInt("police_station_id"));
                officer.setBadgeNumber(rs.getString("badge_number"));
                officer.setOfficerRank(rs.getString("officer_rank"));
                officer.setDepartment(rs.getString("department"));
                return officer;
            } else {
                throw new DatabaseException("Police officer not found with ID: " + personID);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving police officer: " + e.getMessage());
        }
    }

    // Get all Police Officers
    public List<PoliceOfficer> getAllPoliceOfficers() throws DatabaseException {
        String sql = "SELECT * FROM PoliceOfficer";
        List<PoliceOfficer> officers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PoliceOfficer officer = new PoliceOfficer();
                officer.setPersonId(rs.getInt("person_id"));
                officer.setName(rs.getString("name"));
                officer.setContact(rs.getString("contact"));
                officer.setAddress(rs.getString("address"));
                officer.setPoliceStationId(rs.getInt("police_station_id"));
                officer.setBadgeNumber(rs.getString("badge_number"));
                officer.setOfficerRank(rs.getString("officer_rank"));
                officer.setDepartment(rs.getString("department"));
                officers.add(officer);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error loading police officers: " + e.getMessage());
        }

        return officers;
    }
}
