package dao;

import model.BookCategory;
import util.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDAO extends BaseDao {
    public List<BookCategory> getAll() {
        List<BookCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM BookCategory";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookCategory(rs.getInt("cid"), rs.getString("cname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
