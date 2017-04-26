import javax.swing.*;


public class Controller {

   Save save = new Save();
   Crop crop = new Crop();
   
   ImageSize size;
   
   public Controller() {
   
   }
   
   public Controller(ImageSize size) {
   
      this.size = size;
      
   }

   
   public boolean Save(int x,int y) {
      
      return save.Save(x,y);
   
   }
   
   public ImageSize Crop() {
   
      return crop.Crop();
   
   }



}