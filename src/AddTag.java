import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Tomislav on 5/3/2017.
 */
public class AddTag {

    String newTag = "";

    public void AddTag(String tag, Connection connection,String tags,String name) {

        try {
            newTag = tags + tag;
            System.out.println(newTag);
            System.out.println(name);
            String query = "update images set tag = ? where name = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newTag);
            preparedStmt.setString(2, name);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}