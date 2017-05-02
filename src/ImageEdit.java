import javax.swing.*;
import java.sql.Connection;
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
   public JLabel heightLabel;
   public JLabel widthLabel;
   public Connection connection = null;
   ArrayList<File> files;
   File file;   

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
   
   public void loadImage(CustomImage img) {
      imageLabel.setIcon(img.getRescaledImage(450,370));

      currentCustomImage = img;
      System.out.println("Current Image height: " + img.getHeight() + " Current width: " + img.getWidth());

   }
   
  public void toolbar() {


      
      JButton crop = new JButton("Crop");
      JButton flip = new JButton("Flip");
      JButton rotate = new JButton("Rotate");
      JButton save = new JButton("Save");
      JButton addTag = new JButton("Add Tag");
      JPanel toolbarPanel = new JPanel(new FlowLayout());
      toolbarPanel.add(crop);
      toolbarPanel.add(flip);
      toolbarPanel.add(rotate);
      toolbarPanel.add(addTag);
      toolbarPanel.add(save);
      
      crop.addActionListener(this);
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
   
   
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Crop")) {
                        
            controller.Crop(currentCustomImage);
            imageLabel.setIcon(null);
            imageLabel.setIcon(currentCustomImage.getRescaledImage(450,370));
            widthLabel.setText(null);
            heightLabel.setText(null);
            widthLabel.setText(Integer.toString(currentCustomImage.getWidth()));
            heightLabel.setText(Integer.toString(currentCustomImage.getHeight()));
            
            
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