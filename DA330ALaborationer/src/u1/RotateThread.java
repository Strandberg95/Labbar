package u1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


/**
 * This class represent the thread in the right 
 * panel. It is used to rotate the graphics object
 */
public class RotateThread extends Thread {
	
	private Random rdm; 
	private Rectangle rectangle;
	private Graphics2D g2d;
	
	public RotateThread(Graphics2D g2d) {
		this.g2d = g2d;
		rectangle = new Rectangle(50,50,30,30);
		rdm = new Random();
	}

	/**
	 * The run method is used to rotate
	 * the graphics object and to change
	 * the color of the rectangle in the 
	 * matrix
	 */
	public void run(){
		while(!Thread.interrupted()){
			g2d.setColor(new Color(rdm.nextInt(256),rdm.nextInt(256),rdm.nextInt(256)));
			g2d.rotate(1);
			g2d.draw(rectangle);
			g2d.fill(rectangle);
		}
		  Thread.currentThread().interrupt();
	}

}
