package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.Appeal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppealDAO {

    public void addAppeal(Appeal appeal) throws DatabaseException {
        String sql = "INSERT INTO Appeal (Appeal_ID, Case_ID, Filed_By, Date, Reason, Status, Appeal_Level) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appeal.getAppealId());
            stmt.setInt(2, appeal.getCaseId());
            stmt.setInt(3, appeal.getFiledBy());
            stmt.setDate(4, appeal.getDate());
            stmt.setString(5, appeal.getReason());
            stmt.setString(6, appeal.getStatus());
            stmt.setString(7, appeal.getAppealLevel());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error adding appeal: " + e.getMessage(), e);
        }
    }

    public List<Appeal> getAllAppeals() throws DatabaseException {
        List<Appeal> appeals = new ArrayList<>();
        String sql = "SELECT * FROM Appeal";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appeal appeal = new Appeal();
                appeal.setAppealId(rs.getInt("Appeal_ID"));
                appeal.setCaseId(rs.getInt("Case_ID"));
                appeal.setFiledBy(rs.getInt("Filed_By"));
                appeal.setDate(rs.getDate("Date"));
                appeal.setReason(rs.getString("Reason"));
                appeal.setStatus(rs.getString("Status"));
                appeal.setAppealLevel(rs.getString("Appeal_Level"));
                appeals.add(appeal);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching appeals: " + e.getMessage(), e);
        }

        return appeals;
    }

    public void updateAppeal(Appeal appeal) throws DatabaseException {
        String sql = "UPDATE Appeal SET Case_ID = ?, Filed_By = ?, Date = ?, Reason = ?, Status = ?, Appeal_Level = ? WHERE Appeal_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appeal.getCaseId());
            stmt.setInt(2, appeal.getFiledBy());
            stmt.setDate(3, appeal.getDate());
            stmt.setString(4, appeal.getReason());
            stmt.setString(5, appeal.getStatus());
            stmt.setString(6, appeal.getAppealLevel());
            stmt.setInt(7, appeal.getAppealId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating appeal: " + e.getMessage(), e);
        }
    }

    public void deleteAppeal(int appealId) throws DatabaseException {
        String sql = "DELETE FROM Appeal WHERE Appeal_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appealId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting appeal: " + e.getMessage(), e);
        }
    }
}
