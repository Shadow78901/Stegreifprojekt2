import java.awt.image.BufferedImage;

/**
 * Ein Pixelfilter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public abstract class PixelFilter implements Filter {

	@Override
	public BufferedImage process(String value, BufferedImage... input) {
		int width = input[0].getWidth();
		int height = input[0].getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		boolean gotMask = false;

		if (input.length >= 2) {
			gotMask = true;
		}

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {

				if (gotMask && input[1].getRGB(i, j) != -1) {
					result.setRGB(i, j, input[0].getRGB(i, j));
				}

				else {
					result.setRGB(i, j, calculate(input[0].getRGB(i, j)));
				}

			}
		}

		return result;

	}

	protected abstract int calculate(int rgbDec);

}
