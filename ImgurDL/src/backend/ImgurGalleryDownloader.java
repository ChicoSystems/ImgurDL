package backend;
import gui.ImgurDLGUI;
import gui.ImgurDLMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * ImgurGalleryDownloader can be utilized to download a gallery from the
 * website Imgur.com.  
 * It can be utilized in standalone mode via the command line.
 * Using the url to the Imgur gallery desired as the sole argument. 
 * It can also be utilized by a gui, doing the actual work the gui displays.
 * @author Isaac Assegai
 *
 */
public class ImgurGalleryDownloader extends Thread{
	
	int sindex;
	
	static int QUEUE_THRESH = 40;
	static int TIME_PAUSE = 20;
	static int SIM_DLS = 5; //Simultaneous downloads allowed at one time.
	ImgurDLMain parent = null; //The Optional GUI Parent
	DownloadQueue queue;
	DownloadQueueProcessor[] qProcessors;
	boolean isRunning;
	String gal;
	

	/**
	 * @param args The Imgur Gallery To download.
	 */
	public static void main(String[] args) {
		System.err.println("MAIN RUNNING");
		ImgurGalleryDownloader dl = new ImgurGalleryDownloader();
		dl.download("http://imgur.com/r/EarthPorn");
		dl.start();
		//dl.download(args[0]);
	}
	
	/**
	 * Constructor Utilized by Standalone.
	 */
	public ImgurGalleryDownloader(){
		isRunning = true;
		queue = new DownloadQueue();
		
		
		//Initialize the qProcessors according to SIM_DLS number
		qProcessors = new DownloadQueueProcessor[SIM_DLS];
		for(int i = 0; i < SIM_DLS; i++){
			qProcessors[i] = new DownloadQueueProcessor(this); //Initiaze each processor
			qProcessors[i].start(); //start each processor
		}
	}
	
	/**
	 * Constructor Utilized by GUI.
	 */
	public ImgurGalleryDownloader(ImgurDLMain p){
		this();
		parent = p;
		
	}
	
	/**
	 * Downloads the specified Imgur Gallery.
	 * @param gallery The Gallery to Download
	 */
	public void download(String gallery){
		System.err.println("Download: " + gallery);
		//Navigate to the first page.
		gal = gallery;
		
		
		//while you have a good page, as long as no 404, or 500 codes occur, 
		//and as long as we have not reached the last page of the gallery.
		
		
	}
	
	/**
	 * Runs in thread, allows buttons to be pressed on the gui.
	 */
	public void run(){
		String currentPage = getFirstPage(gal);
		while(currentPage != null && isRunning){
			String[] foundLinks = extractLinks(currentPage); //list of links found on current page.
			queue.enQueue(foundLinks);
			currentPage = getNextPage(currentPage);
		}
	}
	
	
	
	/**
	 * Extracts the Imgur Image Links from an Imgur Gallery Page
	 * @param currentPageURL The URL of the current Gallery Page
	 * @return An array of links extracted from page.
	 */
	public String[] extractLinks(String currentPageURL){
		System.err.println("ExtractLinks : " + currentPageURL);
		String pageHTML = extractHTML(currentPageURL);
		String[] extractedLinks = findLinks(pageHTML);
		return extractedLinks;
		
	}
	
	/**
	 * Extract the HTML from a URL
	 * @param currentPageURL The URL
	 * @return THE HTML STRING
	 */
	private String extractHTML(String currentPageURL){
		System.err.println("Extract HTML : " + currentPageURL);
		String theLine = "";
		try {
	        URL u = new URL(currentPageURL);

	        InputStream is = u.openStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	       
	        do{
	        	theLine += br.readLine();
	        } while(br.readLine() != null);
	      }
	      catch (MalformedURLException ex) {
	        System.err.println(ex);
	        return "0";
	      } 
	      catch (IOException ex) {
	        System.err.println(ex); 
	        return "0";
	      }
	      
	      return theLine;
	}
	
	/**
	 * Find the Links in an Imgur Gallery Page
	 * @param pageHTML The Gallery Page to search
	 * @return An array of Links in String format
	 */
	private String[] findLinks(String pageHTML){
		System.err.println("FindLinks : " + pageHTML);
		ArrayList<String>links = new ArrayList<String>();
		
		if(pageHTML.contains("<div class=\"posts\">")){
			sindex = pageHTML.indexOf("<div class=\"posts\">"); //find where posts start
			int oldsindex = sindex;
			links.add(getNextLink(pageHTML)); //adds the next link to list
			while(sindex >= oldsindex){
				links.add(getNextLink(pageHTML)); //adds the next link to list
				if(queue.size() >= QUEUE_THRESH*2){
					try {
						System.err.println("Main BIG Sleeping: " + TIME_PAUSE*queue.size()*queue.size());
						Thread.sleep(TIME_PAUSE*queue.size()*queue.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(queue.size() >= QUEUE_THRESH){
					try {
						System.err.println("Main Little Sleeping: " + TIME_PAUSE*queue.size());
						Thread.sleep(TIME_PAUSE*queue.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		String[] foundLinks = new String[links.size()];
		for(int i = 0; i < links.size()-1; i++){
			foundLinks[i] = links.get(i);
		}
		return foundLinks;
	}
	
	/**
	 * Returns the next image link in an Imgur Gallery page
	 * @param pageHTML The Gallery page searched
	 * @param sindex The index to start searching for the next link at.
	 * @return The next Link Found
	 */
	private String getNextLink(String pageHTML){
		System.err.println("GetNextLink: " + sindex);
		sindex = pageHTML.indexOf("<img alt=\"\" src=\"", sindex); 
		sindex = pageHTML.indexOf("http", sindex); //find where next link starts
		int eindex = pageHTML.indexOf("\" title", sindex); //this is where next link ends
		if(sindex == -1 || eindex == -1) return "0";
		String nextLink = ((String) pageHTML.subSequence(sindex, eindex)); //this is the next link
		int dotIndex = nextLink.indexOf('.', 15);
		nextLink = nextLink.substring(0, dotIndex-1)+nextLink.substring(dotIndex, nextLink.length());
		return nextLink;
	}
	
	/**
	 * Returns the first page of an Imgur Gallery. URL returned as string.
	 * @param gallery The Imgur Gallery Desired
	 * @return The URL of the first page of the Gallery, in a String
	 */
	public String getFirstPage(String gallery){
		return gallery+"/page/0";
	}
	
	/**
	 * Returns the next page of the gallery.
	 * @param currentPage The current Page of the gallery.
	 * @return The next page of the gallery.
	 */
private String getNextPage(String currentPage){
	int pageNum = Integer.valueOf(currentPage.substring(currentPage.length()-1));//last char of string
	pageNum +=1;
	String newPage = currentPage.substring(0, currentPage.length()-1)+pageNum;
	return newPage;
	
	//return currentPage;
		
	}

}
