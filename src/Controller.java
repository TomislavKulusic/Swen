import java.sql.Connection;

public class Controller {

   Save save = new Save();
   Resize resize = new Resize();
      Flip flip = new Flip();
      Rotate rotate = new Rotate();
      AddTag addtag = new AddTag();

   
   CustomImage customImage;
   
   public Controller() {
   
   }
   
   public Controller(CustomImage customImage) {
   
      this.customImage = customImage;
      
   }

   
   public boolean Save(CustomImage img) {
      
      return save.Save(img);
   
   }
   
   public void Resize(CustomImage img,int width,int height) {

      resize.Resize(img,width,height);
   
   }
   
   public void Flip(CustomImage img) {
   
      flip.Flip(img);
   
   }

   public void Rotate(CustomImage img){
       rotate.Rotate(img);
   }

   public void addTag(String tag, Connection connection,String tags,String name) {
      addtag.AddTag(tag,connection,tags,name);
   }



}