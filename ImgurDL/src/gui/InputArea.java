package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;

/**
 * The input area. Includes a textbox and a button.
 * @author Isaac Assegai
 *
 */
public class InputArea extends JPanel{
	static String TEXTFIELD_STRING = "Type Search Here";
	ImgurDLHeader parent; /** The Header, Parent of this Class. */
	JPanel statsPanel; /** Panel to display stats on.*/
	JLabel panelLabel;
	JTextField textField; /** The Box that user inputs Text into. */
	DownloadButton button; /** The button you press to download. */
	//boolean beenPressed = false;
	
	/**
	 * Constructor
	 */
	public InputArea(ImgurDLHeader p){
		super();
		setLayout(new FlowLayout());
		parent = p;
		setupInputArea();
		setupTextField();
		setupButton();
		setupStatsPanel();
	}
	/**
	 * Setup Input area's button.
	 */
	public void setupButton(){
		
		button = new DownloadButton("Download");
		add(button);

		button.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	if(!button.isDownloading()){
            		//We are not currently downloading, but we want to download.
            		 //Execute when button is pressed
                	parent.parent.parent.parent.downloader.statsTracker.startTime = System.currentTimeMillis();
                    System.out.println("You clicked the button");
                    String gallery = textField.getText();
                    gallery = gallery.toLowerCase();
                   // if(!beenPressed){
                    button.setDownloading(true);
                		parent.parent.parent.guiManager.downloadGallery(gallery);
                		
                    //    beenPressed = true;
                	//}
            	}else{
            		//We are already downloading, we need to stop, and change the button.
            		button.setDownloading(false);
            		
            	}
               
                /*
                if(gallery.endsWith("/")){ //remove last /
                	gallery = gallery.substring(0, gallery.length()-1);
                }
                if(!gallery.startsWith("http")){
                	parent.parent.parent.mainCanvas.header.inputArea.textField.setText("Not a HTTP address. Try Another");
                }else if(!gallery.startsWith("http://imgur.com/")){
                	parent.parent.parent.mainCanvas.header.inputArea.textField.setText("Not an Imgur Gallery. Try Another");
                }else{
                	
                	//if(!beenPressed){
                		parent.parent.parent.guiManager.downloadGallery(gallery);
                        beenPressed = true;
                	//}
                }
                */
            }
        });
	}
	
	/**
	 * Setup The input area.
	 */
	public void setupInputArea(){
		this.setBackground(new Color(31, 30, 27));
		
	}
	
	public void setupStatsPanel(){
		statsPanel = new JPanel();
		
		panelLabel = new JLabel("Stats");
		panelLabel.add(statsPanel);
	}
	
	/**
	 * Setup the input area's textfield.
	 */
	public void setupTextField(){
		
		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(8,30));
		textField.setBackground(new Color(31, 30, 27));
		textField.setForeground(Color.WHITE);
		textField.setBorder(null);
		//textField.setBorder(MetalBorders.getTextFieldBorder());
		textField.setBorder(BorderFactory.createLineBorder(new Color(143, 143, 143)));
		textField.setFont(new Font("Thoma", Font.BOLD, 16));
		textField.setText(TEXTFIELD_STRING);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		//textField.setPreferredSize(new Dimension(5,50));
		add(textField);
	
	
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
