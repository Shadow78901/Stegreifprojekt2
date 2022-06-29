import java.awt.image.BufferedImage;

/**
 * Ein Monochrome Filter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class MonochromeFilter extends PixelFilter {

	/**
	 * Diese Methode wird benutzt um das Bild zu filtern
	 *
	 * @param value dieses Argument wird nicht berücksichtigt
	 * @param image ein Array von BufferedImages, das benutzt wird
	 * @return das gefiltertete Bild
	 */

	protected int calculate(int rgbDec) {
		int r = (rgbDec >> 16) & 0xFF;
		int g = (rgbDec >> 8) & 0xFF;
		int b = (rgbDec & 0xFF);

		float rr = (float) Math.pow(r / 255.0, 2.2);
		float gg = (float) Math.pow(g / 255.0, 2.2);
		float bb = (float) Math.pow(b / 255.0, 2.2);

		float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);
		int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
		int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

		return gray;
	}

}
