import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class View extends JFrame implements ActionListener{

   File file;
   ArrayList<JPanel> imagePanels;
   ArrayList<JButton> actionButtons;
   JPanel pictureView;
   JLabel wIcon;
   Controller controller = new Controller();
   Image cropImage;
   Image currentImage = null;
   ArrayList<File> files;
   
   
   // default constructor where we load all images from image folder
   // creating arrayLIst of files to store all the pictures
   // calling the createFrame method for creating frame
   public View() {

      imagePanels = new ArrayList<JPanel>();

      JFileChooser jfc = new JFileChooser();
      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnVal = jfc.showOpenDialog(this);

      if(returnVal == JFileChooser.APPROVE_OPTION) {
         Loader loader = new Loader(jfc.getSelectedFile().getAbsolutePath());
         files = loader.getFiles();
      }

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
            
            Image image = new Image(new ImageIcon(wPic));
            
            JButton wIcon = new JButton(image.getImage());
            
            // this action listener is used to load image in center
            wIcon.addActionListener( 
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     loadImage(image);
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
   
   public void loadImage(Image img) {
   
   wIcon.setIcon(img.getImage());
   currentImage = img;
   ///System.out.println("Current image height: " + img.getHeight());
   //System.out.println("Current image width: " + img.getWidth());
      
   }
   
   
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Crop")) {
            
            cropImage = controller.Crop(currentImage);
            System.out.println(cropImage.getWidth());
            
            
        }
        
        if(e.getActionCommand().equals("Save")) {
            
            
            //controller.Save(newImage);
            
        }
       
   }
   
/*   public static void main(String [] args){
   
      new View();

   }*/

}