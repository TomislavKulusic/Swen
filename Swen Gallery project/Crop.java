import javax.swing.*;

public class Crop {

   public ImageSize icon;

   public ImageSize Crop() {
   
      icon = new ImageSize(200,200);
      System.out.println("Picture is cropped" + icon.x + " " + icon.y);
      return icon;
   }
   
   
}
