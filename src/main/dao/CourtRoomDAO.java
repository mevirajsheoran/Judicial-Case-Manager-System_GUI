package main.dao;

import main.db.DatabaseConnection;
import main.exception.DatabaseException;
import main.model.CourtRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourtRoomDAO {

    public void addCourtRoom(CourtRoom room) throws DatabaseException {
        String sql = "INSERT INTO Court_Room (Room_ID, Court_ID, Room_Number, Capacity, Availability_Status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getRoomId());
            stmt.setInt(2, room.getCourtId());
            stmt.setString(3, room.getRoomNumber());
            stmt.setInt(4, room.getCapacity());
            stmt.setString(5, room.getAvailabilityStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to add courtroom", e);
        }
    }

    public List<CourtRoom> getAllCourtRooms() throws DatabaseException {
        List<CourtRoom> list = new ArrayList<>();
        String sql = "SELECT * FROM Court_Room";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CourtRoom room = new CourtRoom();
                room.setRoomId(rs.getInt("Room_ID"));
                room.setCourtId(rs.getInt("Court_ID"));
                room.setRoomNumber(rs.getString("Room_Number"));
                room.setCapacity(rs.getInt("Capacity"));
                room.setAvailabilityStatus(rs.getString("Availability_Status"));
                list.add(room);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch courtrooms", e);
        }

        return list;
    }

    public void updateCourtRoom(CourtRoom room) throws DatabaseException {
        String sql = "UPDATE Court_Room SET Court_ID = ?, Room_Number = ?, Capacity = ?, Availability_Status = ? WHERE Room_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getCourtId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setInt(3, room.getCapacity());
            stmt.setString(4, room.getAvailabilityStatus());
            stmt.setInt(5, room.getRoomId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to update courtroom", e);
        }
    }

    public void deleteCourtRoom(int roomId) throws DatabaseException {
        String sql = "DELETE FROM Court_Room WHERE Room_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete courtroom", e);
        }
    }
}
