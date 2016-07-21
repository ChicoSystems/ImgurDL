package gui;

import java.awt.image.BufferedImage;

/**
 * The Class that manages the GUI
 * @author Isaac Assegai
 *
 */
public class GUIManager {
	
	ImgurDLGUI parent;	/** The GUI Parent. */
	DownloadThread dlThread; /** The rules ran in dlThread */
	boolean ranAlready = false;
	
	/**
	 * Constructor.
	 */
	public GUIManager(ImgurDLGUI p){
		parent = p;
		dlThread = new DownloadThread();
	}
	
	/**
	 * Updates the display area with the current contents of it's queue.
	 */
	/*public void updateScreen(){
		//put code here to update screen to current queue.
		//parent.mainCanvas.displayArea.
		
	}*/
	
	/**
	 * Adds a picture to the current display queue.
	 * @param img The image to add.
	 */
	public void addPicToQueue(BufferedImage img){
		//put code here to add img to current queue.
		parent.mainCanvas.displayArea.addPicToQueue(img);
	}
	
	public void downloadGallery(String gallery){
		//dlThread.stop();
		dlThread.setGallery(gallery);
		if(!ranAlready){
			
			dlThread.start();
			ranAlready = true;
		}
		
	}
	
	/**
	 * Private Download Thread. Made to keep GUI from locking up.
	 * @author Isaac Assegai
	 *
	 */
	private class DownloadThread extends Thread{
		String gallery;
		boolean ranAlready = false;
		public DownloadThread(){
			//alreadyStarted = false;
		}
		
		/**
		 * Sets the gallery to download.
		 * @param s The gallery url - STRING
		 */
		public void setGallery(String s){
			gallery = s;
			parent.parent.downloader.download(s);
			
			if(!ranAlready){
				parent.parent.downloader.start();
				ranAlready = true;
			}
			
			
			
		}
		
		/**
		 * RUN
		 */
		public void run() {
			// TODO Auto-generated method stub
		//	parent.parent.downloader.download(gallery);
			//parent.parent.downloader.start();
			
		}
		
		public void start(){
			//if(!alreadyStarted){
				super.start();
			//	alreadyStarted = true;
			//}
		}
		
	}

}
