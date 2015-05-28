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

	
	static String LOGO_URL = "http://i.imgur.com/xliw7W1.png";
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
		super.setVisible(true);
		setPreferredSize(new Dimension(500, 250));
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
        g.drawImage(logo, (getWidth()/8), 60, null); // see javadoc for more info on the parameters
    }
	
	
}
