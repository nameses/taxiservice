package pool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ConnectionCreator {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            // properties.load(new FileReader(new File("src\\main\\resources\\database.properties")));
            properties.load(
                    new FileReader(new File(Objects.requireNonNull(
                            ConnectionCreator.class.getClassLoader()
                                    .getResource("database.properties")).toURI())));
            Class.forName((String) properties.get("driver"));
            DriverManager.registerDriver((Driver) Class.forName((String) properties.get("driver")).getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException | IOException | URISyntaxException | SQLException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace(); // fatal exception
        } catch (InvocationTargetException|NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        DATABASE_URL = (String) properties.get("url");
    }

    private ConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
