import java.awt.image.BufferedImage;

public class MonochromeFilter extends PixelFilter {

	@Override
	
	

	public BufferedImage process(String value, BufferedImage... image) {

		int width = image[0].getWidth();
		int height = image[0].getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				result.setRGB(i, j, image[0].getRGB(i, j));

			}
		}

		return result;
	}

}
