package main.model;

public class CourtRoom {
    private int roomId;
    private int courtId;
    private String roomNumber;
    private int capacity;
    private String availabilityStatus;

    // Constructors
    public CourtRoom() {}

    public CourtRoom(int roomId, int courtId, String roomNumber, int capacity, String availabilityStatus) {
        this.roomId = roomId;
        this.courtId = courtId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
