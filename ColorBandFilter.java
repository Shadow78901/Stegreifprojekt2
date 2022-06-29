import java.awt.image.BufferedImage;

/**
 * Ein ColorBandFilter durch den das Bild auf einen Farbwert reduziert wird
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class ColorBandFilter extends PixelFilter {

	private ColorBand color;

	public ColorBandFilter(String color) {

		if (color.equals("Red") || color.equals("red")) {
			this.color = ColorBand.RED;
		} else if (color.equals("Green") || color.equals("green")) {
			this.color = ColorBand.GREEN;
		} else {
			this.color = ColorBand.BLUE;
		}

	}

	/**
	 * 
	 * @param rgbDec Ein RGB Wert wird in seiner Dezimal Variante mitgegeben
	 * @param color  Die Farbe auf die das Bild reduziert werden soll
	 * @return der neue Farbwert als Dezimalzahl
	 */
	protected int calculate(int rgbDec) {
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
