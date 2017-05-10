
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Tomislav on 5/4/2017.
 */

/**
    * This class is used used to load the albums and pictures to the database, it is
 *  also main method.
 */
public class AlbumList extends JFrame implements ActionListener{

    // Attributes
    private ArrayList<File> files;
    private Connection connection;
    private ArrayList<Album> list;
    private DatabaseImages dbimg;
    private Loader loader;

    public AlbumList() { // This class calls the Database class and connects to database
        Database database = new Database();
        connection = database.getConnection();

        createFrame();
    }

    public void createFrame() { // method for creating frame
        setSize(700, 500);
        makeOptions();

        setVisible(true);
    }

    /**
     * This method is used to create the option for loading new album and also if there is already album
     * loaded before it will display it and you can click on it and display pictures.
     */
    public void makeOptions() {
        JButton openAlbum = new JButton("Open Album");
        JButton addAlbum = new JButton("Open new Album");
        openAlbum.addActionListener(this);
        addAlbum.addActionListener(this);
        list = getAlbums();
        if(list.size() == 0) {
            JPanel panel = new JPanel();
            panel.add(openAlbum);
            add(panel);
        } else {
            JPanel title = new JPanel();
            JLabel titleLabel = new JLabel("List of Albums");
            JPanel buttonPannel = new JPanel(new GridLayout(0, 5));
            JPanel bottom = new JPanel();
            bottom.add(addAlbum);


            for(int i = 0; i < list.size(); i++) {

                CustomImage cs = new CustomImage(new File("C:\\Users\\Tomislav\\Desktop\\Swen Gallery\\Swen\\assets\\albumImage.jpg"));
                JButton button = new JButton(cs.getRescaledImage(100,100));


                JLabel label = new JLabel(list.get(i).albumName);
                Album album = list.get(i);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       ImageView iv = new ImageView(getAlbumImages(album),connection);

                    }
                });

                JPanel panel = new JPanel();

                panel.add(button);
                panel.add(label,BorderLayout.SOUTH);
                panel.setBorder(new EmptyBorder(10, 10, 10, 10));

                buttonPannel.add(panel);
            }
            this.add(title.add(titleLabel),BorderLayout.NORTH);
            this.add(buttonPannel,BorderLayout.CENTER);
            add(bottom,BorderLayout.SOUTH);

        }


    }

    /**
     * Method with file chooser object, here after choosing the file we send all the pictures to the database
     */
    public void chooseAlbum() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(workingDirectory);
        int returnVal = jfc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            loader = new Loader(jfc.getSelectedFile().getAbsolutePath());
            files = loader.getFiles();
        }

        dbimg = new DatabaseImages(connection);
        String albumName =  JOptionPane.showInputDialog(this, "Set Album Name");
        dbimg.storeAlbums(albumName);
        dbimg.storeImages(files);


        ImageView iv = new ImageView(loader.getImages(files),connection);
        this.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Open Album") || e.getActionCommand().equals("Open new Album")) {
            chooseAlbum();
        }
    }

    /**
     * This method gets all data from database and from thoes informations crates the album model
     * @return returns the collection of albums
     */
    public ArrayList<Album> getAlbums() {
        ArrayList<Album> albumList = new ArrayList<Album>();
        String query = "SELECT * from album";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                  String albumName = rs.getString("album_name");
                  int albumID = rs.getInt("album_id");
                  Album album = new Album(albumName);


                String newQuery = "select * from images where album_id = ?";

                PreparedStatement newStat = connection.prepareStatement(newQuery);
                newStat.setInt(1,albumID);
                ResultSet newRs = newStat.executeQuery();

                while(newRs.next()) {
                    String imageName = newRs.getString("image_path");
                    CustomImage custom = new CustomImage(new File(imageName));
                    album.addImages(custom);

                }

                albumList.add(album);



            } // while
            st.close();
        }catch (SQLException e) {}

        return albumList;
    }

    /***
     *
     * @param album object
     * @return collection of CustomImages
     */
    public ArrayList<CustomImage> getAlbumImages(Album album) {
        return album.getImages();
    }

    public static void main(String[] args) {
        new AlbumList();
    }
}
