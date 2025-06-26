package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static DBContext instance;
    private Connection connection;

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=NextTest;encrypt=false";
    private final String user = "sa";
    private final String password = "12345";

    private DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
