import java.awt.image.BufferedImage;

/**
 * Ein ColorBandFilter durch den das Bild auf einen Farbwert reduziert wird
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class ColorBandFilter extends PixelFilter {

	private ColorBand color;

	@Override
	/**
	 *Diese Methode wird benutzt um das Bild zu filtern
	 *
	 *@param value der Farbwert auf den das Bild reduziert werden soll
	 *@param image ein Array von BufferedImages, das benutzt wird
	 *@return das gefiltertete Bild
	 */
	public BufferedImage process(String value, BufferedImage... image) {
		int width = image[0].getWidth();
		int height = image[0].getHeight();
		int rgbDec;
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		int rgbDecResult;

		if (value.equals("red") || value.equals("Red")) {
			color = ColorBand.RED;
		} else if (value.equals("blue") || value.equals("Blue")) {
			color = ColorBand.BLUE;
		} else if (value.equals("green") || value.equals("Green")) {
			color = ColorBand.GREEN;
		} else {
			System.out.println("no color choosen");
		}

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				rgbDec = image[0].getRGB(i, j);
				rgbDecResult = determineColor(rgbDec, color);
				result.setRGB(i, j, rgbDecResult);

			}
		}

		System.out.println(value);
		return result;
	}

	/**
	 * 
	 * @param rgbDec Ein RGB Wert wird in seiner Dezimal Variante mitgegeben
	 * @param color Die Farbe auf die das Bild reduziert werden soll
	 * @return der neue Farbwert als Dezimalzahl
	 */
	private int determineColor(int rgbDec, ColorBand color) {

		String rgbBin = Integer.toBinaryString(rgbDec);
		String resultBin = "";
		final String emptyByte = "00000000";
		final String fullByte = "11111111";

		// Das Ergebnis besteht aus 4 Byte. Das 1. Byte ist immer durchg�ngig 1
		resultBin += fullByte;

		if (color == ColorBand.RED) {
			for (int i = 8; i < 16; i++) {
				resultBin += rgbBin.charAt(i);
			}

			resultBin += emptyByte;
			resultBin += emptyByte;
		} else if (color == ColorBand.GREEN) {
			resultBin += emptyByte;

			for (int i = 16; i < 24; i++) {
				resultBin += rgbBin.charAt(i);
			}

			resultBin += emptyByte;
		} else if (color == ColorBand.BLUE) {
			resultBin += emptyByte;
			resultBin += emptyByte;

			for (int i = 24; i < 32; i++) {
				resultBin += rgbBin.charAt(i);
			}
		} else {
			return -1;
		}

		return Integer.parseUnsignedInt(resultBin, 2);

	}

}
