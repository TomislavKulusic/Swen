import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Save {

   public boolean Save(BufferedImage img) {
      try {
         ImageIO.write(img, "jpg", new File("img.jpg"));
         
         return true;
      } catch(Exception e) {
         return false;
      }
   }
     
}