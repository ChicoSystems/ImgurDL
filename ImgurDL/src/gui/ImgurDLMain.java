package gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import backend.ImgurGalleryDownloader;

/**
 * ImgurDL's Main launcher
 * @author Isaac Assegai
 *
 */
public class ImgurDLMain extends JFrame{
	
	static int WIDTH_MAINCANVAS = 510;
	static int HEIGHT_MAINCANVAS = 720;
	static String TITLE = "Loadur";
	public ImgurDLGUI gui; /** THE GUI */
	public ImgurGalleryDownloader downloader; /** The Downloader */
	private boolean isRunning;
	public String newerVersionName;
	public String newerVersionLink = "https://sourceforge.net/projects/imgurdl/";

	/**
	 * The launcher.
	 */
	public static void main(String[] args) {
		System.err.println("MAIN RUNNING");
		new ImgurDLMain();
		
	}
	
	/**
	 * The launchers class
	 */
	public ImgurDLMain(){
		super(TITLE);
		isRunning = true;
		gui = new ImgurDLGUI(this); //create gui.
		gui.start(); // Start gui in new thread.
		downloader = new ImgurGalleryDownloader(this); //Start Image Downloader object.
		setupFrame();
		if(isNewerVersion()){
			gui.mainCanvas.updateLabel.setVisible(true);
			gui.mainCanvas.setupUpdateLabel(newerVersionLink, "Version "+newerVersionName+" available. Click HERE.");
		}else{
			gui.mainCanvas.updateLabel.setVisible(false);
		}
	}
	
	/**
	 * Sets up the frame that the mainCanvas will be inserted in.
	 */
	public void setupFrame(){
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
           	 isRunning = false;
                System.exit(0);
            }
        });
		
		 this.setPreferredSize(new Dimension(WIDTH_MAINCANVAS,HEIGHT_MAINCANVAS));
		 super.pack();
		 setLocation(200, 10);
		 this.setLayout(getLayout());
		 setVisible(true);
		  
	}
	
	public boolean getRunning(){
		return isRunning;
	}
	
	public void setRunning(boolean run){
		System.out.println("Set Main Running");
		downloader.setRunning(run);
		//if(!run){
			//downloader = new ImgurGalleryDownloader(this); //Start Image Downloader object.
		//}
		isRunning = run;	
	}
	
	private boolean isNewerVersion(){
		JSONObject obj = apiHome("imgurdl/version", "", "GET");
		JSONArray data = (JSONArray) obj.get("imgurdlVersion");
		JSONObject item = (JSONObject) data.get(0);
		String newestVer = (String)item.get("ver");
		String newestLink = (String)item.get("link");
		System.out.println("check newest version");
		if(newerVersionBigger(newestVer, getVersion())){
			newerVersionName = newestVer;
			newerVersionLink = newestLink;
			return true;
		}else{
			return false;
		}
	}
	
	private boolean newerVersionBigger(String newestVersion, String oldVersion){
		StringTokenizer newestToken = new StringTokenizer(newestVersion, ".");
		StringTokenizer oldToken = new StringTokenizer(oldVersion, ".");
		while(newestToken.hasMoreTokens()){
			int newest = Integer.parseInt( newestToken.nextToken());
			int oldest = 0;
			if(oldToken.hasMoreTokens()){
				//compare next tokens
				oldest = Integer.parseInt( oldToken.nextToken());
			}else{
				oldest = 0;
			}
			
			if(newest > oldest){
				return true;
			}
		}
		return false;
	}
	
	public String getVersion(){
		return "0.1.2";
	}
	
	public JSONObject apiHome(String endpoint, String urlParameters, String method){
		 String YOUR_REQUEST_URL = "http://chicosystems.com:3000/api/"+endpoint;
		// String YOUR_REQUEST_URL = "http://chicosystems.com:3000/api/imgurdl/adduse";
		 URL imgURL;
		
		 String os = System.getProperty("os.name");
		 String ver = getVersion();
		 urlParameters += "&os="+os+"&ver="+ver;
		    
			try {
				// Desktop.getDesktop().browse(new URI("http://localhost:3000/api/imgurdl/test/adduse"));
				imgURL = new URL(YOUR_REQUEST_URL);
				 HttpURLConnection conn = (HttpURLConnection) imgURL.openConnection();
				 conn.setDoOutput( true );   
				 conn.setRequestMethod(method);
				    //conn.setRequestProperty("time", "2002002002");
				    //conn.setRequestProperty("term", "this is the term");
				    //conn.setRequestProperty("os", "windows");
				// String urlParameters  = "&term="+term+"&os="+os+"&ver="+ver;
				 byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
				 int    postDataLength = postData.length;
				 conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
				 conn.setRequestProperty( "charset", "utf-8");
				 conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
				    try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
				    	   wr.write( postData );
				    	}
				    //conn.setRequestProperty("Authorization", "Client-ID " + Client_ID);

				    BufferedReader bin = null;
				    bin = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				//below will print out bin
				    String jsonString = "";
				    String line;
				    while ((line = bin.readLine()) != null){
				    	jsonString = jsonString + line;
				    }
				        
				    bin.close();
				    
				    JSONParser parser = new JSONParser();
				    try{
				         JSONObject obj = (JSONObject) parser.parse(jsonString);
				         System.out.println(obj.toString());
				         return obj;
				      }catch(ParseException pe){
						
				         System.out.println("position: " + pe.getPosition());
				         System.out.println(pe);
				      }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.toString());
			//} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
			return null;
		   
	}
}
