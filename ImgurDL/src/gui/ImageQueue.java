package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * The Image Queue to be displayed by the gui.
 * @author Isaac Assegai
 *
 */
public class ImageQueue extends LinkedList<BufferedImage>{
	
	//static String BLANK_PIC = "http://i.imgur.com/FZXu3.png"; //normal
	static String BLANK_PIC = "http://i.imgur.com/QmsF6.png"; //yellow
	ImgurDLDisplayArea parent; /** The Display Area. Parent of This Class. */
	
	/**
	 * Constuctor
	 */
	public ImageQueue(ImgurDLDisplayArea p){
		super();
		parent = p;
		fillQueue();
	}
	
	/**
	 * Fills the Queue with blank pics.
	 */
	private void fillQueue(){
		BufferedImage blankPic = getPic(BLANK_PIC);
		for(int i = 0; i <= 16; i++){
			addPic(blankPic);
		}
	}
	
	/**
	 * Adds a picture the the queue. If more than 16 pictures
	 * are present it removes the first picture before adding them.
	 * @param img The picture to add.
	 */
	public void addPic(BufferedImage img){
		if(super.size() > 16){
			super.remove(0);
		}
		
		super.addLast(img);
	}
	
	/**
	 * Retrieve a picture from the internet.
	 * @param s The URL of the picture. STRING
	 * @return The Buffered image of the picture.
	 */
	private BufferedImage getPic(String s){
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

}
