public class Controller {

   Save save = new Save();
   Crop crop = new Crop();
      Flip flip = new Flip();

   
   CustomImage customImage;
   
   public Controller() {
   
   }
   
   public Controller(CustomImage customImage) {
   
      this.customImage = customImage;
      
   }

   
   public boolean Save(int x,int y) {
      
      return save.Save(x,y);
   
   }
   
   public CustomImage Crop(CustomImage img) {
   
      return crop.Crop(img);
   
   }
   
   public void Flip(CustomImage img) {
   
      flip.Flip(img);
   
   }




}