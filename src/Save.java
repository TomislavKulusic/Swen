import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

//s
public class Save {

   public boolean Save(CustomImage image) {
      try {
         BufferedImage img = image.getBufferedImage();
         System.out.println(image.getUrl());
         ImageIO.write(img, "jpg", new File("EditedPictures/edited" + image.getUrl()));
         
         return true;
      } catch(Exception e) {
         return false;
      }
   }
     
}