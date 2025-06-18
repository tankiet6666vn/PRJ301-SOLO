package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;

public abstract class BaseDao {
    protected Connection connection;

   public BaseDao() {
        this.connection = DBContext.getInstance().getConnection();
        if (this.connection == null) {
            System.err.println("❌ Lỗi: Connection trong BaseDao đang NULL!");
        } else {
            System.out.println("✅ Connection trong BaseDao đã được khởi tạo.");
        }
    }

    private void initializeConnection() {
        try {
            String user = "sa";
            String pass = "12345";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=soloproject";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // Optional: Method to close the connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
