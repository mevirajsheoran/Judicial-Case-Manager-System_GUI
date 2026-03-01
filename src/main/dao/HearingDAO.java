package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.exception.DuplicateHearingException;
import main.model.Hearing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HearingDAO {

    public void addHearing(Hearing hearing) throws DatabaseException, DuplicateHearingException {
        // Check for duplicate before inserting
        if (isDuplicateHearing(hearing)) {
            throw new DuplicateHearingException("A hearing is already scheduled at this date and time for the same case.");
        }

        String query = "INSERT INTO Hearing (Hearing_ID, Case_ID, Judge_ID, Date, Time, Description, Outcome) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hearing.getHearingId());
            stmt.setInt(2, hearing.getCaseId());
            stmt.setInt(3, hearing.getJudgeId());
            stmt.setDate(4, hearing.getDate());
            stmt.setTime(5, hearing.getTime());
            stmt.setString(6, hearing.getDescription());
            stmt.setString(7, hearing.getOutcome());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error adding hearing: " + e.getMessage(), e);
        }
    }

    // Duplicate checker method
    private boolean isDuplicateHearing(Hearing hearing) throws DatabaseException {
        String query = "SELECT COUNT(*) FROM Hearing WHERE Case_ID = ? AND Date = ? AND Time = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hearing.getCaseId());
            stmt.setDate(2, hearing.getDate());
            stmt.setTime(3, hearing.getTime());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error checking duplicate hearing: " + e.getMessage(), e);
        }

        return false;
    }

    public List<Hearing> getAllHearings() throws DatabaseException {
        List<Hearing> hearings = new ArrayList<>();
        String query = "SELECT * FROM Hearing";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Hearing hearing = new Hearing();
                hearing.setHearingId(rs.getInt("Hearing_ID"));
                hearing.setCaseId(rs.getInt("Case_ID"));
                hearing.setJudgeId(rs.getInt("Judge_ID"));
                hearing.setDate(rs.getDate("Date"));
                hearing.setTime(rs.getTime("Time"));
                hearing.setDescription(rs.getString("Description"));
                hearing.setOutcome(rs.getString("Outcome"));
                hearings.add(hearing);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching hearings: " + e.getMessage(), e);
        }

        return hearings;
    }

    public void updateHearing(Hearing hearing) throws DatabaseException {
        String query = "UPDATE Hearing SET Case_ID = ?, Judge_ID = ?, Date = ?, Time = ?, Description = ?, Outcome = ? WHERE Hearing_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hearing.getCaseId());
            stmt.setInt(2, hearing.getJudgeId());
            stmt.setDate(3, hearing.getDate());
            stmt.setTime(4, hearing.getTime());
            stmt.setString(5, hearing.getDescription());
            stmt.setString(6, hearing.getOutcome());
            stmt.setInt(7, hearing.getHearingId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating hearing: " + e.getMessage(), e);
        }
    }

    public boolean deleteHearing(int hearingId) throws DatabaseException {
        String query = "DELETE FROM Hearing WHERE Hearing_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hearingId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting hearing: " + e.getMessage(), e);
        }
    }
}
