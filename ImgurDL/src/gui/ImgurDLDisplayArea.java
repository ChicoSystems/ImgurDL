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
		setPreferredSize(new Dimension(500, 500));
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
		parent.header.repaint();
		repaint();
	}
	
	/**
	 * paints components.
	 */
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 paintPictures(g);
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
