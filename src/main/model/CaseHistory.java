package main.model;

import java.time.LocalDate;

public class CaseHistory {
    private int historyId;
    private int caseId;
    private LocalDate date;
    private String statusUpdate;
    private String notes;
    private int updatedBy; // Court_Staff Staff_ID

    public CaseHistory() {}

    public CaseHistory(int historyId, int caseId, LocalDate date, String statusUpdate, String notes, int updatedBy) {
        this.historyId = historyId;
        this.caseId = caseId;
        this.date = date;
        this.statusUpdate = statusUpdate;
        this.notes = notes;
        this.updatedBy = updatedBy;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(String statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "CaseHistory{" +
                "historyId=" + historyId +
                ", caseId=" + caseId +
                ", date=" + date +
                ", statusUpdate='" + statusUpdate + '\'' +
                ", notes='" + notes + '\'' +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
