package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/taxiservice");
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
