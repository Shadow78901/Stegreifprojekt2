import java.awt.image.BufferedImage;

public class MonochromeFilter extends PixelFilter {

	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage image2) {
		int width = image1.getWidth();
		int height = image1.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				result.setRGB(i, j, image1.getRGB(i, j));

			}
		}

		return result;
	}

}
