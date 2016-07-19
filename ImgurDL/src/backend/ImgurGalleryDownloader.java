package backend;
import gui.ImgurDLGUI;
import gui.ImgurDLMain;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
	static int SIM_DLS = 2; //Simultaneous downloads allowed at one time.
	ImgurDLMain parent = null; //The Optional GUI Parent
	public StatsTracker statsTracker;
	public DownloadQueue queue;
	DownloadQueueProcessor[] qProcessors;
	boolean isRunning;
	String gal;

	/**
	 * @param args The Imgur Gallery To download.
	 */
	public static void main(String[] args) {
		System.err.println("MAIN RUNNING");
		ImgurGalleryDownloader dl = new ImgurGalleryDownloader();
		dl.download("https://api.imgur.com/3/album/pTL2I/images");
		dl.start();
		//dl.download(args[0]);
	}
	
	/**
	 * Constructor Utilized by Standalone.
	 */
	public ImgurGalleryDownloader(){
		isRunning = true;
		queue = new DownloadQueue(this);
		statsTracker = new StatsTracker(this);
		
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
		/*
		String currentPage = getFirstPage(gal);
		while(currentPage != null && isRunning){
			String[] foundLinks = extractLinks(currentPage); //list of links found on current page.
		
			//if(foundLinks != null){
			queue.enQueue(foundLinks);
			//}
			
			currentPage = getNextPage(currentPage);
			
		}
		*/
		try {
			ArrayList<String>foundLinks = apiGetLinks("cdee2b1e3c354ec", "https://api.imgur.com/3/gallery/search?q="+gal);
			//Enqueue the links to be downloaded
			
			/*for(int i = 0; i < foundLinks.size(); i++){
				queue.enQueue(foundLinks.get(i));
			}*/
			
			//for(int i = 0; i < foundLinks.length; i++){
			//	queue.enQueue(foundLin);
				
			//}
		//	getImgurContent("cdee2b1e3c354ec", "https://api.imgur.com/3/gallery/3IE5Y");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("current page = null");
	}
	
	public ArrayList<String> apiGetLinks (String Client_ID, String url) throws IOException {

	    // get your own Id and Secret by registering at https://api.imgur.com/oauth2/addclient
	    //String Client_ID = "cdee2b1e3c354ec";
	   // String Client_Secret = "e8e181529ccab63c31c06b6b67ba86dbc739c3ef"; 

	    //String YOUR_USERNAME = "isaacassegai"; // enter your imgur username
	    String YOUR_REQUEST_URL = url;

	    URL imgURL = new URL(YOUR_REQUEST_URL);
	    HttpURLConnection conn = (HttpURLConnection) imgURL.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Authorization", "Client-ID " + Client_ID);

	    BufferedReader bin = null;
	    bin = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	//below will print out bin
	    String jsonString = "";
	    String line;
	    while ((line = bin.readLine()) != null){
	    	//System.out.println(line);
	    	jsonString = jsonString + line;
	    }
	        
	    bin.close();
	    System.out.println("jsonString: "+jsonString);
	    //String [] foundLinks = new String[200];
	    ArrayList<String> foundLinks = new ArrayList<String>();
	    JSONParser parser = new JSONParser();
	    try{
	         JSONObject obj = (JSONObject) parser.parse(jsonString);
	         JSONArray data = (JSONArray) obj.get("data");
	         for(int i = 0; i < data.size(); i++){
	        	 JSONObject img = (JSONObject)data.get(i);
	        	 String link = (String) img.get("link");
	        	 if(linkIsAlbum(link)){
	        		 String albumID = getAlbumID(link);
	        		 foundLinks.addAll(apiGetLinks(Client_ID, "https://api.imgur.com/3/album/"+albumID+"/images"));
	        	 }else{
	        		 foundLinks.add(link);
	        		 queue.enQueue(link); //this will do the downloading.
	        	 }
	        	 
	        	 System.out.println(link);
	        	 
	         }
	      }catch(ParseException pe){
			
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
	    return foundLinks;
	}

	/**
	 * We will have already confirmed that link is an album
	 * before this is called. This will take everything
	 * after the /a/ in the link and return it.
	 * @param link
	 * @return
	 */
	String getAlbumID(String link){
		int i = link.lastIndexOf("/");
		return link.substring(i+1);
	}
	
	/**
	 * Checks a String link to see if it is an album
	 * it will be an album if string contains /a/
	 * @param link
	 * @return
	 */
	boolean linkIsAlbum(String link){
		if(link.contains("/a/")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Uses the api to find image links from a 
	 * gallery or a album, etc.
	 * @param page The URL of the ALBUM/GALLERY
	 * @return Array of url strings where pics are
	 */
	String [] api_getLinks(String page){
		try{
		String url = "https://api.imgur.com/3/account/isaacassegai";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", "Mozilla/5.0");
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Authorization", "Client-ID cdee2b1e3c354ec");
		

		String urlParameters = "";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString()); 
		}catch(IOException e){
			System.out.println(e);
		
		}
		return null;
	}
	
	public static String getImgurContent(String clientID, String theURL) throws Exception {
	    URL url;
	    url = new URL(theURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	   // String data = URLEncoder.encode("image", "UTF-8") + "="
	    //        + URLEncoder.encode(theURL, "UTF-8");

	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "Client-ID " + clientID);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type",
	            "application/x-www-form-urlencoded");

	    conn.connect();
	    StringBuilder stb = new StringBuilder();
	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    //wr.write(data);
	    wr.flush();

	    // Get the response
	    BufferedReader rd = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null) {
	        stb.append(line).append("\n");
	    }
	    wr.close();
	    rd.close();

	    return stb.toString();
	}
	
	/**
	 * Extracts the Imgur Image Links from an Imgur Gallery Page
	 * @param currentPageURL The URL of the current Gallery Page
	 * @return An array of links extracted from page.
	 */
	public String[] extractLinks(String currentPageURL){
		//System.err.println("ExtractLinks : " + currentPageURL);
		String pageHTML = extractHTML(currentPageURL);
		//if(pageHTML == null)return null;
		String[] extractedLinks = findLinks(pageHTML, currentPageURL);
		return extractedLinks;
		
	}
	
	/**
	 * Extract the HTML from a URL
	 * @param currentPageURL The URL
	 * @return THE HTML STRING
	 */
	private String extractHTML(String currentPageURL){
		//System.err.println("Extract HTML : " + currentPageURL);
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
	    	  System.err.println("Malformed URL: " + currentPageURL);
	        System.err.println(ex);
	        return "0";
	      } 
	      catch (IOException ex) {
	        System.err.println(ex); 
	        return "0";
	      }
		
		
		try {
			List<String> lines = Arrays.asList(theLine);
			Path file = Paths.get(currentPageURL.substring(currentPageURL.length()-1, currentPageURL.length())+".html");
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	      
	      return theLine;
	}
	
	/**
	 * Find the Links in an Imgur Gallery Page
	 * @param pageHTML The Gallery Page to search
	 * @return An array of Links in String format
	 */
	private String[] findLinks(String pageHTML, String currentPageURL){
		System.err.println("FindLinks : " + pageHTML);
		ArrayList<String>links = new ArrayList<String>();
		
		//imgur has changed their html classes, we'll use this to make changes easier
		String postDelimiter = "id=\"imagelist";
		
		if(pageHTML.contains(postDelimiter)){
			sindex = pageHTML.indexOf(postDelimiter); //find where posts start
			int oldsindex = sindex;
			links.add(getNextLink(pageHTML)); //adds the next link to list
			while(sindex >= oldsindex){
				oldsindex = sindex;
				links.add(getNextLink(pageHTML)); //adds the next link to list
				updateStats();
				if(queue.size() >= QUEUE_THRESH*2){
					try {
						//System.err.println("Main BIG Sleeping: " + TIME_PAUSE*queue.size()*queue.size());
						Thread.sleep(TIME_PAUSE*queue.size()*queue.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(queue.size() >= QUEUE_THRESH){
					try {
						//System.err.println("Main Little Sleeping: " + TIME_PAUSE*queue.size());
						Thread.sleep(TIME_PAUSE*queue.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		String[] foundLinks = new String[links.size()-1];
		for(int i = 0; i < links.size()-1; i++){
			foundLinks[i] = "http://";
			foundLinks[i] += links.get(i);
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
		//System.err.println("GetNextLink: " + sindex);
		//sindex = pageHTML.indexOf("<img alt=\"\" src=\"", sindex); 
		sindex = pageHTML.indexOf("<img alt src=", sindex); 
		sindex = pageHTML.indexOf("i.imgur", sindex); //find where next link starts
		int eindex = pageHTML.indexOf("\" />", sindex); //this is where next link ends
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
	statsTracker.totalPagesSearched++;
	int pageNum = Integer.valueOf(currentPage.substring(currentPage.length()-1));//last char of string
	pageNum +=1;
	String newPage = currentPage.substring(0, currentPage.length()-1)+pageNum;
	return newPage;
	
	//return currentPage;
		
	}

	public synchronized void addBits(int b){
		statsTracker.totalbits += b;
		statsTracker.currentbits = b;
	}
	
	public void updateStats(){
		statsTracker.update();
	}

}
