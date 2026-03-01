//package main.dao;
//
//import main.db.DatabaseConnection;
//import main.exception.DatabaseException;
//import main.model.Petitioner;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PetitionerDAO {
//
//    public void addPetitioner(Petitioner petitioner) throws DatabaseException {
//        String query = "INSERT INTO Petitioner (Person_ID, Petition_Filed_Date, Legal_Representative) VALUES (?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, petitioner.getPersonID());
//            stmt.setDate(2, Date.valueOf(petitioner.getPetitionFiledDate()));
//            stmt.setInt(3, petitioner.getLegalRepresentative());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new DatabaseException("Failed to add petitioner: " + e.getMessage(), e);
//        }
//    }
//
//    public void updatePetitioner(Petitioner petitioner) throws DatabaseException {
//        String query = "UPDATE Petitioner SET Petition_Filed_Date = ?, Legal_Representative = ? WHERE Person_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setDate(1, Date.valueOf(petitioner.getPetitionFiledDate()));
//            stmt.setInt(2, petitioner.getLegalRepresentative());
//            stmt.setInt(3, petitioner.getPersonID());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new DatabaseException("Failed to update petitioner: " + e.getMessage(), e);
//        }
//    }
//
//    public void deletePetitioner(int personID) throws DatabaseException {
//        String query = "DELETE FROM Petitioner WHERE Person_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, personID);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new DatabaseException("Failed to delete petitioner: " + e.getMessage(), e);
//        }
//    }
//
//    public Petitioner getPetitionerById(int personID) throws DatabaseException {
//        String query = "SELECT * FROM Petitioner WHERE Person_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setInt(1, personID);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Petitioner petitioner = new Petitioner();
//                petitioner.setPersonID(rs.getInt("Person_ID"));
//                petitioner.setPetitionFiledDate(rs.getDate("Petition_Filed_Date").toLocalDate());
//                petitioner.setLegalRepresentative(rs.getInt("Legal_Representative"));
//                return petitioner;
//            }
//
//            return null;
//        } catch (SQLException e) {
//            throw new DatabaseException("Failed to fetch petitioner: " + e.getMessage(), e);
//        }
//    }
//
//    public List<Petitioner> getAllPetitioners() throws DatabaseException {
//        List<Petitioner> list = new ArrayList<>();
//        String query = "SELECT * FROM Petitioner";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Petitioner petitioner = new Petitioner();
//                petitioner.setPersonID(rs.getInt("Person_ID"));
//                petitioner.setPetitionFiledDate(rs.getDate("Petition_Filed_Date").toLocalDate());
//                petitioner.setLegalRepresentative(rs.getInt("Legal_Representative"));
//                list.add(petitioner);
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("Failed to fetch petitioners: " + e.getMessage(), e);
//        }
//
//        return list;
//    }
//}
