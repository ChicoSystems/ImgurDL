package backend;

import java.io.File;

public class Dir {
	
	
	public static void main (String[] args){
		String userdir = System.getProperty("user.dir");
		System.err.println(userdir);
		
		String[] list = dirlist(userdir);
		for(int i = 0; i < list.length; i++)System.out.println(list[i]	);
	}
	
	public static String[] dirlist(String fname){
			File dir = new File(fname);
		    String[] chld = dir.list();
		    if(chld == null){
		    	System.out.println("Specified directory does not exist or is not a directory.");
		    	return null;
		    }else{
		    	return chld;
		    }
	 }
		
}