import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;



public class CustomImage {

   public ImageIcon image;
   public int height;
   public int width;
   public BufferedImage img;
   
   public CustomImage(ImageIcon image) {
   
      this.image = image;
      this.height = image.getIconHeight();
      this.width = image.getIconWidth();
      img = (BufferedImage)image.getImage();
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
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getWidth() {
      return width;
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
   
}