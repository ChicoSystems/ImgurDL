package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Header of the GUI. Includes logo and input area.
 * @author Isaac Assegai
 *
 */
public class ImgurDLHeader extends JPanel{

	
	static String LOGO_URL = "http://i.imgur.com/loDFxIK.jpg";
	public MainCanvas parent; 	/** The MainCanvas of GUI. Parent of me. */
	public BufferedImage logo; 			/** The logo of ImgurDL. */
	public InputArea inputArea; /** The input area of ImgurDL. */
	
	/**
	 * Constructor
	 */
	public ImgurDLHeader(MainCanvas p){
		super();
		parent = p;
		logo = getLogo(LOGO_URL);
		inputArea = new InputArea(this);
		setupHeader();
	}
	
	/**
	 * Sets up header
	 */
	
	private void setupHeader(){
		setPreferredSize(new Dimension(500, 190));
		super.setVisible(true);
		setBackground(Color.red);
		setLayout(new BorderLayout());
		//add(BorderLayout.NORTH,logo);
		add(inputArea, BorderLayout.SOUTH);
		parent.add(this, BorderLayout.NORTH);
	}
	
	/**
	 * Returns the logo from the internet
	 * @param s URL of logo
	 * @return The Logo
	 */
	private BufferedImage getLogo(String s){
		BufferedImage img = null;
 	    try {
			URL u = new URL(s);
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    return img;
	}
	
	@Override
	/**
	 * Paint logo on screen.
	 */
    public void paintComponent(Graphics g) {
		g.setColor(new Color(36, 35, 33));
		g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(logo, 0, -1, null); // see javadoc for more info on the parameters
        paintStats(g);
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
		 System.out.println(parent.parent.parent.downloader.statsTracker.totalPagesSearched);
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Pages Searched: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalPagesSearched), 170, 27);
	        g.setColor(c);
	 }
	 
	 private void paintNumTotal(Graphics g){
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Total in Folder: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalInFolder), 170, 15);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the number of downloaded pics to the screen
	  * @param g
	  */
	 private void paintNumDownloaded(Graphics g){
		 	Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Downloaded: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalPicsDownloaded), 380, 15);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the Current Queue Size to the screen
	  * @param g
	  */
	 private void paintQueueSize(Graphics g){
		 Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Queue: " + String.valueOf(parent.parent.parent.downloader.queue.size()), 10, 27);
	        g.setColor(c);
	 }
	 
	 /**
	  * Paint the current Kbits per sec to the screen
	  * @param g The Graphics Context
	  */
	 private void paintKbits(Graphics g){
			Color c = g.getColor();
		 	g.setColor(Color.GREEN);
	        g.drawString("Kbit/s: " + String.valueOf(parent.parent.parent.downloader.statsTracker.totalKbPerSec).substring(0,String.valueOf(parent.parent.parent.downloader.statsTracker.totalKbPerSec).length()/2 ), 10, 15);
	        g.setColor(c);
	 }
	
	
}
