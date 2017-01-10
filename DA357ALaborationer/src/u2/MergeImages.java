package u2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MergeImages {

	private BufferedImage firstImage;
	private BufferedImage secondImage;
	private BufferedImage thirdImage;
	
	private BufferedImage outImage;
	private WritableRaster firstRaster;
	private WritableRaster secondRaster;
	private WritableRaster thirdRaster;
	private WritableRaster outraster;

	private int width;
	private int height;

	private float c;
	private float b;

	public BufferedImage getImage(){
		return this.outImage;
	}

	public MergeImages(String firstURL, String secondURL, String thirdURL, float c, float b){
		this.c = c;
		this.b = b;

		this.firstImage = readImage(firstURL);
		this.secondImage = readImage(secondURL);
		this.thirdImage = readImage(thirdURL);

		width  = firstImage.getWidth();
		height = firstImage.getHeight();
		
		this.outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		firstRaster  = firstImage.getRaster();
		secondRaster  = secondImage.getRaster();
		thirdRaster  = thirdImage.getRaster();
		
		outraster = outImage.getRaster();

		merge(); 

		try {
			System.out.println("Writing file");
			ImageIO.write(outImage, "PNG", new File("merge.png"));
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

	public void merge(){
		float alpha = 0.5f;
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++) {
//				red = outraster.getSample(i, j, 0);
//				green = outraster.getSample(i, j, 1);
//				blue = outraster.getSample(i, j, 2);
//				alpha = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
				for(int n = 0; n < 3; n++){
					alpha = thirdRaster.getSampleFloat(i, j, n) / 255;
					outraster.setSample(i, j, n, alpha * secondRaster.getSample(i, j, n) + (1-alpha)*firstRaster.getSample(i, j, n));
				}
			}
		}
	}

	public static void main(String[] args) {
		String URL1 = "/Users/Strandberg95/Desktop/aylmao/pic1.jpg";
		String URL2 = "/Users/Strandberg95/Desktop/aylmao/pic2.jpg";
		String URL3 = "/Users/Strandberg95/Desktop/aylmao/background.png";
		float c = 1;
		float b = 0;
		new MergeImages(URL1,URL2,URL3,c,b);
	}



}
