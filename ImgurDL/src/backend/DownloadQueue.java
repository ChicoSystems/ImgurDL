package backend;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This Is the Download Queue used by ImgurGalleryDownloader
 * @author Isaac Assegai
 *
 */
public class DownloadQueue extends LinkedList<String> implements Queue<String>{

	FileTracker fileTracker;
	LinkedList<String>done;
	public ImgurGalleryDownloader parent;
	/**
	 * Constructor
	 */
	public DownloadQueue(ImgurGalleryDownloader p){
		super();
		parent = p;
		fileTracker = new FileTracker();
		done = new LinkedList<String>();
	}
	
	/**
	 * Adds a String to the Done List
	 * @param s The String To add
	 */
	synchronized public void addDone(String s){
		done.add(s);
	}
	
	/**
	 * Checks to see if a String has already been added to the queue
	 * @param s The String To Check
	 * @return True or False
	 */
	synchronized public boolean isDone(String s){
		if(done.contains(s)){
			return true;
		}else{
			return false;
		}
			
	}
	
	/**
	 * Adds a String to the Queue
	 * @param s the String to add to the queue
	 */
	synchronized public void enQueue(String s){
		
		if(!isDone(s) && !fileTracker.doesExist(s)){
			addDone(s);
			super.addLast(s);
		}
		
	}
	
	/**
	 * Adds an array of strings to the Queue, one string at a time.
	 * @param sA The array of strings to add
	 */
	synchronized public void enQueue(String[] sA){
		
		for(int i = 0; i < sA.length; i++){
			enQueue(sA[i]);
		}
		
	}
	
	/**
	 * Removes the first item up from the queue.
	 * @return The First Item Up
	 */
	synchronized public String deQueue(){
		//System.err.println("Queued: " + super.size() + " Done: " + done.size());
		parent.updateStats();
		return super.removeFirst();
	}
	
	/**
	 * Shows the first item up, without removing it.
	 * @return Shows the first item up.
	 */
	synchronized public String peek(){
		return super.peekFirst();
	}
	
}
