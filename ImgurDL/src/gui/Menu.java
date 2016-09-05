package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Used to let the user choose certain config options.
 * @author Isaac Assegai
 *
 */
public class Menu extends JMenuBar{
	private JMenu menu;
	private JMenuItem menuItem;
	private Color bgColor = Color.RED;
	private Color fgColor = Color.GREEN;
	
	public Menu(){
		super();
		menu = new JMenu("Options");
		menuItem = new JMenuItem("Set DL Directory");
		
		menu.add(menuItem);
		add(menu);
		
		//set look and feel
		setBackground(bgColor);
		this.setBorderPainted(false);
		
		menu.setBackground(bgColor);
		menu.setForeground(fgColor);
		menu.setBorderPainted(false);
		
		menuItem.setBorderPainted(false);
		menuItem.setBackground(bgColor);
		menuItem.setForeground(fgColor);
	}
	
	 /* @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(bgColor);
	        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

	    }*/
	
}
