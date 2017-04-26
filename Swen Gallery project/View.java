import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class View extends JFrame implements ActionListener{

   ArrayList<File> files;
   File file;
   ArrayList<JPanel> imagePanels;
   ArrayList<JButton> actionButtons;
   JPanel pictureView;
   JLabel wIcon;
   Controller controller = new Controller();
   ImageSize newImage;
   
   
   // default constructor where we load all images from image folder
   // creating arrayLIst of files to store all the pictures
   // calling the createFrame method for creating frame
   public View() {
   
      file = new File("images/");
      files = new ArrayList<File>(Arrays.asList(file.listFiles()));
      imagePanels = new ArrayList<JPanel>();
         
      createFrame();
   
      setVisible(true);
   
   }
   
   
   public void createFrame(){
   
      setSize(800,800);
      setPictureView();
      setImage();
      setOptionTab();
      
      
   
   }
   
   // this method is used to load all the images from the arrayList and display them
   public void setImage(){
      
      try {
         for(int i = 1; i < files.size(); i++) {
         
            JPanel panel = new JPanel();
            BufferedImage wPic = ImageIO.read(files.get(i));
            JButton wIcon = new JButton(new ImageIcon(wPic));
            panel.setPreferredSize(new Dimension(50, 50));
            
            // this action listener is used to load image in center
            wIcon.addActionListener( 
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     loadImage(wPic);
                  }
               });
            
            wIcon.addActionListener(this);
            panel.add(wIcon);
            imagePanels.add(panel);         
            
         }
         
         
         JPanel left = new JPanel(new GridLayout(0,1));
         for(int i = 0; i < imagePanels.size(); i++) {
            
            left.add(imagePanels.get(i));
         
         }
         left.setPreferredSize(new Dimension(200, 1000));
      
         add(left,BorderLayout.WEST);
         
         
         
      
      }
      catch(IOException e) {
         System.out.println("Cannot Load image");
      }
      
   
   }
   
   public void setOptionTab() {
   
      JPanel bot = new JPanel(new GridLayout(0,1));
      actionButtons = new ArrayList<JButton>();
      JButton crop = new JButton("Crop");
      actionButtons.add(crop);
      JButton save = new JButton("Save");
      actionButtons.add(save);
      save.addActionListener(this);
      
      crop.addActionListener(this);
      // controller.crop();
      
      bot.add(crop);
      bot.add(save);
   
   
      add(bot,BorderLayout.EAST);
   
   }
   
   public void setPictureView() {
   
      pictureView = new JPanel();
      wIcon = new JLabel();
      
      pictureView.add(wIcon);
   
   
      add(pictureView,BorderLayout.CENTER);
   
   
   }
   
   public void loadImage(BufferedImage img) {
   
   
   ImageIcon i = new ImageIcon(img);
   wIcon.setIcon(i);
   System.out.println("Current image height: " + i.getIconHeight());
   System.out.println("Current image width: " + i.getIconWidth());
      
   }
   
   
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Crop")) {
            newImage = controller.Crop();
            
        }
        
        if(e.getActionCommand().equals("Save")) {
            
            
            //controller.Save(newImage);
            
        }
       
   }
   
   public static void main(String [] args){
   
      new View();
   }

}