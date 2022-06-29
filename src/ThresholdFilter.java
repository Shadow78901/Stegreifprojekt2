import java.awt.image.BufferedImage;

/**
 * Ein Threshold Filter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class ThresholdFilter extends PixelFilter {

	private int threshhold;
	
	
	
	/**
	 * 
	 * @param rgbDec Der Farbwert als Dezimalzahl
	 * @return Die Helligkeit
	 */
	protected int calculate(int rgbDec) {
		String rgbBin = Integer.toBinaryString(rgbDec);
		String redBin = "";
		String greenBin = "";
		String blueBin = "";
		int redDec;
		int greenDec;
		int blueDec;
		int brightness;

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

		brightness = (int) (Math
				.sqrt(0.299 + Math.pow(redDec, 2) + 0.578 * Math.pow(greenDec, 2) + 0.114 * Math.pow(greenDec, 2)));

		if (brightness < threshhold) {
			return 0; // schwarz

		} else {
			return 16777215; // weiß
		}

	}

}
