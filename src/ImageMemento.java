import javax.swing.*;

/**
 * Created by Nikola on 04.05.2017..
 *///s
public class ImageMemento {
    private ImageIcon image;

    public ImageMemento(ImageIcon image) {
        this.image = image;
    }

    public ImageIcon getState() {
        return image;
    }
}
