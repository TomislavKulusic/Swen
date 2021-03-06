import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class is mostly used to get all informations from database and also to store changes to database
 */
public class DatabaseImages {
    private Connection connection;

    DatabaseImages(Connection connection) {
        this.connection = connection;

    }

    /***
     * This method is used to store new albums to database
     * @param albumName Name of the album
     */
    void storeAlbums(String albumName) {
        try {
            String query = "INSERT INTO album (album_name) VALUES (?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, albumName);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * This method is used to store images to the database
     * @param images collection of image paths
     */
    void storeImages(ArrayList<File> images) {
        String countQuery = "SELECT count(*) AS `RowCount` FROM album";
        int i = 1;

        try {
            ResultSet rs = connection.prepareStatement(countQuery).executeQuery();

            rs.next();

            int numberRow = rs.getInt("RowCount");

            for (File image : images) {
                String query = "INSERT INTO Images (album_id, image_path, name, image_date, tag) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);

                preparedStmt.setInt(1, numberRow);
                preparedStmt.setString(2, image.getAbsolutePath());
                preparedStmt.setString(3, image.getName().split("-")[0]);
                preparedStmt.setDate(4, new Date(Calendar.getInstance().getTime().getTime()));
                preparedStmt.setString(5, "image" + i++);
                preparedStmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param image   Image that you want to insert
     * @param albumID ID of an album you want to insert in
     */
    void addImage(CustomImage image, int albumID) {
        String query = "INSERT INTO Images (album_id, image_path, name, image_date, tag) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, albumID);
            preparedStmt.setString(2, image.getPath());
            preparedStmt.setString(3, image.getName());
            preparedStmt.setDate(4, new Date(Calendar.getInstance().getTime().getTime()));
            preparedStmt.setString(5, image.getName() + "tag");
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Here we get all images from database
     * @return ArrayList of images from database
     */
    public ArrayList<CustomImage> getImagesFromDatabase() {
        ArrayList<CustomImage> list = new ArrayList<CustomImage>();

        String query = "SELECT * FROM images";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String path = rs.getString("image_path");
                String tag = rs.getString("tag");
                String name = rs.getString("name");

                CustomImage image = new CustomImage(new File(path));
                image.addTag(tag);
                image.setName(name);

                list.add(image);
            } // while

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } // catch

        return list;
    }

}
