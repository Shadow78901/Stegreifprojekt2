import java.awt.image.BufferedImage;

/**
 * Ein Monochrome Filter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class MonochromeFilter extends PixelFilter {

	private boolean gotMask = false;

	/**
	 * Diese Methode wird benutzt um das Bild zu filtern
	 *
	 * @param value dieses Argument wird nicht berücksichtigt
	 * @param image ein Array von BufferedImages, das benutzt wird
	 * @return das gefiltertete Bild
	 */
	public BufferedImage process(String value, BufferedImage... image) {

		if (image.length >= 2) {
			gotMask = true;
		}

		int width = image[0].getWidth();
		int height = image[0].getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		if (gotMask) {
			System.out.println(image[1].getRGB(50, 60));
		}

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {

				if (gotMask) {
					int tempCol = image[1].getRGB(i, j);
					if (tempCol == -16777216) {

					} else {
						result.setRGB(i, j, image[0].getRGB(i, j));
					}
				}
			}
		}

		return result;
	}

}
