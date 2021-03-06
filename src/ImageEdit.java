import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;

/**
 * This class is for editing the pictures
 */
public class ImageEdit extends JFrame implements ActionListener {

    private JLabel imageLabel;
    private CustomImage currentCustomImage;
    private JLabel heightLabel;
    private JLabel widthLabel;
    private Connection connection = null;
    private JButton redo = new JButton("Redo");
    private JButton undo = new JButton("Undo");

    private Caretaker caretaker = new Caretaker();
    private Controller controller = new Controller();
    private int mCounter = 0;


    /**
     * Parametrized constructor which accepts the Custom image and database connection
     *
     * @param img CustomImage that will be edited
     * @param connection Connection to the database
     * @throws IOException If edit fails
     */
    ImageEdit(CustomImage img, Connection connection) throws IOException {
        this.connection = connection;
        setImageView();

        loadImage(img);
        toolbar();
        Point frameLocationPoint = ImageView.frame.getLocation();
        frameLocationPoint.setLocation(frameLocationPoint.getX() + 500, frameLocationPoint.getY());
        setLocation(frameLocationPoint);
        setVisible(true);
    }

    /**
     * Sets the view to fit window
     */
    private void setImageView() {
        setSize(500, 500);

        JPanel pictureView = new JPanel();

        imageLabel = new JLabel();

        pictureView.add(imageLabel);

        add(pictureView);
    }

    /***
     * This class is used to load image to new frame
     * @param img Image to load
     */
    private void loadImage(CustomImage img) {
        imageLabel.setIcon(img.getRescaledImage(450, 370));

        currentCustomImage = img;
        caretaker.addMemento(currentCustomImage.save());
        System.out.println("Current Image height: " + img.getHeight() + " Current width: " + img.getWidth());
    }

    /**
     * Initializes the toolbar
     */
    private void toolbar() {
        JButton resize = new JButton("Resize");
        JButton flip = new JButton("Flip");
        JButton rotate = new JButton("Rotate");
        JButton save = new JButton("Save");
        JButton addTag = new JButton("Add Tag");

        undo.setEnabled(false);
        redo.setEnabled(false);

        JPanel toolbarPanel = new JPanel(new FlowLayout());
        toolbarPanel.add(resize);
        toolbarPanel.add(flip);
        toolbarPanel.add(rotate);
        toolbarPanel.add(addTag);
        toolbarPanel.add(save);

        resize.addActionListener(this);
        save.addActionListener(this);
        flip.addActionListener(this);
        rotate.addActionListener(this);
        addTag.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout());

        widthLabel = new JLabel("Current width: " + Integer.toString(currentCustomImage.getWidth()));

        heightLabel = new JLabel("Current height: " + Integer.toString(currentCustomImage.getHeight()));
        topPanel.add(undo);
        topPanel.add(widthLabel);
        topPanel.add(heightLabel);
        topPanel.add(redo);

        add(topPanel, BorderLayout.NORTH);

        add(toolbarPanel, BorderLayout.SOUTH);
    }

    /***
     * Here we handle all on click commands and depending on click a different action will be invoked
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("Redo") && !e.getActionCommand().equals("Undo")) {
            if (mCounter != (caretaker.getMementosSize() - 1)) {
                System.out.println("Wiped");
                caretaker.wipeMementos(mCounter);
                currentCustomImage.restore(caretaker.getMemento(mCounter));
            }
        }

        if (e.getActionCommand().equals("Resize")) {
            mCounter++;
            String width = JOptionPane.showInputDialog(this, "Set new width");
            String height = JOptionPane.showInputDialog(this, "Set new height");

            if (width == null || height == null) {
                mCounter--;
                return;
            }

            controller.Resize(currentCustomImage, Integer.parseInt(width), Integer.parseInt(height));
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450, 370));
            widthLabel.setText(null);
            heightLabel.setText(null);
            widthLabel.setText("Current width: " + Integer.toString(currentCustomImage.getWidth()));
            heightLabel.setText("Current height: " + Integer.toString(currentCustomImage.getHeight()));

            caretaker.addMemento(currentCustomImage.save());
        }

        if (e.getActionCommand().equals("Flip")) {
            mCounter++;

            controller.Flip(currentCustomImage);

            caretaker.addMemento(currentCustomImage.save());

            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450, 370));
        }

        if (e.getActionCommand().equals("Save"))
            if (controller.Save(currentCustomImage))
                System.out.print("Image Saved");
            else
                System.out.print("Failed to Save image");

        if (e.getActionCommand().equals("Rotate")) {
            mCounter++;

            controller.Rotate(currentCustomImage);
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450, 370));

            caretaker.addMemento(currentCustomImage.save());
        }

        if (e.getActionCommand().equals("Add Tag")) {
            String name = JOptionPane.showInputDialog(this, "Add tag");

            controller.addTag(name, connection, currentCustomImage.getTag(), currentCustomImage.getName());
        }

        if (e.getActionCommand().equals("Undo")) {
            redo.setEnabled(true);
            mCounter--;
            imageLabel.setIcon(null);
            System.out.println(caretaker.getMementosSize());
            currentCustomImage.restore(caretaker.getMemento(mCounter));
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450, 370));

            if (mCounter == 0)
                undo.setEnabled(false);
        }

        if (e.getActionCommand().equals("Redo")) {
            undo.setEnabled(true);
            mCounter++;
            imageLabel.setIcon(null);
            currentCustomImage.restore(caretaker.getMemento(mCounter));
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450, 370));
        }

        if (mCounter == (caretaker.getMementosSize() - 1))
            redo.setEnabled(false);

        if (mCounter > 0)
            undo.setEnabled(true);
    }

}