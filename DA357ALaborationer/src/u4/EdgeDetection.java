package u4;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;
import javax.print.DocFlavor.URL;

public class EdgeDetection {

   private BufferedImage inImage;
   private BufferedImage outImage;
   private WritableRaster inraster;
   private WritableRaster outraster;
   
   private int width;
   private int height;
   private int threshold;
   
//   private float c;
//   private float b;
   
   private int[][] sobelX = {{-1,0,1},
		   					 {-2,0,2},
		   					 {-1,0,1}};
   
   private int[][] sobelY = {{-1,-2,-1},
		    				 { 0, 0, 0},
		    				 { 1, 2, 1}};

   public BufferedImage getImage(){
      return this.outImage;
      
   }

   public EdgeDetection(String URL,int threshold){
	   
//	   this.c = c;
//	   this.b = b;
	   this.threshold = threshold;
	   this.inImage = readImage(URL);
	   
	   width  = inImage.getWidth();
	   height = inImage.getHeight();
	   this.outImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
	   inraster  = inImage.getRaster();
	   outraster = outImage.getRaster();
	   
	   detectEdges();  
	   
	   try {
		   System.out.println("Writing file");
		   ImageIO.write(outImage, "PNG", new File("Calr2.png"));
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
   
   public void detectEdges(){
	   
	   float pixel_x = 0;
	   float pixel_y = 0;
	   int val = 0;
	   
	   for(int x = 1; x < width - 2; x++){
		   for(int y = 1; y < height - 2; y++){
			   
			    pixel_x = (sobelX[0][0] * inraster.getSample(x-1,y-1,0)) + (sobelX[0][1] * inraster.getSample(x,y-1,0)) + (sobelX[0][2] * inraster.getSample(x+1,y-1,0)) +
			              (sobelX[1][0] * inraster.getSample(x-1,y,0))   + (sobelX[1][1] * inraster.getSample(x,y,0))   + (sobelX[1][2] * inraster.getSample(x+1,y,0)) +
			              (sobelX[2][0] * inraster.getSample(x-1,y+1,0)) + (sobelX[2][1] * inraster.getSample(x,y+1,0)) + (sobelX[2][2] * inraster.getSample(x+1,y+1,0));

			    pixel_y = (sobelY[0][0] * inraster.getSample(x-1,y-1,0)) + (sobelY[0][1] * inraster.getSample(x,y-1,0)) + (sobelY[0][2] * inraster.getSample(x+1,y-1,0)) +
			              (sobelY[1][0] * inraster.getSample(x-1,y,0))   + (sobelY[1][1] * inraster.getSample(x,y,0))   + (sobelY[1][2] * inraster.getSample(x+1,y,0)) +
			              (sobelY[2][0] * inraster.getSample(x-1,y+1,0)) + (sobelY[2][1] * inraster.getSample(x,y+1,0)) + (sobelY[2][2] * inraster.getSample(x+1,y+1,0));

			    val = (int) Math.sqrt(Math.round((pixel_x * pixel_x) + (pixel_y * pixel_y)));
			    if(val > threshold){
			    	outraster.setSample(x, y, 0, 255);
			    }else{
			    	outraster.setSample(x, y, 0, 0);
			    }
//			    
//			    outraster.setSample(x, y, 0, val);
			   
		   }
	   }
   }
   
   public static void main(String[] args) {
	   String URl = "";
	   new EdgeDetection("/Users/Strandberg95/Desktop/aylmao/Calr.jpg",90);
   }
}


