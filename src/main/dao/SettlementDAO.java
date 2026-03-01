package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.Settlement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettlementDAO {

    public void addSettlement(Settlement settlement) throws DatabaseException {
        String sql = "INSERT INTO Settlement (Settlement_ID, Case_ID, Terms, Date, Agreement_Signed) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, settlement.getSettlementId());
            stmt.setInt(2, settlement.getCaseId());
            stmt.setString(3, settlement.getTerms());
            stmt.setDate(4, settlement.getDate());
            stmt.setBoolean(5, settlement.isAgreementSigned());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding settlement: " + e.getMessage(), e);
        }
    }

    public List<Settlement> getAllSettlements() throws DatabaseException {
        List<Settlement> settlements = new ArrayList<>();
        String sql = "SELECT * FROM Settlement";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Settlement settlement = new Settlement();
                settlement.setSettlementId(rs.getInt("Settlement_ID"));
                settlement.setCaseId(rs.getInt("Case_ID"));
                settlement.setTerms(rs.getString("Terms"));
                settlement.setDate(rs.getDate("Date"));
                settlement.setAgreementSigned(rs.getBoolean("Agreement_Signed"));
                settlements.add(settlement);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving settlements: " + e.getMessage(), e);
        }

        return settlements;
    }

    public void updateSettlement(Settlement settlement) throws DatabaseException {
        String sql = "UPDATE Settlement SET Case_ID = ?, Terms = ?, Date = ?, Agreement_Signed = ? WHERE Settlement_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, settlement.getCaseId());
            stmt.setString(2, settlement.getTerms());
            stmt.setDate(3, settlement.getDate());
            stmt.setBoolean(4, settlement.isAgreementSigned());
            stmt.setInt(5, settlement.getSettlementId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating settlement: " + e.getMessage(), e);
        }
    }

    public boolean deleteSettlement(int settlementId) throws DatabaseException {
        String sql = "DELETE FROM Settlement WHERE Settlement_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, settlementId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting settlement: " + e.getMessage(), e);
        }
    }
}
