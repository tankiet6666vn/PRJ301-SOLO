package dao;

import model.User;
import util.BaseDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends BaseDao {
    public User login(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
