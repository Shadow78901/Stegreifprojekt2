import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Der Controller mit dem die Filter und Bilder aufgerufen werden
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class Controller {
	/**
	 * Die Main Methode
	 * 
	 * @param args Die args werden berücksichtigt
	 */
	public static void main(String[] args) {
		BufferedImage picture = null;
		BufferedImage result = null;
		BufferedImage mask = null;

		String filtername = args[0];
		String filtervalue = args[1];
		String inputImage = args[2];
		String outputImage = args[3];
		String maskImage = null;

		Filter filter;

		if (args.length > 4) {
			maskImage = args[4];

			try {
				mask = ImageIO.read(new File(maskImage));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Image loaded as Mask not found");
			}
		}

		// filter werden initialisiert

		if (filtername.equals("ColorBandFilter")) {
			filter = new ColorBandFilter(filtervalue);
		} else if (filtername.equals("MonochromeFilter")) {
			filter = new MonochromeFilter();
		} else {
			filter = new ThresholdFilter();
		}

		// hier muss Hashmap hin

		try {
			picture = ImageIO.read(new File(inputImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (mask == null) {
			result = filter.process(filtervalue, picture);
		} else {
			result = filter.process(filtervalue, picture, mask);
		}

		try {
			ImageIO.write(result, "bmp", new File(outputImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
