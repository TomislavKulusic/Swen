import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CustomImage {

   public ImageIcon image;
   public int height;
   public int width;
   public File file;
   public BufferedImage img;
   
   public CustomImage(File imageFile) {


      this.image = new ImageIcon(imageFile.toString());

      try {
         img = ImageIO.read(imageFile);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public ImageIcon getImage() {
      return image;
   }
   
   public ImageIcon getRescaledImage(int width, int height) {
      return new ImageIcon(image.getImage().getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) );
      
   }

   public void setImage(ImageIcon image) {
      this.image = image;
   }

   public int getHeight() {
      return image.getIconHeight();
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getWidth() {
      return image.getIconWidth();
   }

   public void setWidth(int width) {
      this.width = width;
   }
   
   public BufferedImage getBufferedImage() {
      return img;
   }
   
   public void setBuffered(BufferedImage img)
   {
      this.img = img;
   }

   public void setBufferedFromImage()
   {
      img = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_RGB);
      Graphics g = img.createGraphics();
      image.paintIcon(null, g, 0,0);
      g.dispose();
   }

}
