import javax.swing.*;
import java.awt.*;

//s
public class Resize {

      CustomImage customImage;

   public void Resize(CustomImage img,int width,int height) {
   
      customImage = img;

      
      resize(width,height);
   }

   public void resize(int w, int h)
   {
      Image im = customImage.getBufferedImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
      customImage.setImage(new ImageIcon(im));
      customImage.setBufferedFromImage();
   }


   
   
}
