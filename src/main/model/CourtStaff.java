package main.model;

public class CourtStaff {
    private int staffId;
    private String name;
    private String role;
    private String contact;
    private int courtId;

    // Constructor
    public CourtStaff() {
    }

    public CourtStaff(int staffId, String name, String role, String contact, int courtId) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.contact = contact;
        this.courtId = courtId;
    }

    // Getters and Setters
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    @Override
    public String toString() {
        return "CourtStaff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contact='" + contact + '\'' +
                ", courtId=" + courtId +
                '}';
    }
}
