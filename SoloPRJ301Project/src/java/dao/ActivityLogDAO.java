package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ActivityLog;

public class ActivityLogDAO extends BaseDao {

    public List<ActivityLog> getAllLogs() {
        List<ActivityLog> list = new ArrayList<>();
        String sql = "SELECT l.*, u.FullName FROM ActivityLog l " +
                     "JOIN Users u ON l.UserID = u.UserID " +
                     "ORDER BY l.Timestamp DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ActivityLog log = new ActivityLog();
                log.setLogID(rs.getInt("LogID"));
                log.setUserID(rs.getInt("UserID"));
                log.setFullName(rs.getString("FullName"));
                log.setAction(rs.getString("Action"));
                log.setDetails(rs.getString("Details"));
                log.setTimestamp(rs.getTimestamp("Timestamp"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void insertLog(int userID, String action, String details) {
    String sql = "INSERT INTO ActivityLog (UserID, Action, Details) VALUES (?, ?, ?)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, userID);
        ps.setString(2, action);
        ps.setString(3, details);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public List<ActivityLog> getLogsByPage(int offset, int limit) {
    List<ActivityLog> list = new ArrayList<>();
    String sql = "SELECT l.*, u.FullName FROM ActivityLog l " +
                 "JOIN Users u ON l.UserID = u.UserID " +
                 "ORDER BY l.Timestamp DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, offset);
        ps.setInt(2, limit);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ActivityLog log = new ActivityLog();
            log.setLogID(rs.getInt("LogID"));
            log.setUserID(rs.getInt("UserID"));
            log.setFullName(rs.getString("FullName"));
            log.setAction(rs.getString("Action"));
            log.setDetails(rs.getString("Details"));
            log.setTimestamp(rs.getTimestamp("Timestamp"));
            list.add(log);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

public int countLogs() {
    String sql = "SELECT COUNT(*) FROM ActivityLog";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}


}
