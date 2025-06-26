package dao;

import model.Book;
import util.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDao {
    public List<Book> getBooksByUser(String username) {
        List<Book> list = new ArrayList<>();
        String sql = """
            SELECT b.bid, b.title, b.publisheddate, b.cid, b.createdby, c.cname
            FROM Book b
            JOIN BookCategory c ON b.cid = c.cid
            WHERE b.createdby = ?
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setBid(rs.getInt("bid"));
                b.setTitle(rs.getString("title"));
                b.setPublisheddate(rs.getDate("publisheddate"));
                b.setCid(rs.getInt("cid"));
                b.setCreatedby(rs.getString("createdby"));
                b.setCategoryName(rs.getString("cname")); // ✅ tên thể loại
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createBook(Book b) {
        String sql = "INSERT INTO Book (title, publisheddate, cid, createdby) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, b.getTitle());
            ps.setDate(2, b.getPublisheddate());
            ps.setInt(3, b.getCid());
            ps.setString(4, b.getCreatedby());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
