package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * The Display Area of ImgurDL. This is where DL'd pictures are displayed.
 * @author Isaac Assegai
 *
 */
public class ImgurDLDisplayArea extends JPanel{

	MainCanvas parent; /** The MainCanvas, parent of this class. */
	ImageQueue imageQueue; /** The queue of Images displayed. */
	
	/**
	 * Constructor.
	 */
	public ImgurDLDisplayArea(MainCanvas p){
		super();
		parent = p;
		setupDisplayArea();
		
		imageQueue = new ImageQueue(this);
	}
	
	/**
	 * Sets up the display area.
	 */
	public void setupDisplayArea(){
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.green);
		parent.add(this, BorderLayout.CENTER);
		
	}

	/**
	 * Adds specified picture to queue.
	 * @param img
	 */
	public void addPicToQueue(BufferedImage img) {
		// TODO Auto-generated method stub
		imageQueue.addPic(img);
		repaint();
	}
	
	/**
	 * paints components.
	 */
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);

	      paintPictures(g);
	      paintStats(g);
	
		  // see javadoc for more info on the parameters
	 }
	 
	 /**
	  * Paint the program stats to the screen
	  * @param g
	  */
	 public void paintStats(Graphics g){
		    paintKbits(g);
		    paintQueueSize(g);
		    paintNumDownloaded(g);
		    paintNumTotal(g);
		    paintPagesSearched(g);
		 
	 }
	 
	 private void paintPagesSearched(Graphics g){
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Pages Searched: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalPagesSearched), 170, 20);
	        g.setColor(c);
	 }
	 
	 private void paintNumTotal(Graphics g){
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Total in Folder: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalInFolder), 170, 10);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the number of downloaded pics to the screen
	  * @param g
	  */
	 private void paintNumDownloaded(Graphics g){
		 	Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Downloaded: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalPicsDownloaded), 380, 10);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the Current Queue Size to the screen
	  * @param g
	  */
	 private void paintQueueSize(Graphics g){
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Queued: " + String.valueOf(parent.parent.parent.downloader.queue.size()), 10, 20);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the current Kbits per sec to the screen
	  * @param g The Graphics Context
	  */
	 private void paintKbits(Graphics g){
			Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Kbit/s: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalKbPerSec).substring(0,String.valueOf(parent.parent.parent.downloader.statsTracker.totalKbPerSec).length()/2 ), 10, 10);
	        g.setColor(c);
	 }
	 
	 
	 /**
	  * Paints all images in queue to screen.
	  * @param g The graphics context
	  */
	private void paintPictures(Graphics g){
		for(int i = 0; i <4; i++){
			for(int n = 0; n <4; n++){
				int j = convertBase4(i, n);
				    BufferedImage img = imageQueue.get(j);
					g.drawImage(img, n*img.getWidth(), i*img.getHeight(), null);
			}
			
				
		}
	}
	
	/**
	 * Converts a base 4 number to base 10
	 * @param i First Digit of base 4 num.
	 * @param n Second Digit of base 4 num.
	 * @return Base 10 num
	 */
	private int convertBase4(int i, int n){
		int tens = i*4;
		int ones = n*1;
		
		int answer = tens+ones;
		//System.err.println("tens: " + i + " Ones: " + n + " Answer: " + answer);
		return answer;
	}
	 
	 
}
