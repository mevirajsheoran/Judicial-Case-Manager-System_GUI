package main.model.person;

import main.model.Person;

public class PoliceOfficer extends Person {
    private int policeStationId;
    private String badgeNumber;
    private String officerRank;
    private String department;

    // Constructor
    public PoliceOfficer() {
        super();
        this.policeStationId = policeStationId;
        this.badgeNumber = badgeNumber;
        this.officerRank = officerRank;
        this.department = department;
    }

    // Getters and Setters
    public int getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(int policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getOfficerRank() {
        return officerRank;
    }

    public void setOfficerRank(String officerRank) {
        this.officerRank = officerRank;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Implementing abstract method from Person
    @Override
    public String getRole() {
        return "Police Officer";
    }
}
