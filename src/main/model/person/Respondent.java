package main.model.person;

import main.model.Person;
import java.util.Date;

public class Respondent extends Person {
    private Date responseSubmittedDate;
    private int legalRepresentativeId; // Refers to Lawyer(Lawyer_ID)

    // Constructor
    public Respondent() {
        super();
        this.responseSubmittedDate = responseSubmittedDate;
        this.legalRepresentativeId = legalRepresentativeId;
    }



    // Getters and Setters
    public Date getResponseSubmittedDate() {
        return responseSubmittedDate;
    }

    public void setResponseSubmittedDate(Date responseSubmittedDate) {
        this.responseSubmittedDate = responseSubmittedDate;
    }

    public int getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(int legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    @Override
    public String getRole() {
        return "Respondent";
    }
}
