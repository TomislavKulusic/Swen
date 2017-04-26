import javax.swing.*;

public class Crop {

   public Image Crop(Image img) {
   
      Image image = img;
      
      
      System.out.println("Current image width" + img.getWidth());
      
      image.setWidth(500);
      
      
      return image;
   }
   
   
}
