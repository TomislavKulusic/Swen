public class Crop {

   public CustomImage Crop(CustomImage img) {
   
      CustomImage customImage = img;
      
      
      System.out.println("Current customImage width" + img.getWidth());
      
      customImage.setWidth(500);
      
      
      return customImage;
   }
   
   
}
