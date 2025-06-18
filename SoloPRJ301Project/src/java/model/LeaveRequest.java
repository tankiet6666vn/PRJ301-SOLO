package model;

import java.sql.Timestamp;

public class LeaveRequest {

    private int requestID;
    private int userID;
    private String fullName; // Tên nhân viên
    private int leaveTypeID;
    private String leaveTypeName; // Tên loại nghỉ phép
    private String startDate;
    private String endDate;
    private String reason;
    private String status;
    private Timestamp requestDate;
    private int approvedBy;
    private String approvedByName; // Tên người duyệt (nếu có)

    public LeaveRequest() {
    }

    public LeaveRequest(int requestID, int userID, int leaveTypeID, String startDate, String endDate,
                        String reason, String status, Timestamp requestDate, int approvedBy) {
        this.requestID = requestID;
        this.userID = userID;
        this.leaveTypeID = leaveTypeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.requestDate = requestDate;
        this.approvedBy = approvedBy;
    }

    // Getters and setters

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedByName() {
        return approvedByName;
    }

    public void setApprovedByName(String approvedByName) {
        this.approvedByName = approvedByName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
