package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.BailRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BailRequestDAO {

    public void addBailRequest(BailRequest bail) throws DatabaseException {
        String sql = "INSERT INTO Bail (Bail_ID, Case_ID, Person_ID, Granted_Date, Conditions, Bail_Amount, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bail.getBailId());
            pstmt.setInt(2, bail.getCaseId());
            pstmt.setInt(3, bail.getPersonId());
            pstmt.setDate(4, Date.valueOf(bail.getGrantedDate()));
            pstmt.setString(5, bail.getConditions());
            pstmt.setBigDecimal(6, bail.getBailAmount());
            pstmt.setString(7, bail.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding bail request", e);
        }
    }

    public void updateBailRequest(BailRequest bail) throws DatabaseException {
        String sql = "UPDATE Bail SET Case_ID=?, Person_ID=?, Granted_Date=?, Conditions=?, Bail_Amount=?, Status=? WHERE Bail_ID=?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bail.getCaseId());
            pstmt.setInt(2, bail.getPersonId());
            pstmt.setDate(3, Date.valueOf(bail.getGrantedDate()));
            pstmt.setString(4, bail.getConditions());
            pstmt.setBigDecimal(5, bail.getBailAmount());
            pstmt.setString(6, bail.getStatus());
            pstmt.setInt(7, bail.getBailId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating bail request", e);
        }
    }

    public void deleteBailRequest(int bailId) throws DatabaseException {
        String sql = "DELETE FROM Bail WHERE Bail_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bailId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting bail request", e);
        }
    }

    public List<BailRequest> getAllBailRequests() throws DatabaseException {
        List<BailRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM Bail";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BailRequest bail = new BailRequest();
                bail.setBailId(rs.getInt("Bail_ID"));
                bail.setCaseId(rs.getInt("Case_ID"));
                bail.setPersonId(rs.getInt("Person_ID"));
                bail.setGrantedDate(rs.getDate("Granted_Date").toLocalDate());
                bail.setConditions(rs.getString("Conditions"));
                bail.setBailAmount(rs.getBigDecimal("Bail_Amount"));
                bail.setStatus(rs.getString("Status"));
                list.add(bail);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving bail requests", e);
        }

        return list;
    }

    public BailRequest getBailRequestById(int bailId) throws DatabaseException {
        String sql = "SELECT * FROM Bail WHERE Bail_ID = ?";
        BailRequest bail = null;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bailId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                bail = new BailRequest();
                bail.setBailId(rs.getInt("Bail_ID"));
                bail.setCaseId(rs.getInt("Case_ID"));
                bail.setPersonId(rs.getInt("Person_ID"));
                bail.setGrantedDate(rs.getDate("Granted_Date").toLocalDate());
                bail.setConditions(rs.getString("Conditions"));
                bail.setBailAmount(rs.getBigDecimal("Bail_Amount"));
                bail.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching bail request by ID", e);
        }

        return bail;
    }
}
