package backend;

import java.util.LinkedList;

/**
 * 
 * @author Isaac Assegai
 * This Class examines the programs folder, adds the name of
 * every picture to a list. Before a picture is added to the
 * queue this list will be checked, to make sure the picture
 * has not already been downloaded.
 *
 */
public class FileTracker {
	LinkedList<String> downloadedFiles ;
	
	public FileTracker(){
		downloadedFiles = new LinkedList<String>();
		populateList(Dir.dirlist(System.getProperty("user.dir")));
	}
	
	private void populateList(String[] list){
		for(int i = 0; i < list.length; i++){
			if(list[i].endsWith(".jpg") || list[i].endsWith(".gif") || list[i].endsWith(".png")
				|| list[i].endsWith(".jpeg") || list[i].endsWith(".tiff")){
				downloadedFiles.add(list[i]);
				System.err.println(list[i]);
			}
				
		}
	}
	
	public boolean doesExist(String file){
	
		if(file != null){
		file = file.substring(file.length()-9, file.length());
		}
		return downloadedFiles.contains(file);
		
	}
	
	
	
	

}
