import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    /**
     * Constructor
     *
     * @param album      Accepts album
     * @param connection Connection to database
     */
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

    /**
     * @param customImages Array of images that need to be populated
     */
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

    /**
     * Creates search panel with also add image button
     */
    private void searchPanel() {
        JLabel label = new JLabel("Search picture by tag");
        JTextField search = new JTextField(5);
        JButton searchButton = new JButton("Search");

        JButton addImageButton = new JButton("Add Image");

        searchButton.addActionListener(e -> search(search.getText()));

        addImageButton.addActionListener(e -> {
            Object[] options = {"URL",
                    "Choose Image",
                    "Cancel"};

            int n = JOptionPane.showOptionDialog(frame,
                    "Do you want to add image from URL or by selecting it",
                    "Add Image",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            if (n == 0) {
                String s = JOptionPane.showInputDialog(frame,
                        "Image URL",
                        "Add Image From URL",
                        JOptionPane.QUESTION_MESSAGE);

                if (!s.isEmpty()) {
                    try {
                        BufferedImage image = ImageIO.read(new URL(s));

                        String[] name = s.split("[/]");
                        File file;

                        if (name.length != 1)
                            file = new File(name[name.length - 1]);
                        else
                            file = new File(name[name.length - 1]);

                        ImageIO.write(image, file.getName().split("[.]")[1], file);

                        DatabaseImages di = new DatabaseImages(connection);
                        CustomImage ci = new CustomImage(file);

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
                    } catch (IOException z) {
                        z.printStackTrace();
                    }
                }
            } else if (n == 1) {
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
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(label);
        bottomPanel.add(search);
        bottomPanel.add(searchButton);
        bottomPanel.add(addImageButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Searches the database for tags
     *
     * @param tag Tag that needs to be searched
     */
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

                    for (String aTagArray : tagArray)
                        if (aTagArray.equals(tag))
                            newList.add(custom);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            populateGrid(newList);
        }
    }
}