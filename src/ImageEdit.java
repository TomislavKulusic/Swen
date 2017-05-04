import javax.swing.*;
import java.sql.Connection;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/***
 * This class is for editing the pictures
 */

public class ImageEdit extends JFrame implements ActionListener{

   private JPanel pictureView;
    private JLabel imageLabel;
    private CustomImage currentCustomImage;
    private Controller controller = new Controller();
    private JLabel heightLabel;
    private JLabel widthLabel;
    private Connection connection = null;

    /***
     * Parametrized constructor which accepts the Custom image and database connection
      * @param img
     * @param connection
     * @throws IOException
     */
   public ImageEdit(CustomImage img, Connection connection) throws IOException{

      this.connection = connection;
      setImageView();

      loadImage(img);
      toolbar();
      Point frameLocationPoint = ImageView.frame.getLocation();
      frameLocationPoint.setLocation(frameLocationPoint.getX()+500, frameLocationPoint.getY());
      setLocation(frameLocationPoint);
      setVisible(true);
   
   }
   
   public void setImageView() {
   
      setSize(500,500);
      
      
      pictureView = new JPanel();
      imageLabel = new JLabel();
      pictureView.add(imageLabel);
      
      add(pictureView);
      
   
   }

    /***
     * This class is used to load image to new frame
     * @param img
     */
   public void loadImage(CustomImage img) {
      imageLabel.setIcon(img.getRescaledImage(450,370));

      currentCustomImage = img;
      System.out.println("Current Image height: " + img.getHeight() + " Current width: " + img.getWidth());

   }
   
  public void toolbar() {


      
      JButton resize = new JButton("Resize");
      JButton flip = new JButton("Flip");
      JButton rotate = new JButton("Rotate");
      JButton save = new JButton("Save");
      JButton addTag = new JButton("Add Tag");
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

      JPanel topPanel = new JPanel(new FlowLayout());
      widthLabel = new JLabel("Current width: " + Integer.toString(currentCustomImage.getWidth()));

      heightLabel = new JLabel("Current height: " + Integer.toString(currentCustomImage.getHeight()));
      topPanel.add(widthLabel);
      topPanel.add(heightLabel);

      add(topPanel,BorderLayout.NORTH);

      add(toolbarPanel,BorderLayout.SOUTH);
   }

    /***
     * Here we hanle all on click commands and depending on click the different action will be invoked
     * @param e
     */
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Resize")) {


            int width = Integer.parseInt(JOptionPane.showInputDialog(this, "Set new width"));
            int height = Integer.parseInt(JOptionPane.showInputDialog(this, "Set new height"));
            controller.Resize(currentCustomImage,width,height);
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450,370));
            widthLabel.setText(null);
            heightLabel.setText(null);
            widthLabel.setText("Current width: " + Integer.toString(currentCustomImage.getWidth()));
            heightLabel.setText("Current height: " + Integer.toString(currentCustomImage.getHeight()));
            
            
        }
        
        if(e.getActionCommand().equals("Flip")) {
            controller.Flip(currentCustomImage);

            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450,370));

        }
        
        if(e.getActionCommand().equals("Save")) {
            if(controller.Save(currentCustomImage)) {
                System.out.print("Image Saved");
            } else {
                System.out.print("Failed to Save image");
            }
        }

        if(e.getActionCommand().equals("Rotate")) {
            controller.Rotate(currentCustomImage,30);
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450,370));
        }

       if(e.getActionCommand().equals("Add Tag")) {
           String name = JOptionPane.showInputDialog(this, "Add tag");

           controller.addTag(name,connection,currentCustomImage.getTag(),currentCustomImage.getName());


       }


   }


   

}