import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nikola on 26.04.2017..
 * Swen final project
 * Created in: 21 : 53
 */
class ImageView {

    static JFrame frame;
    private ArrayList<CustomImage> customImages;
    private Connection connection;
    private JPanel imageGrid = new JPanel(new GridLayout(0, 5));
    private String albumName;
    private int albumId;

    ImageView(Album album, Connection connection) {
        this.connection = connection;
        this.customImages = album.getImages();
        this.albumName = album.getAlbumName();
        this.albumId = album.getAlbumId();

        frame = new JFrame();

        populateGrid(customImages);
        searchPanel();

        frame.pack();
        frame.setVisible(true);
    }

    private void populateGrid(ArrayList<CustomImage> customImages) {
        for (CustomImage customImage : customImages) {
            JButton imageIcon = new JButton(customImage.getRescaledImage(100, 100));

            imageIcon.addActionListener(e -> {
                try {
                    new ImageEdit(customImage, connection);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            imageGrid.add(imageIcon);
        }

        frame.add(imageGrid, BorderLayout.NORTH);
        imageGrid.revalidate();
    }

    private void searchPanel() {
        JLabel label = new JLabel("Search picture by tag");
        JTextField search = new JTextField(5);
        JButton searchButton = new JButton("Search");

        JButton addImageButton = new JButton("Add Image " + albumId);

        searchButton.addActionListener(e -> search(search.getText()));

        addImageButton.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            jfc.setFileFilter(new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes()));

            if (jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                DatabaseImages di = new DatabaseImages(connection);
                CustomImage ci = new CustomImage(new File(jfc.getSelectedFile().getAbsolutePath()));

                customImages.add(ci);

                di.addImage(ci, albumId);

                JButton imageIcon = new JButton(ci.getRescaledImage(100, 100));

                imageIcon.addActionListener(u -> {
                    try {
                        new ImageEdit(ci, connection);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });

                imageGrid.add(imageIcon);
                imageGrid.revalidate();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(label);
        bottomPanel.add(search);
        bottomPanel.add(searchButton);
        bottomPanel.add(addImageButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void search(String tag) {
        ArrayList<CustomImage> newList = new ArrayList<>();
        String tagArray[];
        imageGrid.removeAll();

        if (tag.equals("")) {
            populateGrid(customImages);
        } else {
            try {
                String query = "SELECT image_path, tag FROM images LEFT JOIN album ON album.album_id = images.album_id WHERE Album.album_name = ?";

                PreparedStatement newStat = connection.prepareStatement(query);
                newStat.setString(1, albumName);
                ResultSet newRs = newStat.executeQuery();

                while (newRs.next()) {
                    String imageName = newRs.getString("image_path");
                    String iTag = newRs.getString("tag");
                    CustomImage custom = new CustomImage(new File(imageName));
                    custom.addTag(iTag);
                    tagArray = custom.getTag().split(",");

                    for (String aTagArray : tagArray) {
                        if (aTagArray.equals(tag)) {
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