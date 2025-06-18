package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO extends BaseDao {

    // Đăng nhập
    public User checkLogin(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND PasswordHash = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password); // Hash nếu cần

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

    // Đăng ký người dùng mới
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

   public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM Users WHERE Email = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // nếu có dòng nào thì email đã tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra email: " + e.getMessage());
        }
        return false;
    }
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




}
