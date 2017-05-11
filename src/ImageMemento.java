import javax.swing.*;

/**
 * Created by Nikola on 04.05.2017..
 * Swen final project
 * Created in: 21 : 53
 */

class ImageMemento {
    private ImageIcon image;

    ImageMemento(ImageIcon image) {
        this.image = image;
    }

    ImageIcon getState() {
        return image;
    }
}
