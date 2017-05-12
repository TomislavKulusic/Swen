import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * This
 */
class Save {

    /**
     * Saves the image in specified folder
     *
     * @param image Image object that will be saved
     * @throws IOException If saving failed
     */
    Save(CustomImage image) throws IOException {
        JFileChooser chooser = new JFileChooser();
        String name = image.getName();
        image.getImgType();

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setSelectedFile(new File(name.contains(".") ? name : name + "." + image.getImgType()));



        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            ImageIO.write(image.getBufferedImage(), image.getImgType(), chooser.getSelectedFile());
    }

}