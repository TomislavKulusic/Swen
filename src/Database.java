import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Tomislav on 5/2/2017.
 */
public class Database {

    private Connection connection = null;

    /**
     * This class is used to connect to database. To connect on your local database you need to change the parameters of getConnection();
     *
     * @return
     */

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Here you have to add your own url and password for connection to database
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gallery", "root", "Svakacast1");

            if (connection != null) {

                System.out.println("Connected to database;");
            }

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }


        return connection;
    }


}
