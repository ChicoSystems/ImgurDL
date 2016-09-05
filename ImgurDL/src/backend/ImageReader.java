package backend;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.twelvemonkeys.image.ResampleOp;
import com.twelvemonkeys.imageio.*;;


public class ImageReader {
	public static void main(String [] args){
		URL u;
		try {
			u = new URL("7qa6yWv.jpg");
			BufferedImage input = ImageIO.read(u);
			//BufferedImage input = "7qa6yWv.jpg"; // Image to resample
			int width = 200, height = 200; // new width/height

			BufferedImageOp resampler = new ResampleOp(width, height, ResampleOp.FILTER_LANCZOS); // A good default filter, see class documentation for more info
			BufferedImage output = resampler.filter(input, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
