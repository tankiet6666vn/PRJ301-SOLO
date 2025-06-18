package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaveRequestDAO extends BaseDao {

    public boolean insertLeaveRequest(int userID, int leaveTypeID, String startDate, String endDate, String reason) {
    String sql = "INSERT INTO LeaveRequests (UserID, LeaveTypeID, StartDate, EndDate, Reason, Status) " +
                 "VALUES (?, ?, ?, ?, ?, 'Pending')";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, userID);
        ps.setInt(2, leaveTypeID);
        ps.setString(3, startDate);
        ps.setString(4, endDate);
        ps.setString(5, reason);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("❌ Lỗi insertLeaveRequest: " + e.getMessage());
        return false;
    }
}

}
