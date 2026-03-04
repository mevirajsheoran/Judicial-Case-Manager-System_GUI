package main.model;


import java.sql.Date;

public class CaseDetails {
    private int caseId;
    private String caseNumber;
    private Date filingDate;
    private String caseType;
    private String status;
    private int categoryId;
    private int courtId;

    // ✅ Constructors
    public CaseDetails() {}

    public CaseDetails(int caseId, String caseNumber, Date filingDate, String caseType,
                       String status, int categoryId, int courtId) {
        this.caseId = caseId;
        this.caseNumber = caseNumber;
        this.filingDate = filingDate;
        this.caseType = caseType;
        this.status = status;
        this.categoryId = categoryId;
        this.courtId = courtId;
    }

    // ✅ Getters and Setters
    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    @Override
    public String toString() {
        return "CaseDetails{" +
                "caseId=" + caseId +
                ", caseNumber='" + caseNumber + '\'' +
                ", filingDate=" + filingDate +
                ", caseType='" + caseType + '\'' +
                ", status='" + status + '\'' +
                ", categoryId=" + categoryId +
                ", courtId=" + courtId +
                '}';
    }
}

