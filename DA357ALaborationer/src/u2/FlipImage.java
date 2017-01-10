package u2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;
import javax.print.DocFlavor.URL;

public class FlipImage {

   private BufferedImage inImage;
   private BufferedImage outImage;
   private WritableRaster inraster;
   private WritableRaster outraster;
   
   private int width;
   private int height;
   
   private float c;
   private float b;

   public BufferedImage getImage(){
      return this.outImage;
   }

   public FlipImage(String URL, float c, float b){
	   
	   this.c = c;
	   this.b = b;
	   
	   this.inImage = readImage(URL);
	   
	   width  = inImage.getWidth();
	   height = inImage.getHeight();
	   this.outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	   inraster  = inImage.getRaster();
	   outraster = outImage.getRaster();
	   
	   flip(); 
	   
	   try {
		   System.out.println("Writing file");
		   ImageIO.write(outImage, "PNG", new File("flip_bre5.png"));
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
   }
   
   private BufferedImage readImage(String URL){
	   BufferedImage img = null;
	   try {
		   img = ImageIO.read(new FileInputStream(URL));
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   System.out.println(img.equals(null));
	   return img;

   }
   
   public void flip(){
	   for (int i=0; i<width; i++){
		   for (int j=0; j<height; j++) {
			   for(int n = 0; n < 3; n++){
				   int value = inraster.getSample(i,j,n);
				   outraster.setSample(i,j,n, (c * value) + b );
//				   outraster.setSample(i, j, n, value);
			   }
		   }
	   }
   }
   
   public static void main(String[] args) {
	   String URl = "";
	   float c = 1;
	   float b = 0;
	   new FlipImage("/Users/Strandberg95/Desktop/aylmao/bre.png",c,b);
   }
}


