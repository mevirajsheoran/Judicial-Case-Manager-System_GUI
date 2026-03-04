package main.model;

public class Lawyer {
    private int lawyerId;
    private String name;
    private String contact;
    private String specialization;
    private String barRegistrationNumber;
    private int experienceYears;

    // Constructor
    public Lawyer(int lawyerId, String name, String contact, String specialization,
                  String barRegistrationNumber, int experienceYears) {
        this.lawyerId = lawyerId;
        this.name = name;
        this.contact = contact;
        this.specialization = specialization;
        this.barRegistrationNumber = barRegistrationNumber;
        this.experienceYears = experienceYears;
    }

    // Getters and Setters
    public int getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(int lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBarRegistrationNumber() {
        return barRegistrationNumber;
    }

    public void setBarRegistrationNumber(String barRegistrationNumber) {
        this.barRegistrationNumber = barRegistrationNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}
