package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * StandardButton.java
 * @author Isaac Assegai
 *
 */
public class DownloadButton extends JButton {

	private Color startColor = new Color(192, 192, 192);
	private Color endColor = new Color(82, 82, 82);
	private Color rollOverColor = new Color(158, 223, 52);
	private Color pressedColor = new Color(137, 198, 36);
	private int outerRoundRectSize = 10;
	private int innerRoundRectSize = 8;
	private GradientPaint GP;
	private boolean isDownloading;
	
	/**
	 * Constructor
	 * @param text The Name of the Button.
	 */
	public DownloadButton(String text) {
		super();
		setText(text);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFont(new Font("Thoma", Font.BOLD, 12));
		setForeground(Color.WHITE);
		//setFocusable(false);
		setDownloading(false);
	}
	
	/**
	 * Constructor
	 * @param startColor The Untouched Color of the Button
	 * @param endColor	The End color of the button
	 * @param rollOverColor The color of the button when rolled over
	 * @param pressedColor The color of the button when pressed.
	 */
	public DownloadButton(Color startColor, Color endColor,
			Color rollOverColor, Color pressedColor) {
		super();
		this.startColor = startColor;
		this.endColor = endColor;
		this.rollOverColor = rollOverColor;
		this.pressedColor = pressedColor;
		setForeground(Color.WHITE);
		//setFocusable(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setDownloading(false);

	}
	
	/**
	 * Paints The button components.
	 */
		public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight();
		int w = getWidth();
		ButtonModel model = getModel();
		
		if (!model.isEnabled()) {
			setForeground(Color.GRAY);
			GP = new GradientPaint(0, 0, new Color(192,192,192), 0, h, new Color(192,192,192),
					true);
		}else{
			setForeground(Color.WHITE);
			if (model.isRollover()) {
				GP = new GradientPaint(0, 0, rollOverColor, 0, h, rollOverColor,
						true);
	    	} else {
				GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
			}
		}
		
		g2d.setPaint(GP);
		GradientPaint p1;
		GradientPaint p2;
		if (model.isPressed()) {
			GP = new GradientPaint(0, 0, pressedColor, 0, h, pressedColor, true);
			g2d.setPaint(GP);
			p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
					new Color(100, 100, 100));
			p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
					new Color(255, 255, 255, 100));
		} else {
			p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
					new Color(0, 0, 0));
			p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
					h - 3, new Color(0, 0, 0, 50));
			GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
		}
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
				h - 1, outerRoundRectSize, outerRoundRectSize);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		g2d.fillRect(0, 0, w, h);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
				outerRoundRectSize);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
				innerRoundRectSize);
		g2d.dispose();

		super.paintComponent(g);
	}

	/**
	 *  This method sets the Actual Background Color of the Button
	 */
	public void setStartColor(Color color) {
		startColor = color;
	}

	/**
	 *  This method sets the Pressed Color of the Button
	 */
	public void setEndColor(Color pressedColor) {
		endColor = pressedColor;
	}

	/**
	 * @return  Starting Color of the Button
	 */
	public Color getStartColor() {
		return startColor;
	}

	/**
	 * @return  Ending Color of the Button
	 */
	public Color getEndColor() {
		return endColor;
	}

	/**
	 * Custom Button Demo Launcher
	 * @param args The args
	 */
	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Buttons Demo");
		frame.setLayout(new FlowLayout());
		DownloadButton standardButton = new DownloadButton("Standard Button");
		frame.add(standardButton.getButtonsPanel());
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setSize(700, 85);
		frame.setVisible(true);
	}
	
	public JPanel getButtonsPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		DownloadButton standardButton = new DownloadButton("Standard Button");
		standardButton.setPreferredSize(new Dimension(130, 28));
		
		DownloadButton rollOverButton = new DownloadButton("RollOver Button");
		rollOverButton.setPreferredSize(new Dimension(130, 28));
	
		DownloadButton disabledButton = new DownloadButton("Disable Button");
		disabledButton.setPreferredSize(new Dimension(130, 28));
		disabledButton.setEnabled(false);
		
		DownloadButton pressedButton = new DownloadButton("Pressed Button");
		pressedButton.setPreferredSize(new Dimension(130, 28));
	
		panel.add(standardButton);
		panel.add(rollOverButton);
		panel.add(disabledButton);
		panel.add(pressedButton);
		return panel;
	
	}

	public boolean isDownloading() {
		return isDownloading;
	}

	public void setDownloading(boolean isDownloading) {
		if(isDownloading){
			setText("Stop");
			startColor = new Color(192, 192, 192);
			endColor = new Color(82, 82, 82);
			rollOverColor = new Color(223, 52, 61);
			pressedColor = new Color(137, 198, 36);
		}else{
			startColor = new Color(192, 192, 192);
			endColor = new Color(82, 82, 82);
			rollOverColor = new Color(158, 223, 52);
			setText("Download");
			pressedColor = new Color(137, 198, 36);
		}
		this.isDownloading = isDownloading;
	}
}