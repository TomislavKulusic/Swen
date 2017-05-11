import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tomislav on 5/2/2017.
 * Swen final project
 * Created in: 21 : 46
 */
public class Database {

    private Connection connection = null;

    /**
     * This class is used to connect to database. To connect on your local database you need to change the parameters of getConnection();
     *
     * @return
     */
    Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Here you have to add your own url and password for connection to database
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gallery", "root", "root");

            if (connection != null)
                System.out.println("Connected to database;");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
