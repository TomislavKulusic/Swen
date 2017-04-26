import javax.swing.*;


public class Controller {

   Save save = new Save();
   Crop crop = new Crop();
   
   Image image;
   
   public Controller() {
   
   }
   
   public Controller(Image image) {
   
      this.image = image;
      
   }

   
   public boolean Save(int x,int y) {
      
      return save.Save(x,y);
   
   }
   
   public Image Crop(Image img) {
   
      return crop.Crop(img);
   
   }



}