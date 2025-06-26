package util;

import java.sql.Connection;

public abstract class BaseDao {
    protected Connection connection;

    public BaseDao() {
        this.connection = DBContext.getInstance().getConnection();
    }
}
