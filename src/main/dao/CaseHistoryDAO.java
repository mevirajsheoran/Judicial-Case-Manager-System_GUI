package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.CaseHistory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CaseHistoryDAO {

    public void addCaseHistory(CaseHistory history) throws DatabaseException {
        String sql = "INSERT INTO Case_History (History_ID, Case_ID, Date, Status_Update, Notes, Updated_By) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, history.getHistoryId());
            stmt.setInt(2, history.getCaseId());
            stmt.setDate(3, Date.valueOf(history.getDate()));
            stmt.setString(4, history.getStatusUpdate());
            stmt.setString(5, history.getNotes());
            stmt.setInt(6, history.getUpdatedBy());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding case history: " + e.getMessage(), e);
        }
    }

    public List<CaseHistory> getAllCaseHistories() throws DatabaseException {
        List<CaseHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM Case_History";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CaseHistory history = new CaseHistory();
                history.setHistoryId(rs.getInt("History_ID"));
                history.setCaseId(rs.getInt("Case_ID"));
                history.setDate(rs.getDate("Date").toLocalDate());
                history.setStatusUpdate(rs.getString("Status_Update"));
                history.setNotes(rs.getString("Notes"));
                history.setUpdatedBy(rs.getInt("Updated_By"));
                list.add(history);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving case histories: " + e.getMessage(), e);
        }

        return list;
    }

    public void updateCaseHistory(CaseHistory history) throws DatabaseException {
        String sql = "UPDATE Case_History SET Case_ID=?, Date=?, Status_Update=?, Notes=?, Updated_By=? WHERE History_ID=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, history.getCaseId());
            stmt.setDate(2, Date.valueOf(history.getDate()));
            stmt.setString(3, history.getStatusUpdate());
            stmt.setString(4, history.getNotes());
            stmt.setInt(5, history.getUpdatedBy());
            stmt.setInt(6, history.getHistoryId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating case history: " + e.getMessage(), e);
        }
    }

    public void deleteCaseHistory(int historyId) throws DatabaseException {
        String sql = "DELETE FROM Case_History WHERE History_ID=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, historyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting case history: " + e.getMessage(), e);
        }
    }
}
