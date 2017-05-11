import javax.swing.*;

/**
 * Created by Nikola on 04.05.2017..
 * Swen final project
 * Created in: 21 : 53
 *
 * Used for memento pattern
 */
class ImageMemento {
    private ImageIcon image;

    /**
     * Returns image
     *
     * @param image ImageIcon
     */
    ImageMemento(ImageIcon image) {
        this.image = image;
    }

    /**
     * Gets state
     * @return Image
     */
    ImageIcon getState() {
        return image;
    }
}
