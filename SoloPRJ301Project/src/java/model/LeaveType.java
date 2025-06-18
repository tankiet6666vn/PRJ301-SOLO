package model;

public class LeaveType {
    private int leaveTypeID;
    private String typeName;

    public LeaveType() {}

    public LeaveType(int leaveTypeID, String typeName) {
        this.leaveTypeID = leaveTypeID;
        this.typeName = typeName;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
