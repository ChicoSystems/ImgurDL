package gui;

import java.awt.image.BufferedImage;

/**
 * The GUI
 * @author Isaac Assegai
 *
 */
public class ImgurDLGUI extends Thread{

	static int GUI_PAUSE_TIME = 250;
	public ImgurDLMain parent = null; /** Reference to Launcher */
	public MainCanvas mainCanvas = null; /** The Display of ImgurDL */
	public GUIManager guiManager = null; /** The GUI Manager. */
	
	/*
	 * Constructor
	 */
	public ImgurDLGUI(ImgurDLMain p){
		parent = p;
		mainCanvas = new MainCanvas(this);
		guiManager = new GUIManager(this);
	}
	
	/**
	 * Main Loop of GUI.
	 */
	public void run(){
		while(parent.getRunning()){
		//	updateScreen();
			sleep(GUI_PAUSE_TIME);
		}
	}
	
	/**
	 * Add's a picture to the display queue.
	 * @param img The picture to add.
	 */
	public void addPicToQueue(BufferedImage img){
		guiManager.addPicToQueue(img);
	}
	
	/**
	 * Updates the GUI Display.
	 */
	/*private void updateScreen(){
		guiManager.updateScreen();
	}*/
	
	/**
	 * Causes the GUI Thread to Sleep for Given Amount of Time.
	 * @param time Amount of time to sleep, in Milliseconds.
	 */
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("GUI_SLEEP Has FAILED. ");
		}
	}
	
	
}
