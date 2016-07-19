package backend;

/**
 * A Thread that Processes the Download Queue, by downloading Items in the Queue.
 * @author Isaac Assegai
 *
 */
public class DownloadQueueProcessor extends Thread{
	
	static int TIME_SLEEP_IF_EMPTY = 500;
	static int TIME_PAUSE = 100;
	ImgurGalleryDownloader parent;
	PictureDownloader picDownloader; //The object that downloads a picture
	
	
	/**
	 * Constructor
	 */
	public DownloadQueueProcessor(ImgurGalleryDownloader p){
		parent = p;
		picDownloader = new PictureDownloader(this);
	}
	
	/**
	 * The Processors main loop.
	 */
	public void run(){
		while(parent.isRunning){ //make sure program is still running
			while(parent.queue.isEmpty()){ 
				try {
					Thread.sleep(TIME_SLEEP_IF_EMPTY); //if DL Queue is empty, pause for half a second before trying again
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(!parent.queue.isEmpty()){
				
				String picToDL = parent.queue.deQueue();
				//if(picToDL != null)
					picDownloader.download(picToDL);
				
			}
			
			try {
				Thread.sleep(TIME_PAUSE); //Allow thread to rest some time, before checking again.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
