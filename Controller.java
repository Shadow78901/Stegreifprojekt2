import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

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
	 * @param args Die args werden ber√ºcksichtigt
	 */
	public static void main(String[] args) {
		// Deklarierungen und Initalisierungen
		BufferedImage picture = null;
		BufferedImage result = null;
		BufferedImage mask = null;
		String filtername = null;
		String inputImage = null;
		String maskImage = null;
		String outputImage = null;
		Filter filter = null;

		// HashMap generieren
		HashMap<String, Filter> filterMap = new HashMap<String, Filter>();
		filterMap.put("monochrom", new MonochromeFilter());
		filterMap.put("colorband_red", new ColorBandFilter("Red"));
		filterMap.put("colorband_green", new ColorBandFilter("Green"));
		filterMap.put("colorband_blue", new ColorBandFilter("Blue"));
		filterMap.put("threshold_128", new ThresholdFilter(128));
		filterMap.put("threshold_192", new ThresholdFilter(192));
		
		ChainFilter chainfilter = new ChainFilter();
		chainfilter.add(filterMap.get("threshold_128"));
		chainfilter.add(filterMap.get("colorband_blue"));
		filterMap.put("chainfilter",chainfilter);
		

		// Argumente interpretieren
		filtername = args[0];
		inputImage = args[1];

		if (args[2].equals("-m")) {
			maskImage = args[3];
			outputImage = args[4];

		} else {
			outputImage = args[2];
		}

		// Filter wird ausgew‰hlt
		filter = filterMap.get(filtername);

		// Bild einlesen
		try {
			picture = ImageIO.read(new File(inputImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Maske einlesen
		if (maskImage != null) {
			try {
				mask = ImageIO.read(new File(maskImage));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Image loaded as Mask not found");
			}
		}

		// Filter bearbeitet die Bilder und speichert sie ab
		if (!filtername.equals("test")) {
			if (mask == null) {
				result = filter.process(picture);
			} else {
				result = filter.process(picture, mask);
			}

			try {
				ImageIO.write(result, "bmp", new File(outputImage));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {

			Iterator<String> iterator = filterMap.keySet().iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();

				if (mask == null) {
					result = filterMap.get(key).process(picture);
				} else {
					result = filterMap.get(key).process(picture, mask);
				}

				try {
					ImageIO.write(result, "bmp", new File(outputImage + "_" + key + ".bmp"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}

	}
}
