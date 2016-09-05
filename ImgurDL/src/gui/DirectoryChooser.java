package gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DirectoryChooser extends JFrame{
	ImgurDLMain parent;
	public JFileChooser jFileChooser;
	
	public DirectoryChooser(ImgurDLMain p){
		super("Choose Directory");
		parent = p;
		//this.setSize(50, 50);
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new java.io.File("."));
		jFileChooser.setDialogTitle("Choose DL Directory");
		jFileChooser.setFileSelectionMode(jFileChooser.DIRECTORIES_ONLY);
		jFileChooser.setAcceptAllFileFilterUsed(false);
		//jFileChooser.setPreferredSize(parent.getPreferredSize());
		jFileChooser.setVisible(true);
		this.add(jFileChooser);
		//this.setSize(parent.getSize());
		this.setSize(parent.getSize().width, parent.getSize().height/2);
		this.setVisible(false);
		
	}
	

}
