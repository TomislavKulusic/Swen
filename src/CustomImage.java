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

   public ImageIcon getImageIcon(){
      Image img = image.getImage() ;
      Image newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;
      return new ImageIcon( newimg );
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