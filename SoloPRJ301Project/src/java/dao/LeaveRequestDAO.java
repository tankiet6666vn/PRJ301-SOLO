package dao;

import java.sql.*;
import java.util.*;
import model.LeaveRequest;

public class LeaveRequestDAO extends BaseDao {

    // Gửi đơn nghỉ phép
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

    // Lấy danh sách đơn nghỉ phép của một user (dùng cho người dùng thường)
    public List<LeaveRequest> getByUser(int userId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, lt.TypeName FROM LeaveRequests lr " +
                     "JOIN LeaveTypes lt ON lr.LeaveTypeID = lt.LeaveTypeID " +
                     "WHERE lr.UserID = ? ORDER BY lr.RequestDate DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest r = new LeaveRequest();
                    r.setRequestID(rs.getInt("RequestID"));
                    r.setLeaveTypeName(rs.getString("TypeName"));
                    r.setStartDate(rs.getDate("StartDate").toString());
                    r.setEndDate(rs.getDate("EndDate").toString());
                    r.setReason(rs.getString("Reason"));
                    r.setStatus(rs.getString("Status"));
                    r.setRequestDate(rs.getTimestamp("RequestDate")); // ✅ đúng kiểu Timestamp

                    list.add(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi getByUser: " + e.getMessage());
        }
        return list;
    }

    // Lấy danh sách tất cả đơn nghỉ phép (dành cho admin)
    public List<LeaveRequest> getAllRequests() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, u.FullName, lt.TypeName FROM LeaveRequests lr " +
                     "JOIN Users u ON lr.UserID = u.UserID " +
                     "JOIN LeaveTypes lt ON lr.LeaveTypeID = lt.LeaveTypeID " +
                     "ORDER BY lr.RequestDate DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LeaveRequest r = new LeaveRequest();
                r.setRequestID(rs.getInt("RequestID"));
                r.setUserID(rs.getInt("UserID"));
                r.setFullName(rs.getString("FullName"));
                r.setLeaveTypeName(rs.getString("TypeName"));
                r.setStartDate(rs.getDate("StartDate").toString());
                r.setEndDate(rs.getDate("EndDate").toString());
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                r.setRequestDate(rs.getTimestamp("RequestDate"));


                list.add(r);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi getAllRequests: " + e.getMessage());
        }
        return list;
    }
    public boolean updateRequestStatus(int requestID, String status, int approverID) {
    String sql = "UPDATE LeaveRequests SET Status = ?, ApprovedBy = ? WHERE RequestID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, status);
        ps.setInt(2, approverID);
        ps.setInt(3, requestID);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("❌ Lỗi updateRequestStatus: " + e.getMessage());
        return false;
    }
}
public boolean updateStatus(int requestID, String status) {
    String sql = "UPDATE LeaveRequests SET Status = ? WHERE RequestID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, status);
        ps.setInt(2, requestID);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("❌ Lỗi updateStatus: " + e.getMessage());
        return false;
    }
}

}
