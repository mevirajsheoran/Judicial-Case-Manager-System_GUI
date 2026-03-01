package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.Evidence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvidenceDAO {

    public void addEvidence(Evidence evidence) throws DatabaseException {
        String sql = "INSERT INTO Evidence (Evidence_ID, Case_ID, Type, Description, Collected_By, Submission_Date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, evidence.getEvidenceId());
            stmt.setInt(2, evidence.getCaseId());
            stmt.setString(3, evidence.getType());
            stmt.setString(4, evidence.getDescription());
            stmt.setInt(5, evidence.getCollectedBy());
            stmt.setDate(6, evidence.getSubmissionDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding evidence", e);
        }
    }

    public List<Evidence> getAllEvidence() throws DatabaseException {
        List<Evidence> list = new ArrayList<>();
        String sql = "SELECT * FROM Evidence";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evidence e = new Evidence();
                e.setEvidenceId(rs.getInt("Evidence_ID"));
                e.setCaseId(rs.getInt("Case_ID"));
                e.setType(rs.getString("Type"));
                e.setDescription(rs.getString("Description"));
                e.setCollectedBy(rs.getInt("Collected_By"));
                e.setSubmissionDate(rs.getDate("Submission_Date"));

                list.add(e);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving evidence", e);
        }

        return list;
    }

    public void updateEvidence(Evidence evidence) throws DatabaseException {
        String sql = "UPDATE Evidence SET Case_ID = ?, Type = ?, Description = ?, Collected_By = ?, Submission_Date = ? WHERE Evidence_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, evidence.getCaseId());
            stmt.setString(2, evidence.getType());
            stmt.setString(3, evidence.getDescription());
            stmt.setInt(4, evidence.getCollectedBy());
            stmt.setDate(5, evidence.getSubmissionDate());
            stmt.setInt(6, evidence.getEvidenceId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating evidence", e);
        }
    }

    public boolean deleteEvidence(int evidenceId) throws DatabaseException {
        String sql = "DELETE FROM Evidence WHERE Evidence_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, evidenceId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting evidence", e);
        }
    }
}
