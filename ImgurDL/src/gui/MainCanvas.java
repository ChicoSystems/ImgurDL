package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Main Canvas of ImgurDL, Includes a Header and Display Area.
 * @author Isaac Assegai
 *
 */
public class MainCanvas extends JPanel{
    public JLabel updateLabel;
	public Color BACKGROUND_COLOR = new Color(18,18,17);
	public ImgurDLGUI parent; /** The GUI parent Class. */
	public ImgurDLHeader header; /** The Header That has logo and Input area. */
	public ImgurDLDisplayArea displayArea; /** The Area where Downloaded Pictures are displayed. */
	
	/**
	 * Constructor
	 */
	public MainCanvas(ImgurDLGUI p){
		super();
		
		parent = p;
		setupCanvas();
		header = new ImgurDLHeader(this);
		displayArea = new ImgurDLDisplayArea(this);
		setupUpdateLabel(parent.parent.newerVersionLink, "");
		updateLabel.setVisible(false);
	}
	
	/**
	 * Sets up the size and location of the mainCanvas.
	 */
	public void setupCanvas(){
		//setPreferredSize(new Dimension(500,1000));
		this.setBackground(BACKGROUND_COLOR);
		setLayout(new BorderLayout());
		parent.parent.add(this);
	}
	
	public void setupUpdateLabel(String link, String text){
		updateLabel = new JLabel(text, SwingConstants.CENTER);
		updateLabel.setForeground(Color.RED);
		updateLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Yay you clicked me " + link);
                if (Desktop.isDesktopSupported()) {
                    try {
                      Desktop.getDesktop().browse(new URI(parent.parent.newerVersionLink));
                    } catch (IOException | URISyntaxException e1) { System.out.println(e1.getMessage());}
                  } else { System.out.println("links not supported"); }
                updateLabel.setVisible(false);
            }

        });
		this.add(updateLabel, BorderLayout.SOUTH);
	}

	
	/**
	 * Adds a picture to the displayArea's queue;
	 * @param img The picture to add.
	 */
	private void addPicToQueue(BufferedImage img){
		displayArea.addPicToQueue(img);
	}
	
	
	
	
	
}
