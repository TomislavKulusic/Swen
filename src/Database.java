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

    private String host = "host";
    private String password = "Svakacast1";
    private String url = "jdbc:mysql://localhost/gallery";
    private Connection connection = null;


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
