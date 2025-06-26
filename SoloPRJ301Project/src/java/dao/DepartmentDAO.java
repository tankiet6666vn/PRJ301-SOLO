package dao;

import model.Department;
import java.sql.*;
import java.util.*;

public class DepartmentDAO extends BaseDao {

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Department d = new Department();
                d.setDepartmentID(rs.getInt("DepartmentID"));
                d.setDepartmentName(rs.getString("DepartmentName"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi load phòng ban: " + e.getMessage());
        }
        return list;
    }
}
