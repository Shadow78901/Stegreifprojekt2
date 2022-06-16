import java.awt.image.BufferedImage;

public class ColorBandFilter extends PixelFilter {

	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage image2) {
		int width = image1.getWidth();
		int height = image1.getHeight();
		int rgbDec;
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		int rgbDecResult;
		
		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				rgbDec = image1.getRGB(i, j);
				rgbDecResult = determineColor(rgbDec, ColorBand.BLUE);
				result.setRGB(i, j, rgbDecResult);

			}
		}

		return result;

	}

	private int determineColor(int rgbDec, ColorBand color) {
		String rgbBin = Integer.toBinaryString(rgbDec);
		String resultBin = "";
		final String emptyByte = "00000000";
		final String fullByte = "11111111";

		// Das Ergebnis besteht aus 4 Byte. Das 1. Byte ist immer durchgängig 1
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
