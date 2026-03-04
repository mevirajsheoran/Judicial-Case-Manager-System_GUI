package main.model;

public class Judge {
    private int judgeId;
    private String name;
    private String designation;
    private int experienceYears;
    private int courtId;

    // Constructor
    public Judge(int judgeId, String name, String designation, int experienceYears, int courtId) {
        this.judgeId = judgeId;
        this.name = name;
        this.designation = designation;
        this.experienceYears = experienceYears;
        this.courtId = courtId;
    }

    // Getters and Setters
    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }
}
