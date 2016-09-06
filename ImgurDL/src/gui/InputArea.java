package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	public JTextField textField; 

	/** The Box that user inputs Text into. */
	public DownloadButton button; /** The button you press to download. */
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

		parent.parent.parent.parent.getRootPane().setDefaultButton(button);
		button.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	
            	if(!button.isDownloading()){
            		
            		reportHome(textField.getText());
            		
            		//We are not currently downloading, but we want to download.
            		 //Execute when button is pressed
                	parent.parent.parent.parent.downloader.statsTracker.startTime = System.currentTimeMillis();
                    System.out.println("You clicked the button");
                    String gallery = textField.getText();
                    //gallery = gallery.toLowerCase();
                    button.setDownloading(true);
                	parent.parent.parent.guiManager.downloadGallery(gallery);
                	parent.parent.parent.parent.setRunning(true);
                //	textField.transferFocusBackward();
                	textField.grabFocus();
                	
                	
            	}else{
            		parent.parent.parent.parent.menu.setVisible(true);
            		//We are already downloading, we need to stop, and change the button.
            		button.setDownloading(false);
            		parent.parent.parent.parent.setRunning(false);
            		textField.grabFocus();
            		//parent.parent.parent.parent.downloader.queue.
            		
            	}
               System.out.println("test");
            }
        });
	}
	
	/**
	 * Setup The input area.
	 */
	public void setupInputArea(){
		this.setBackground(new Color(31, 30, 27));
		
	}
	
	/**
	 * Reports to the server the button has been pressed.
	 * @param term The term the user searched for.
	 * We do this in a new thread to minimize wait time.
	 */
	public void reportHome(String term){
		(new Thread() {
			  public void run() {
					String urlParameters  = "&term="+term;
					parent.parent.parent.parent.apiHome("imgurdl/adduse", urlParameters, "POST");
			  }
			 }).start();
	
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
		//textField.setToolTipText("Tool tip for text field");
		
		//when user presses enter in textfield we want dl button pressed
		/*textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				button.press
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		
		
		//When textfield is focused we wan't it to select the whole thing.
		textField.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		        displayMessage("Focus gained", e);
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                textField.selectAll();
		            }
		        });
		      }

		      public void focusLost(FocusEvent e) {
		        displayMessage("Focus lost", e);
		      }

		      void displayMessage(String prefix, FocusEvent e) {
		        System.out.println(prefix
		            + (e.isTemporary() ? " (temporary):" : ":")
		            + e.getComponent().getClass().getName()
		            + "; Opposite component: "
		            + (e.getOppositeComponent() != null ? e.getOppositeComponent().getClass().getName()
		                : "null"));
		      }

		    });
		
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
	
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	
}
