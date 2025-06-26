package model;

import java.util.Date;

public class ActivityLog {
    private int logID;
    private int userID;
    private String fullName; // tên người thực hiện hành động
    private String action;
    private String details;
    private Date timestamp;

    // Getters & Setters
    public int getLogID() { return logID; }
    public void setLogID(int logID) { this.logID = logID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
