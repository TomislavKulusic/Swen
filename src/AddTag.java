import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
  Created by Tomislav on 5/3/2017.
  Swen final project
  Created in: 22 : 02
 */

/**
 * This class is used to add additional tags to picture
 */
class AddTag {

    /**
     * @param tag New tag name
     * @param connection Database connection object
     * @param tags Existing image tags
     * @param name Image name
     */
    AddTag(String tag, Connection connection, String tags, String name) {
        try {
            String newTag = tags + tag;

            String query = "UPDATE images SET tag = ? WHERE name = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newTag);
            preparedStmt.setString(2, name);

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}