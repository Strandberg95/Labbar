package u1;

import javax.swing.JLabel;

/**
 * This class represent a thread for the left panel
 * in the Frame. It makes the text move around.
 */
public class DisplayThread extends Thread {
	
	private JLabel displayLabel;
	
	private int label_PositionX;
	private int label_PositionY;
	
	private int label_AccelerationX;
	private int label_AccelerationY;
	
	private boolean label_goingUp;
	private boolean label_goingRight;
	
	public DisplayThread(JLabel displayLabel){
		this.displayLabel = displayLabel;
		
		label_PositionX = 0;
		label_PositionY = 0;
		
		label_AccelerationX = 1;
		label_AccelerationY = 2;
		
		label_goingUp = false;
		label_goingRight = false;
	}

	/**
	 * This method is used to update the position 
	 * and change the trajectory of the text 
	 * moving around to make sure that it stays 
	 * on screen 
	 */
	public void run(){
		while(!Thread.interrupted()){
			if(label_PositionX > 150)
				label_goingRight = false;
			if(label_PositionX <= 0)
				label_goingRight = true;
			if(label_PositionY > 180)
				label_goingUp = true;
			if(label_PositionY <= 0)
				label_goingUp = false;
			
			if(label_goingRight){
				displayLabel.setLocation((label_PositionX += label_AccelerationX), label_PositionY);
			}
			if(!label_goingRight){
				displayLabel.setLocation((label_PositionX -= label_AccelerationX), label_PositionY);
			}
			if(label_goingUp){
				displayLabel.setLocation(label_PositionX, label_PositionY -= label_AccelerationY);
			}
			if(!label_goingUp){
				displayLabel.setLocation(label_PositionX, label_PositionY += label_AccelerationY);
			}
		}
	}

}
