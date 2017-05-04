import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Tomislav on 5/2/2017.
 */

/**
 * This class is mostly used to get all informations from database and also to store changes to database
 */

public class DatabaseImages {
    private Connection connection;
    private ArrayList<File> list;

    public DatabaseImages(Connection connection) {
        this.connection = connection;

    }

    /***
     * This method is used to store new albums to database
     * @param albumName
     */
    public void storeAlbums(String albumName) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String query = " insert into album (album_name) values (?)";
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
    public void storeImages(ArrayList<File> images) {
        Statement stmt = null;
        int numberRow = 0;

        String countQuery = "select count(*) from album";
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        try {
            PreparedStatement ps = connection.prepareStatement(countQuery);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                numberRow = rs.getInt("count(*)");
            }
            System.out.print("Row count" + numberRow);




        for (int i = 0; i < images.size(); i++) {



                String query = "INSERT INTO Images (album_id,image_path,name,image_date,tag) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, numberRow);
                preparedStmt.setString(2, images.get(i).getAbsolutePath());
                preparedStmt.setString(3,images.get(i).getName().split("-")[0]);
                preparedStmt.setDate(4, startDate);
                preparedStmt.setString(5, images.get(i).getName() + "tag");
                preparedStmt.execute();

        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Here we get all images from database
     * @return
     */
    public ArrayList<CustomImage> getImagesFromDatabase() {
        ArrayList<CustomImage> list = new ArrayList<CustomImage>();

        String query = "SELECT * FROM images";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                String path = rs.getString("image_path");
                String date = rs.getString("image_date");
                String tag = rs.getString("tag");
                String name = rs.getString("name");


                CustomImage image = new CustomImage(new File(path));
                image.addTag(tag);
                image.setName(name);

                list.add(image);

                // print the results
            } // while
            st.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } // catch

        return list;

    }

}
