import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Custom model class for images
 */

public class CustomImage {

    public ImageIcon image;
    private File file;
    private BufferedImage img;
    private String tag = "";
    private String name;
    private String path;

    /**
     * @param imageFile Image as a file object
     */
    public CustomImage(File imageFile) {
        this.file = imageFile;
        this.image = new ImageIcon(imageFile.toString());
        this.name = imageFile.getName().split("-")[0];
        this.path = imageFile.toString();

        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Image name getter
     * @return Name of the image
     */
    String getName() {
        return name;
    }

    /**
     * Image name setter
     * @param name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Get image as ImageIcon
     * @return Image as ImageIcon
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Set image icon
     * @param image
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Rescale an image using java smooth scaling
     * @param width New image width
     * @param height New image height
     * @return Rescaled image
     */
    ImageIcon getRescaledImage(int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    /**
     * @return
     */
    int getHeight() {
        return image.getIconHeight();
    }

    /**
     * @return
     */
    int getWidth() {
        return image.getIconWidth();
    }

    /**
     * @return
     */
    BufferedImage getBufferedImage() {
        return img;
    }

    /**
     * @param img
     */
    void setBuffered(BufferedImage img) {
        this.img = img;
    }

    /**
     *  Necessary to call when saving image otherwise it won't work
     */
    void setBufferedFromImage() {
        img = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = img.createGraphics();
        image.paintIcon(null, g, 0, 0);
        g.dispose();
    }

    /**
     * @return
     */
    String getUrl() {
        return file.getName();
    }

    /**
     * Add a new tag to an image
     * @param addTag Tag to be added to image
     */
    void addTag(String addTag) {
        tag = tag + addTag + ",";
    }

    /**
     * @return
     */
    String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Get an image state
     * @return An image state
     */
    ImageMemento save() {
        return new ImageMemento(getImage());
    }

    /**
     * Restore current image to provided ImageMemento state
     * @param m ImageMemento state to be restored to
     */
    void restore(ImageMemento m) {
        image = m.getState();
        setBufferedFromImage();

    }

    /**
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * @return
     */
    String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }


    public String getImgType() {
        String nesto = file.getName().split("[.]")[1];

        return nesto;
    }
}
