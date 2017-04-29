import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Save {

   public boolean Save(CustomImage image) {
      try {
         BufferedImage img = image.getBufferedImage();
         System.out.println(image.getUrl());
         ImageIO.write(img, "jpg", new File("edited" + image.getUrl()));
         
         return true;
      } catch(Exception e) {
         return false;
      }
   }
     
}