import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class ImageEdit extends JFrame implements ActionListener{

   public JPanel pictureView;
   public JLabel imageLabel;
   public JPanel totalView;
   public Image cropImage;
   public Image currentImage;
   public Controller controller = new Controller();
   ArrayList<File> files;
   File file;   

   public ImageEdit(Image img) throws IOException{
    
      setImageView();
      toolbar();
      loadImage(img);
      setVisible(true);
   
   
   }
   
   public void setImageView() {
   
      setSize(500,500);
      
      
      pictureView = new JPanel();
      imageLabel = new JLabel();
      pictureView.add(imageLabel);
      
      add(pictureView);
      
   
   }
   
   public void loadImage(Image img) {
      imageLabel.setIcon(img.getImage());
      currentImage = img;
   
   }
   
   public void toolbar() {
      
      JButton crop = new JButton("Crop");
      JButton left = new JButton("Rotate Left");
      JButton right = new JButton("Rotate Right");
      JButton save = new JButton("Save");
      JPanel toolbarPanel = new JPanel(new FlowLayout());
      toolbarPanel.add(crop);
      toolbarPanel.add(left);
      toolbarPanel.add(right);
      toolbarPanel.add(save);
      
      crop.addActionListener(this);
      
      add(toolbarPanel,BorderLayout.SOUTH);
   }
   
   
   public void actionPerformed(ActionEvent e) {
  
        if(e.getActionCommand().equals("Crop")) {
                        
            cropImage = controller.Crop(currentImage);
            System.out.println(cropImage.getWidth());
            
            
        }
        
        if(e.getActionCommand().equals("Save")) {
            
            
            //controller.Save(cropImage);
            
        }
       
   }
   
   
   
   
   
   // file = new File("../images/");
//       files = new ArrayList<File>(Arrays.asList(file.listFiles()));
//       imagePanels = new ArrayList<JPanel>();
//          
//       createFrame();
//    
//       setVisible(true);
//    
//    }
//    
//    
//    public void createFrame(){
//    
//       setSize(800,800);
//       setPictureView();
//       setImage();
//       setOptionTab();
//       
//       
//    
//    }
//    
//    // this method is used to load all the images from the arrayList and display them
//    public void setImage(){
//       
//       try {
//          for(int i = 1; i < files.size(); i++) {
//          
//             JPanel panel = new JPanel();
            
            //BufferedImage wPic = ImageIO.read(files.get(i));
            
                     //Image image = new Image(new ImageIcon(wPic));
            
            //JButton wIcon = new JButton(image.getImage());
            
            // this action listener is used to load image in center
            // wIcon.addActionListener( 
//                new ActionListener()
//                {
//                   public void actionPerformed(ActionEvent e)
//                   {
//                     // loadImage(image);
//                   }
//                });
            
            //wIcon.addActionListener(this);
           //  panel.add(wIcon);
//             imagePanels.add(panel);         
//             
//          }
//          
//          
//          JPanel left = new JPanel(new GridLayout(0,1));
//          
//          for(int i = 0; i < imagePanels.size(); i++) {
//             
//             left.add(imagePanels.get(i));
//          
//          }
//          left.setPreferredSize(new Dimension(200, 1000));
//       
//          add(left,BorderLayout.WEST);
//          
//          
//          
//       
//       }
//       catch(IOException e) {
//          System.out.println("Cannot Load image");
//       }
//       
//    
//    }
//    
//    public void setOptionTab() {
//    
//       JPanel bot = new JPanel(new GridLayout(0,1));
//       actionButtons = new ArrayList<JButton>();
//       JButton crop = new JButton("Crop");
//       actionButtons.add(crop);
//       JButton save = new JButton("Save");
//       actionButtons.add(save);
//       save.addActionListener(this);
//       
//       crop.addActionListener(this);
//       // controller.crop();
//       
//       bot.add(crop);
//       bot.add(save);
//    
//    
//       add(bot,BorderLayout.EAST);
//    
//    }
//    
//    public void setPictureView() {
//    
//       pictureView = new JPanel();
//       wIcon = new JLabel();
//       
//       pictureView.add(wIcon);
//    
//    
//       add(pictureView,BorderLayout.CENTER);
//    
//    
//    }
//    
//    public void loadImage(Image img) {
//    
//    wIcon.setIcon(img.getImage());
//    currentImage = img;
//  
//       
//    }
//    
//    
//    public void actionPerformed(ActionEvent e) {
//   
//         if(e.getActionCommand().equals("Crop")) {
//                         
//             cropImage = controller.Crop(currentImage);
//             System.out.println(cropImage.getWidth());
//             
//             
//         }
//         
//         if(e.getActionCommand().equals("Save")) {
//             
//             
//             controller.Save(cropImage);
//             
//         }
//        
//    }

   

}