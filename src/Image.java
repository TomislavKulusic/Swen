import java.awt.*;
import javax.swing.*;


public class Image {

   public ImageIcon image;
   public int height;
   public int width;
   
   public Image(ImageIcon image) {
   
      this.image = image;
      this.height = image.getIconHeight();
      this.width = image.getIconWidth();
      
    
   }
   
   public int getHeight() {
      return height;
   }
   
   public int getWidth() {
      return width;
   }
   
   public void setWidth(int w) {
      width = w;
   }
   
   
   public void setHeight(int h) {
      height = h;
   }
   
   public ImageIcon getImage() {
      return this.image;
   }


}