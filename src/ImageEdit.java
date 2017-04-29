import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ImageEdit extends JFrame implements ActionListener{

   public JPanel pictureView;
   public JLabel imageLabel;
   public JPanel totalView;
   public CustomImage cropCustomImage;
   public CustomImage currentCustomImage;
   public Controller controller = new Controller();
   ArrayList<File> files;
   File file;   

   public ImageEdit(CustomImage img) throws IOException{
    
      setImageView();
      toolbar();
      loadImage(img);
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
   
   public void loadImage(CustomImage img) {
      imageLabel.setIcon(img.getRescaledImage(450,370));

      currentCustomImage = img;
      System.out.println("Current Image height: " + img.getHeight() + " Current width: " + img.getWidth());

   }
   
  public void toolbar() {
      
      JButton crop = new JButton("Crop");
      JButton flip = new JButton("Flip");
      JButton left = new JButton("Rotate Left");
      JButton right = new JButton("Rotate Right");
      JButton save = new JButton("Save");
      JPanel toolbarPanel = new JPanel(new FlowLayout());
      toolbarPanel.add(crop);
      toolbarPanel.add(flip);
      toolbarPanel.add(left);
      toolbarPanel.add(right);
      
      toolbarPanel.add(save);
      
      crop.addActionListener(this);
      save.addActionListener(this);
      flip.addActionListener(this);
      
      add(toolbarPanel,BorderLayout.SOUTH);
   }
   
   
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Crop")) {
                        
            controller.Crop(currentCustomImage);
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450,370));

            System.out.println("Current image height" + currentCustomImage.getHeight());

            
            
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


   }


   

}