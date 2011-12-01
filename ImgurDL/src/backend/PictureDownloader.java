package backend;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

/**
 * This class downloads pictures from a web address.
 * @author Isaac Assegai
 *
 */
public class PictureDownloader {
	
	DownloadQueueProcessor parent = null; /** Reference to Parent QueueProcessor. */
	
	/**
	 * Individual Launcher.
	 */
	 public static void main(String[] args){
	      PictureDownloader pd = new PictureDownloader(null);
	      //pd.download("http://i.imgur.com/f0b2Lb.jpg");
	      String[] list = {"http://i.imgur.com/f0b2Lb.jpg", 
	    		  			"http://i.imgur.com/PvsEs.jpg",
	    		  			"http://i.imgur.com/Wthb3.jpg",
	    		  			"http://i.imgur.com/TqRiA.jpg"};
	      pd.download(list);
	      
	    }
	 
	 /**
	  * Constructor
	  * @param p Parent of this object
	  */
	 public PictureDownloader(DownloadQueueProcessor p){
		 parent =  p;
	 }
	 
	 /**
	  * Downloads a list of pictures, list is full of string typed urls.
	  * @param s List of URLS of type string.
	  */
	 public void download(String[] s){
		 for(int i = 0; i < s.length; i++){
	    	  download(s[i]);
	      }
	 }
	 
	 /**
	  * Download a picture from a url.
	  * @param s The url of the picture. STRING
	  */
	 public void download(String s){
		 try {
			 if(s == null){
				 System.err.println("malformed url");
				 return;
			 }
			 System.out.println("Downloading: " + s);
			   BufferedImage img;
	    	   URL u = new URL(s);
	    	   img = ImageIO.read(u);
	           String fileName = getFileName(s);
	           String ext = getExt(s);
	           System.out.println(fileName + "." + ext);
	           //ADD PIC TO GUI QUEUE HERE
	           if(parent.parent.parent != null){
	           parent.parent.parent.gui.mainCanvas.displayArea.addPicToQueue(resize(img, 125, 125));
	           }
	           save(img, fileName, ext);
	           parent.parent.statsTracker.totalPicsDownloaded++;
	       } catch (IOException e) {
	    	   System.err.println(e);
	       }
	 }
	 
	 /**
	  * Resizes a buffered image to new size.
	  * @param img Image to resize.
	  * @param newW New Width
	  * @param newH new Height
	  * @return resized image.
	  */
	 public BufferedImage resize(BufferedImage img, int newW, int newH) {  
	        int w = img.getWidth();  
	        int h = img.getHeight();  
	        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());  
	        Graphics2D g = dimg.createGraphics();  
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	        g.dispose();  
	        return dimg;  
	    }  
	 
	 /**
	  * Gets the Pictures filename from the URL
	  * @param s The URL of the picture. STRING
	  * @return The name of the picture.
	  */
	 private String getFileName(String s){
		 StringTokenizer t = new StringTokenizer(s, "/") ;
		 //System.err.println("tokens: " + t.countTokens());
		 t.nextToken();
		 t.nextToken();
		 //System.err.println(t.nextToken());
		// System.err.println( );
		 
		 return new StringTokenizer(t.nextToken(), ".").nextToken();
	 }
	 
	 /**
	  * Get the extention of the picture from the URL
	  * @param s The URL of the picture. STRING
	  * @return The extention of the picture, STRING.
	  */
	 private String getExt(String s){
		 StringTokenizer t = new StringTokenizer(s, "/") ;
		 //System.err.println("tokens: " + t.countTokens());
		 t.nextToken();
		 t.nextToken();
		 //System.err.println(t.nextToken());
		// System.err.println( );
		 
		 StringTokenizer st = new StringTokenizer(t.nextToken(), ".");
		 st.nextToken();
		 return st.nextToken();
	 }
	 
	 /**
	  * Save an Image to the current Directory.
	  * @param image The Image to Save.
	  * @param fileName The filename of the Image.
	  * @param ext The Extention of the Image.
	  */
	 private void save(BufferedImage image, String fileName, String ext) {
	        File file = new File(fileName + "." + ext);
	        try {
	        	ByteArrayOutputStream tmp = new ByteArrayOutputStream();
	            ImageIO.write(image, "png", tmp);
	            tmp.close();
	            Integer contentLength = tmp.size();
	            parent.parent.addBits(contentLength);
	            ImageIO.write(image, ext, file);  // ignore returned boolean
	        } catch(IOException e) {
	            System.out.println("Write error for " + file.getPath() +
	                               ": " + e.getMessage());
	        }
	    }

}
