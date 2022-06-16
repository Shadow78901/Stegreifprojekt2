import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Controller {
	public static void main(String[] args) {
		BufferedImage picture = null;
		BufferedImage result = null;
		BufferedImage mask = null; //ak
		
		String filtername = args[0];
		String filtervalue = args[1];
		String inputImage = args[2];
		String outputImage = args[3];
		
		Filter filter;
		
		if ( args.length > 4) {
			String maskImage = args[4];
			System.out.println(args[4]);
			
			try {
				mask = ImageIO.read(new File(maskImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Image loaded as Mask not found");
			}
		} else {
			System.out.println("no mask");
		}
		

		//filter werden initialisiert
		
		if (filtername.equals("ColorBandFilter")) {
			filter = new ColorBandFilter();
		} else if (filtername.equals("MonochromeFilter")) {
			filter = new MonochromeFilter();
		} else {
			filter = new ThresholdFilter();
		}

		// hier muss Hashmap hin 
		
		
		// file wird eingelesen
		
		try {
			picture = ImageIO.read(new File(inputImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//neues bild wird mit ausgewählten filter gebaut
	
		result = filter.process(filtervalue, picture, mask);
		try {
			ImageIO.write(result, "bmp", new File(outputImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
