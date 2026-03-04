package main.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BailRequest {
    private int bailId;
    private int caseId;
    private int personId;
    private LocalDate grantedDate;
    private String conditions;
    private BigDecimal bailAmount;
    private String status;

    public BailRequest() {
    }

    public BailRequest(int bailId, int caseId, int personId, LocalDate grantedDate, String conditions, BigDecimal bailAmount, String status) {
        this.bailId = bailId;
        this.caseId = caseId;
        this.personId = personId;
        this.grantedDate = grantedDate;
        this.conditions = conditions;
        this.bailAmount = bailAmount;
        this.status = status;
    }

    public int getBailId() {
        return bailId;
    }

    public void setBailId(int bailId) {
        this.bailId = bailId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDate getGrantedDate() {
        return grantedDate;
    }

    public void setGrantedDate(LocalDate grantedDate) {
        this.grantedDate = grantedDate;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public BigDecimal getBailAmount() {
        return bailAmount;
    }

    public void setBailAmount(BigDecimal bailAmount) {
        this.bailAmount = bailAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
