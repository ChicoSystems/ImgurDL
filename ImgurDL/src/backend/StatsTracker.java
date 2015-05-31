package backend;

/**
 * Tracks Programs Statistics
 * @author Isaac Assegai
 *
 */
public class StatsTracker {
	
	public float bitsPerSec;
	public float totalBitsPerSec;
	public float kbPerSec;
	public float totalKbPerSec;
	public float mbPerSec;
	public float totalMbPerSec;
	public long currentbits;
	public long totalbits; //Keeps track of total bits downloaded
	public double elapsedTime;
	public double startTime;
	public double currentTime;
	public double runTime;
	public int totalPicsDownloaded;
	public int totalPagesSearched;
	public int totalInFolder;
	ImgurGalleryDownloader parent;
	
	
	public StatsTracker(ImgurGalleryDownloader p){
		parent = p;
		totalInFolder = 0;
		totalPicsDownloaded = 0;
		totalPagesSearched = 0;
		bitsPerSec = 0;
		totalBitsPerSec = 0;
		totalKbPerSec = 0;
		mbPerSec = 0;
		totalMbPerSec = 0;
		currentbits = 0;
		totalbits = 0; //Keeps track of total bits downloaded
		elapsedTime = 0;
		startTime = 0;
		currentTime = System.currentTimeMillis();
		runTime = 0;
	}
	
	public void update(){
		currentTime = System.currentTimeMillis();
		elapsedTime = currentTime - startTime;
		runTime += elapsedTime;
		//System.err.println("ElapsedTime: " + elapsedTime + " : " + "RunTime: " + runTime);
		//System.err.println("Totalbits: " + totalbits + "Currentbits: " + currentbits);
		bitsPerSec = (float) (currentbits / elapsedTime) ;
		totalBitsPerSec = (float) (totalbits / runTime);
		kbPerSec = bitsPerSec / 1024;
		totalKbPerSec = totalBitsPerSec;
		mbPerSec = kbPerSec / 1024;
		totalMbPerSec = totalKbPerSec / 1024;
		//System.out.println("KB/S: " + totalKbPerSec + " ElapsedTime: " + elapsedTime);
		
		//update pic counts
		totalInFolder = totalPicsDownloaded + parent.queue.fileTracker.downloadedFiles.size();
		
		//reset things that need reset
		startTime = currentTime;
	}

}
