
package u1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIDualThreads
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	
	private JPanel pnlMove;		// The panel to move display in
	private JPanel pnlRotate;	// The panel to move graphics in
	
	private JPanel pnlTriangle;
	
	//DisplayThread
	private DisplayThread displayThread;
	private JLabel displayLabel;

	
	//RotateThread
	private RotateThread rotateThread;
	

	/**
	 * Constructor
	 */
	public GUIDualThreads(){
		
	}
	
	/**
	 * Starts the application
	 */
	public void Start(){
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
//		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI(){
		//Initialize threads and objects
		
		displayLabel = new JLabel("AY LMAO", JLabel.CENTER);
		displayLabel.setLocation((0), 0);
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 12, 222, 269);
		pnlDisplay.setLayout(null);
		pnlDisplay.add(displayLabel);
		
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		btnDisplay.addActionListener(new DisplayStartListener());
		pnlDisplay.add(btnDisplay);
		
		
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		btnDStop.addActionListener(new DisplayStopListener());
		btnDStop.setEnabled(false);
		pnlDisplay.add(btnDStop);
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlMove.add(displayLabel);
		
		pnlDisplay.add(pnlMove);
		
		// Then add this to main window
		frame.add(pnlDisplay);
		
		// The moving graphics outer panel
		pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 12, 222, 269);
		pnlTriangle.setLayout(null);
	
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		btnTriangle.addActionListener(new TriangleStartListener());
		
		pnlTriangle.add(btnTriangle);
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		btnTStop.addActionListener(new TriangleStopListener());
		btnTStop.setEnabled(false);
		pnlTriangle.add(btnTStop);
		
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		
		// Add this to main window
		frame.add(pnlTriangle);
		

	}
	
	/**
	 * This class represent a Listener that waits
	 * for a specific button to be pressed and then
	 * starts or stops the corresponding thread. 
	 */
	private class TriangleStartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		    if(rotateThread == null || !rotateThread.isAlive()){
		    	rotateThread = new RotateThread((Graphics2D)pnlTriangle.getGraphics());
		    	rotateThread.start();
		    	
				btnTStop.setEnabled(true);
				btnTriangle.setEnabled(false);
		    }
		}
	}
	
	/**
	 * This class represent a Listener that waits
	 * for a specific button to be pressed and then
	 * starts or stops the corresponding thread. 
	 */
	private class TriangleStopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * For some reason the graphics object interferes with the
			 * thread, making the interrupt-method unreliable. Therefore
			 * i use the stop-method instead 
			 */
		  //rotateThread.interrupt();
			rotateThread.stop();
			
			btnTStop.setEnabled(false);
			btnTriangle.setEnabled(true);

		}	
	}
	
	/**
	 * This class represent a Listener that waits
	 * for a specific button to be pressed and then
	 * starts or stops the corresponding thread. 
	 */
	private class DisplayStartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(displayThread == null || !displayThread.isAlive()){
				displayThread = new DisplayThread(displayLabel);
				displayThread.start();
				
				btnDStop.setEnabled(true);
				btnDisplay.setEnabled(false);
			}
		}
	}
	
	/**
	 * This class represent a Listener that waits
	 * for a specific button to be pressed and then
	 * starts or stops the corresponding thread. 
	 */
	private class DisplayStopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			displayThread.interrupt();
			
			btnDisplay.setEnabled(true);
			btnDStop.setEnabled(false);
		}
		
	}
}
