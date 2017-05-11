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

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    ImageIcon getRescaledImage(int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    int getHeight() {
        return image.getIconHeight();
    }

    int getWidth() {
        return image.getIconWidth();
    }

    BufferedImage getBufferedImage() {
        return img;
    }

    void setBuffered(BufferedImage img) {
        this.img = img;
    }

    void setBufferedFromImage() {
        img = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = img.createGraphics();
        image.paintIcon(null, g, 0, 0);
        g.dispose();
    }

    String getUrl() {
        return file.getName();
    }

    void addTag(String addTag) {
        tag = tag + addTag + ",";
    }

    String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    ImageMemento save() {
        return new ImageMemento(getImage());
    }

    void restore(ImageMemento m) {
        image = m.getState();
        setBufferedFromImage();

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
