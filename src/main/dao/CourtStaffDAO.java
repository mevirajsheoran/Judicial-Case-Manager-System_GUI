package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.CourtStaff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourtStaffDAO {

    // Add CourtStaff
    public void addCourtStaff(CourtStaff staff) throws DatabaseException {
        String sql = "INSERT INTO Court_Staff (Staff_ID, Name, Role, Contact, Court_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staff.getStaffId());
            stmt.setString(2, staff.getName());
            stmt.setString(3, staff.getRole());
            stmt.setString(4, staff.getContact());
            stmt.setInt(5, staff.getCourtId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding court staff: " + e.getMessage());
        }
    }

    // Update CourtStaff
    public void updateCourtStaff(CourtStaff staff) throws DatabaseException {
        String sql = "UPDATE Court_Staff SET Name = ?, Role = ?, Contact = ?, Court_ID = ? WHERE Staff_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getContact());
            stmt.setInt(4, staff.getCourtId());
            stmt.setInt(5, staff.getStaffId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error updating court staff: " + e.getMessage());
        }
    }

    // Delete CourtStaff
    public void deleteCourtStaff(int staffId) throws DatabaseException {
        String sql = "DELETE FROM Court_Staff WHERE Staff_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting court staff: " + e.getMessage());
        }
    }

    // Get CourtStaff by ID
    public CourtStaff getCourtStaffById(int staffId) throws DatabaseException {
        String sql = "SELECT * FROM Court_Staff WHERE Staff_ID = ?";
        CourtStaff staff = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new CourtStaff();
                staff.setStaffId(rs.getInt("Staff_ID"));
                staff.setName(rs.getString("Name"));
                staff.setRole(rs.getString("Role"));
                staff.setContact(rs.getString("Contact"));
                staff.setCourtId(rs.getInt("Court_ID"));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching court staff by ID: " + e.getMessage());
        }

        return staff;
    }

    // Get All CourtStaff
    public List<CourtStaff> getAllCourtStaff() throws DatabaseException {
        String sql = "SELECT * FROM Court_Staff";
        List<CourtStaff> staffList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CourtStaff staff = new CourtStaff();
                staff.setStaffId(rs.getInt("Staff_ID"));
                staff.setName(rs.getString("Name"));
                staff.setRole(rs.getString("Role"));
                staff.setContact(rs.getString("Contact"));
                staff.setCourtId(rs.getInt("Court_ID"));
                staffList.add(staff);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching court staff list: " + e.getMessage());
        }

        return staffList;
    }
}
