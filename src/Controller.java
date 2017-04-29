import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Controller {

   Save save = new Save();
   Crop crop = new Crop();
      Flip flip = new Flip();
      Rotate rotate = new Rotate();

   
   CustomImage customImage;
   
   public Controller() {
   
   }
   
   public Controller(CustomImage customImage) {
   
      this.customImage = customImage;
      
   }

   
   public boolean Save(CustomImage img) {
      
      return save.Save(img);
   
   }
   
   public void Crop(CustomImage img) {

      crop.Crop(img);
   
   }
   
   public void Flip(CustomImage img) {
   
      flip.Flip(img);
   
   }

   public void Rotate(CustomImage img,double degrees){
       rotate.Rotate(img,degrees);
   }




}