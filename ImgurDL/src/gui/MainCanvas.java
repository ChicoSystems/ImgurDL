package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Main Canvas of ImgurDL, Includes a Header and Display Area.
 * @author Isaac Assegai
 *
 */
public class MainCanvas extends JPanel{

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

	
	/**
	 * Adds a picture to the displayArea's queue;
	 * @param img The picture to add.
	 */
	private void addPicToQueue(BufferedImage img){
		displayArea.addPicToQueue(img);
	}
	
	
	
	
	
}
