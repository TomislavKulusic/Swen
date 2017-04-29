import javax.swing.*;
import java.awt.*;

public class Crop {

      CustomImage customImage;

   public void Crop(CustomImage img) {
   
      customImage = img;

      
      resize(100,100);
   }

   public void resize(int w, int h)
   {
      Image im = customImage.getBufferedImage().getScaledInstance(w, h, customImage.getBufferedImage().SCALE_DEFAULT);
      customImage.setImage(new ImageIcon(im));
      customImage.setBufferedFromImage();
   }


   
   
}
