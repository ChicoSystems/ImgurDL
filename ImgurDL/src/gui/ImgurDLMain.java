package gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import backend.ImgurGalleryDownloader;

/**
 * ImgurDL's Main launcher
 * @author Isaac Assegai
 *
 */
public class ImgurDLMain extends JFrame{
	
	static int WIDTH_MAINCANVAS = 510;
	static int HEIGHT_MAINCANVAS = 720;
	static String TITLE = "Loadur";
	public ImgurDLGUI gui; /** THE GUI */
	public ImgurGalleryDownloader downloader; /** The Downloader */
	public boolean isRunning;

	/**
	 * The launcher.
	 */
	public static void main(String[] args) {
		System.err.println("MAIN RUNNING");
		new ImgurDLMain();
		
	}
	
	/**
	 * The launchers class
	 */
	public ImgurDLMain(){
		super(TITLE);
		isRunning = true;
		gui = new ImgurDLGUI(this); //create gui.
		gui.start(); // Start gui in new thread.
		downloader = new ImgurGalleryDownloader(this); //Start Image Downloader object.
		setupFrame();
	}
	
	/**
	 * Sets up the frame that the mainCanvas will be inserted in.
	 */
	public void setupFrame(){
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
           	 isRunning = false;
                System.exit(0);
            }
        });
		
		 this.setPreferredSize(new Dimension(WIDTH_MAINCANVAS,HEIGHT_MAINCANVAS));
		 super.pack();
		 setLocation(200, 10);
		 this.setLayout(getLayout());
		 setVisible(true);
		 
		 
	}
}
