package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static DBContext instance;
    private Connection connection;

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=soloproject;encrypt=false";
    private final String user = "sa";
    private final String password = "12345";

    // ✅ Đảm bảo constructor là private (không sửa thành public)
    DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Kết nối thành công với SQL Server.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Lỗi: Không tìm thấy driver JDBC!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối SQL Server!");
            e.printStackTrace();
        }
    }

    // ✅ Sử dụng phương thức này để lấy instance
    public static synchronized DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("🔄 Kết nối lại với SQL Server.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kết nối lại SQL Server!");
            e.printStackTrace();
        }
        return connection;
    }
}
