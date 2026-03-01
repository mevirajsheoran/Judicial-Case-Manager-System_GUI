package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.Court;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourtDAO {

    // Create
    public void addCourt(Court court) throws DatabaseException {
        String query = "INSERT INTO Court (Court_ID, Court_Name, Location, Jurisdiction_Level) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, court.getCourtId());
            stmt.setString(2, court.getCourtName());
            stmt.setString(3, court.getLocation());
            stmt.setString(4, court.getJurisdictionLevel());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to add court: " + e.getMessage());
        }
    }

    // Read All
    public List<Court> getAllCourts() throws DatabaseException {
        List<Court> courts = new ArrayList<>();
        String query = "SELECT * FROM Court";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Court court = new Court();
                court.setCourtId(rs.getInt("Court_ID"));
                court.setCourtName(rs.getString("Court_Name"));
                court.setLocation(rs.getString("Location"));
                court.setJurisdictionLevel(rs.getString("Jurisdiction_Level"));
                courts.add(court);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch courts: " + e.getMessage());
        }

        return courts;
    }

    // Update
    public void updateCourt(Court court) throws DatabaseException {
        String query = "UPDATE Court SET Court_Name = ?, Location = ?, Jurisdiction_Level = ? WHERE Court_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, court.getCourtName());
            stmt.setString(2, court.getLocation());
            stmt.setString(3, court.getJurisdictionLevel());
            stmt.setInt(4, court.getCourtId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update court: " + e.getMessage());
        }
    }

    // Delete
    public void deleteCourt(int courtId) throws DatabaseException {
        String query = "DELETE FROM Court WHERE Court_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, courtId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete court: " + e.getMessage());
        }
    }
}
