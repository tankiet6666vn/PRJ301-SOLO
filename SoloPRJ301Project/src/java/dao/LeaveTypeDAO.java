package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.LeaveType;

public class LeaveTypeDAO extends BaseDao {

    public List<LeaveType> getAll() {
        List<LeaveType> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveTypes";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LeaveType lt = new LeaveType(
                    rs.getInt("LeaveTypeID"),
                    rs.getString("TypeName")
                );
                list.add(lt);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi getAll LeaveTypes: " + e.getMessage());
        }

        return list;
    }
}
