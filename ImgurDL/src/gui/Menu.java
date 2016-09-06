package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Used to let the user choose certain config options.
 * @author Isaac Assegai
 *
 */
public class Menu extends JMenuBar implements MouseListener{
	private ImgurDLMain parent;
	private JMenu menu;
	private JMenuItem menuItem;
	public JMenuItem updateItem;
	private Color bgColor;
	private Color fgColor = Color.GREEN;
	private Color clickedColor;
	
	public Menu(ImgurDLMain p){
		super();
		parent = p;
		bgColor = new Color(4, 4, 2, 255);
		fgColor = new Color(253, 253, 253, 255);
		clickedColor = new Color(100, 100, 100, 255);
		//menu = new JMenu("Options");
		menuItem = new JMenuItem("Set DL Directory");
		updateItem = new JMenuItem("New Version Available");
		updateItem.setVisible(false);
		//updateItem.setText(text);
		
		//menu.add(menuItem);
		//add(menu);
		add(menuItem);
		add(updateItem);
		
		//set look and feel
		setBackground(bgColor);
		this.setBorderPainted(false);
		
	//	menu.setBackground(bgColor);
		//menu.setForeground(fgColor);
		//menu.setBorderPainted(false);
		
		
		menuItem.setBorderPainted(false);
		menuItem.setBackground(bgColor);
		menuItem.setForeground(fgColor);
		
		menuItem.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				
				menuItem.setBackground(clickedColor);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("SetDLDirectory Menu Item Selected");
		parent.chooser.setSize(parent.getSize().width, parent.getSize().height/2);
		parent.chooser.setLocation(parent.getLocation().x, parent.getHeight()/4);
		//parent.chooser.setVisible(!parent.chooser.isVisible());
		//parent.chooser.jFileChooser.getFile
		//System.out.println(parent.chooser.jFileChooser.showDialog(parent.chooser, "Choose"));
		
		int returnVal = parent.chooser.jFileChooser.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    File yourFolder = parent.chooser.jFileChooser.getSelectedFile();
		    System.out.println("directory choosen: " + yourFolder);
		    parent.directoryName = yourFolder.toString();
		    //set new dl folder here dads talking to me
		}
		menuItem.setBackground(bgColor);
		
	}

	
	
	 /* @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(bgColor);
	        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

	    }*/
	
}
