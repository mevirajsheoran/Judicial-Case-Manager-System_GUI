package main.dao;

import main.db.DatabaseConnection;
import main.model.CaseDetails;
import main.exception.DatabaseException;
import main.exception.CaseAlreadyClosedException; // 🆕 Add the custom exception import

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {

    // Insert a new case into the database
    public void addCase(CaseDetails caseDetails) throws DatabaseException {
        String sql = "INSERT INTO Case_details (Case_ID, Case_Number, Filing_Date, Case_Type, Status, Category_ID, Court_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, caseDetails.getCaseId());
            stmt.setString(2, caseDetails.getCaseNumber());
            stmt.setDate(3, caseDetails.getFilingDate());
            stmt.setString(4, caseDetails.getCaseType());
            stmt.setString(5, caseDetails.getStatus());
            stmt.setInt(6, caseDetails.getCategoryId());
            stmt.setInt(7, caseDetails.getCourtId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error inserting case: " + e.getMessage());
        }
    }

    // Retrieve a case by its ID
    public CaseDetails getCaseById(int caseId) throws DatabaseException {
        String sql = "SELECT * FROM Case_details WHERE Case_ID = ?";
        CaseDetails caseDetails = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, caseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                caseDetails = new CaseDetails(
                        rs.getInt("Case_ID"),
                        rs.getString("Case_Number"),
                        rs.getDate("Filing_Date"),
                        rs.getString("Case_Type"),
                        rs.getString("Status"),
                        rs.getInt("Category_ID"),
                        rs.getInt("Court_ID")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving case: " + e.getMessage());
        }

        return caseDetails;
    }

    // Retrieve all cases
    public static List<CaseDetails> getAllCases() throws DatabaseException {
        String sql = "SELECT * FROM Case_details";
        List<CaseDetails> caseList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CaseDetails caseDetails = new CaseDetails(
                        rs.getInt("Case_ID"),
                        rs.getString("Case_Number"),
                        rs.getDate("Filing_Date"),
                        rs.getString("Case_Type"),
                        rs.getString("Status"),
                        rs.getInt("Category_ID"),
                        rs.getInt("Court_ID")
                );
                caseList.add(caseDetails);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching case list: " + e.getMessage());
        }

        return caseList;
    }

    // 🆕 Updated: Prevent updates to closed/disposed cases
    public void updateCase(CaseDetails caseDetails) throws DatabaseException, CaseAlreadyClosedException {
        String checkSql = "SELECT Status FROM Case_details WHERE Case_ID = ?";
        String updateSql = "UPDATE Case_details SET Case_Number = ?, Filing_Date = ?, Case_Type = ?, " +
                "Status = ?, Category_ID = ?, Court_ID = ? WHERE Case_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, caseDetails.getCaseId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("Status");
                if ("Closed".equalsIgnoreCase(status) || "Disposed".equalsIgnoreCase(status)) {
                    throw new CaseAlreadyClosedException("Cannot update case. The case is already marked as '" + status + "'.");
                }
            } else {
                throw new DatabaseException("Case with ID " + caseDetails.getCaseId() + " not found.");
            }

            // Proceed with update
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, caseDetails.getCaseNumber());
                updateStmt.setDate(2, caseDetails.getFilingDate());
                updateStmt.setString(3, caseDetails.getCaseType());
                updateStmt.setString(4, caseDetails.getStatus());
                updateStmt.setInt(5, caseDetails.getCategoryId());
                updateStmt.setInt(6, caseDetails.getCourtId());
                updateStmt.setInt(7, caseDetails.getCaseId());

                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error updating case: " + e.getMessage());
        }
    }

    // Delete a case by ID
    public boolean deleteCase(int caseId) throws DatabaseException {
        String sql = "DELETE FROM Case_details WHERE Case_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, caseId);
            int rowsAffected = stmt.executeUpdate(); // 🟢 capture affected rows
            return rowsAffected > 0; // 🟢 return true if something was deleted

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting case: " + e.getMessage());
        }
    }
}
