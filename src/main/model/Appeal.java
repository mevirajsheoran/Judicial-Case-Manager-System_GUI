package main.model;

import java.sql.Date;

public class Appeal {
    private int appealId;
    private int caseId;
    private int filedBy;
    private Date date;
    private String reason;
    private String status;
    private String appealLevel;

    // Getters and Setters
    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(int filedBy) {
        this.filedBy = filedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppealLevel() {
        return appealLevel;
    }

    public void setAppealLevel(String appealLevel) {
        this.appealLevel = appealLevel;
    }
}
