import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikola on 26.04.2017..
 *///s
public class ImageView {


    public static JFrame frame;
    private ArrayList<CustomImage> customImages;
    private Connection connection;
    private JPanel imageGrid = new JPanel(new GridLayout(0,5));
    private String albumName;

    public ImageView(ArrayList<CustomImage> files,Connection connection,String albumName){
        this.connection = connection;
        this.customImages = files;
        this.albumName = albumName;

        frame = new JFrame();


        frame.setLocationRelativeTo(null);
        populateGrid(customImages);
        searchPanel();

        frame.pack();
        frame.setVisible(true);
    }

    public void populateGrid(ArrayList<CustomImage> customImages){



        for(int i = 0; i < customImages.size(); i++){
            CustomImage customImage = customImages.get(i);

            JButton imageIcon = new JButton(customImage.getRescaledImage(100,100));


            imageIcon.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            try {
                                new ImageEdit(customImage,connection);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
            imageGrid.add(imageIcon);
        }

        frame.add(imageGrid,BorderLayout.NORTH);
        imageGrid.revalidate();
    }

    public void searchPanel() {
        JLabel label = new JLabel("Search picture by tag");
        JTextField search = new JTextField(5);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(search.getText());
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(label);
        bottomPanel.add(search);
        bottomPanel.add(searchButton);
        frame.add(bottomPanel,BorderLayout.SOUTH);
    }

    public void search(String tag) {
        ArrayList<CustomImage> newList = new ArrayList<CustomImage>();
        String tagArray[];
        imageGrid.removeAll();
        int id = 0;

        if (tag.equals("")) {
            populateGrid(customImages);
        } else {

            try {
            String query = "SELECT image_path, tag FROM images LEFT JOIN album ON album.album_id = images.album_id WHERE Album.album_name = ?";

                PreparedStatement newStat = connection.prepareStatement(query);
                newStat.setString(1, albumName);
                ResultSet newRs = null;
                newRs = newStat.executeQuery();

                while (newRs.next()) {

                    String imageName = newRs.getString("image_path");
                    String iTag = newRs.getString("tag");
                    CustomImage custom = new CustomImage(new File(imageName));
                    custom.addTag(iTag);
                    tagArray = custom.getTag().split(",");

                    for (int j = 0; j < tagArray.length; j++) {
                        if (tagArray[j].equals(tag)) {
                            newList.add(custom);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            populateGrid(newList);
        }
    }
}