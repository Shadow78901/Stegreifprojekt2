import java.awt.image.BufferedImage;

/**
 * Ein Threshold Filter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class ThresholdFilter extends PixelFilter {

	@Override
	/**
	 * Diese Methode wird benutzt um das Bild zu filtern
	 *
	 * @param value dieses Argument wird nicht berücksichtigt
	 * @param image ein Array von BufferedImages, das benutzt wird
	 * @return das gefiltertete Bild
	 */
	public BufferedImage process(String value, BufferedImage... image) {
		int width = image[0].getWidth();
		int height = image[0].getHeight();
		int rgbDec;
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		int brightness;

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				rgbDec = image[0].getRGB(i, j);
				brightness = brightnessCalculator(rgbDec);

				if (brightness < 128) {
					// Schwarz
					result.setRGB(i, j, 0);
				} else {
					// Weiß
					result.setRGB(i, j, 16777215);
				}

			}
		}

		return result;
	}

	/**
	 * 
	 * @param rgbDec Der Farbwert als Dezimalzahl
	 * @return Die Helligkeit
	 */
	public int brightnessCalculator(int rgbDec) {
		String rgbBin = Integer.toBinaryString(rgbDec);
		String redBin = "";
		String greenBin = "";
		String blueBin = "";
		int redDec;
		int greenDec;
		int blueDec;

		for (int i = 8; i < 16; i++) {
			redBin += rgbBin.charAt(i);
		}

		for (int i = 16; i < 24; i++) {
			greenBin += rgbBin.charAt(i);
		}

		for (int i = 24; i < 32; i++) {
			blueBin += rgbBin.charAt(i);
		}

		redDec = Integer.parseUnsignedInt(redBin, 2);
		greenDec = Integer.parseUnsignedInt(greenBin, 2);
		blueDec = Integer.parseUnsignedInt(blueBin, 2);

		return (int) (Math
				.sqrt(0.299 + Math.pow(redDec, 2) + 0.578 * Math.pow(greenDec, 2) + 0.114 * Math.pow(greenDec, 2)));
	}

}
