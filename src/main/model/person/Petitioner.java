package main.model.person;

import main.model.Person;

import java.time.LocalDate;

public class Petitioner extends Person {
    private LocalDate petitionFiledDate;
    private int legalRepresentativeId; // Refers to Lawyer(Lawyer_ID)

    // Constructor
    public Petitioner() {
        super();
        this.petitionFiledDate = petitionFiledDate;
        this.legalRepresentativeId = legalRepresentativeId;
    }



    // Getters and Setters
    public LocalDate getPetitionFiledDate() {
        return petitionFiledDate;
    }

    public void setPetitionFiledDate(LocalDate petitionFiledDate) {
        this.petitionFiledDate = petitionFiledDate;
    }

    public int getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(int legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    @Override
    public String getRole() {
        return "Petitioner";
    }



}
