package main.model.person;

import main.model.Person;
import java.util.Date;

public class Witness extends Person {
    private Date testimonyDate;
    private String typeOfWitness;

    // Constructor
    public Witness() {
        super();
        this.testimonyDate = testimonyDate;
        this.typeOfWitness = typeOfWitness;
    }

    // Getters and Setters
    public Date getTestimonyDate() {
        return testimonyDate;
    }

    public void setTestimonyDate(Date testimonyDate) {
        this.testimonyDate = testimonyDate;
    }

    public String getTypeOfWitness() {
        return typeOfWitness;
    }

    public void setTypeOfWitness(String typeOfWitness) {
        this.typeOfWitness = typeOfWitness;
    }

    @Override
    public String getRole() {
        return "Witness";
    }
}
