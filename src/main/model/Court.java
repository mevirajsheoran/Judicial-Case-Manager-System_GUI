package main.model;

public class Court {
    private int courtId;
    private String courtName;
    private String location;
    private String jurisdictionLevel;

    // Constructors
    public Court() {
    }

    public Court(int courtId, String courtName, String location, String jurisdictionLevel) {
        this.courtId = courtId;
        this.courtName = courtName;
        this.location = location;
        this.jurisdictionLevel = jurisdictionLevel;
    }

    // Getters and Setters
    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJurisdictionLevel() {
        return jurisdictionLevel;
    }

    public void setJurisdictionLevel(String jurisdictionLevel) {
        this.jurisdictionLevel = jurisdictionLevel;
    }
}
