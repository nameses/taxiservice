package pool;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final Properties properties = new Properties();
    private List<Connection> availableConnections = null;

    private ConnectionPool() {
        try {
            properties.load(
                    new FileReader(new File(Objects.requireNonNull(
                            ConnectionPool.class.getClassLoader()
                                    .getResource("database.properties")).toURI())));
            this.availableConnections = new ArrayList<>();
            this.initPool();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void initPool() {
        try {
            Class.forName((String) properties.get("driver"));
            Connection connection = null;
            for (int i = 0; i < Integer.parseInt((String) properties.get("maxConnections")); i++) {
                connection = DriverManager.getConnection((String) properties.get("url"),
                        (String) properties.get("user"),
                        (String) properties.get("password"));
                availableConnections.add(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }


    public Connection getConnection() {
        int index = availableConnections.size() - 1;
        Connection connection = availableConnections.get(index);
        availableConnections.remove(index);
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            connection.close();
            availableConnections.add(renewConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection renewConnection() {
        try {
            return DriverManager.getConnection((String) properties.get("url"),
                    (String) properties.get("user"),
                    (String) properties.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}