package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends BaseDao {

    // Đăng nhập
    public User checkLogin(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND PasswordHash = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getInt("DepartmentID"),
                        rs.getInt("RoleID")
                    );
                    user.setSecurityQuestion(rs.getString("SecurityQuestion"));
                    user.setSecurityAnswer(rs.getString("SecurityAnswer"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi checkLogin: " + e.getMessage());
        }
        return null;
    }

    // Thêm người dùng mới
    public boolean insert(User user) {
        String sql = "INSERT INTO Users (Username, PasswordHash, FullName, Email, DepartmentID, RoleID, SecurityQuestion, SecurityAnswer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getDepartmentID());
            ps.setInt(6, user.getRoleID());
            ps.setString(7, user.getSecurityQuestion());
            ps.setString(8, user.getSecurityAnswer());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi insert User: " + e.getMessage());
            return false;
        }
    }

    // Kiểm tra email tồn tại
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM Users WHERE Email = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kiểm tra email: " + e.getMessage());
        }
        return false;
    }
    public boolean existsByUsername(String username) {
    String sql = "SELECT 1 FROM Users WHERE Username = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        System.err.println("❌ Lỗi kiểm tra username: " + e.getMessage());
    }
    return false;
}


    // Kiểm tra câu hỏi bảo mật
    public User checkSecurity(String username, String question, String answer) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND SecurityQuestion = ? AND SecurityAnswer = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, question);
            ps.setString(3, answer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getInt("DepartmentID"),
                        rs.getInt("RoleID")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi checkSecurity: " + e.getMessage());
        }
        return null;
    }

    // Lấy danh sách tất cả người dùng kèm RoleName & DepartmentName
 public List<User> getAllUsers() {
    List<User> list = new ArrayList<>();
    String sql = "SELECT u.*, d.DepartmentName, r.RoleName " +
                 "FROM Users u " +
                 "JOIN Departments d ON u.DepartmentID = d.DepartmentID " +
                 "JOIN Roles r ON u.RoleID = r.RoleID";
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            User user = new User(
                rs.getInt("UserID"),
                rs.getString("Username"),
                rs.getString("PasswordHash"),
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getInt("DepartmentID"),
                rs.getInt("RoleID")
            );
            user.setDepartmentName(rs.getString("DepartmentName"));
            user.setRoleName(rs.getString("RoleName"));
            list.add(user);
        }
    } catch (SQLException e) {
        System.err.println("Lỗi getAllUsers: " + e.getMessage());
    }
    return list;
}
 public boolean updateUserProfile(User user) {
    String sql = "UPDATE Users SET FullName = ?, Email = ? WHERE UserID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, user.getFullName());
        ps.setString(2, user.getEmail());
        ps.setInt(3, user.getUserID());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("❌ Lỗi updateUserProfile: " + e.getMessage());
        return false;
    }
}
 public boolean isEmailTakenByOthers(int userId, String email) {
    String sql = "SELECT 1 FROM Users WHERE Email = ? AND UserID != ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.setInt(2, userId);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        System.err.println("Lỗi kiểm tra email: " + e.getMessage());
    }
    return false;
}
public List<User> searchUsers(String keyword) {
    List<User> list = new ArrayList<>();
    String sql = "SELECT u.*, d.DepartmentName, r.RoleName FROM Users u " +
                 "JOIN Departments d ON u.DepartmentID = d.DepartmentID " +
                 "JOIN Roles r ON u.RoleID = r.RoleID " +
                 "WHERE u.FullName LIKE ? OR u.Email LIKE ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        String q = "%" + keyword + "%";
        ps.setString(1, q);
        ps.setString(2, q);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setRoleID(rs.getInt("RoleID"));
                user.setDepartmentID(rs.getInt("DepartmentID"));
                user.setDepartmentName(rs.getString("DepartmentName"));
                user.setRoleName(rs.getString("RoleName"));
                list.add(user);
            }
        }
    } catch (SQLException e) {
        System.err.println("❌ Lỗi searchUsers: " + e.getMessage());
    }
    return list;
}
public User getUserById(int id) {
    String sql = "SELECT * FROM Users WHERE UserID = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new User(
                rs.getInt("UserID"),
                rs.getString("Username"),
                rs.getString("PasswordHash"),
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getInt("DepartmentID"),
                rs.getInt("RoleID"),
                rs.getString("SecurityQuestion"),
                rs.getString("SecurityAnswer")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public boolean updateUser(User user) {
    String sql = "UPDATE Users SET FullName=?, Email=?, DepartmentID=?, RoleID=? WHERE UserID=?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, user.getFullName());
        ps.setString(2, user.getEmail());
        ps.setInt(3, user.getDepartmentID());
        ps.setInt(4, user.getRoleID());
        ps.setInt(5, user.getUserID());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}



public boolean deleteUserCompletely(int userID) {
    String deleteLogs = "DELETE FROM ActivityLog WHERE UserID = ?";
    String deleteLeaves = "DELETE FROM LeaveRequests WHERE UserID = ? OR ApprovedBy = ?";
    String deleteUser = "DELETE FROM Users WHERE UserID = ?";

    try {
        connection.setAutoCommit(false); // bắt đầu transaction

        try (PreparedStatement ps1 = connection.prepareStatement(deleteLogs)) {
            ps1.setInt(1, userID);
            ps1.executeUpdate();
        }

        try (PreparedStatement ps2 = connection.prepareStatement(deleteLeaves)) {
            ps2.setInt(1, userID);
            ps2.setInt(2, userID);
            ps2.executeUpdate();
        }

        try (PreparedStatement ps3 = connection.prepareStatement(deleteUser)) {
            ps3.setInt(1, userID);
            int rows = ps3.executeUpdate();
            connection.commit();
            return rows > 0;
        }

    } catch (SQLException e) {
        try {
            connection.rollback(); // rollback nếu lỗi
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("❌ Lỗi deleteUserCompletely: " + e.getMessage());
        return false;
    } finally {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public User getByUsername(String username) {
    String sql = "SELECT * FROM Users WHERE Username = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUserID(rs.getInt("UserID"));
            user.setUsername(rs.getString("Username"));
            user.setPasswordHash(rs.getString("PasswordHash"));
            user.setFullName(rs.getString("FullName"));
            user.setEmail(rs.getString("Email"));
            user.setDepartmentID(rs.getInt("DepartmentID"));
            user.setRoleID(rs.getInt("RoleID"));
            user.setSecurityQuestion(rs.getString("SecurityQuestion"));
            user.setSecurityAnswer(rs.getString("SecurityAnswer"));
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}






}
