import java.awt.*;
import javax.swing.*;


public class CustomImage {

   public ImageIcon image;
   public int height;
   public int width;
   
   public CustomImage(ImageIcon image) {
   
      this.image = image;
      this.height = image.getIconHeight();
      this.width = image.getIconWidth();
      
    
   }

   public ImageIcon getImageReScaled(int width, int height){
      return new ImageIcon( image.getImage().getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) );
   }

   public ImageIcon getImage() {
      return image;
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
}