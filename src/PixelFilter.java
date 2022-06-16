import java.awt.image.BufferedImage;

public class PixelFilter implements Filter {

	@Override
	public BufferedImage process(BufferedImage image1, BufferedImage image2) {
      return null;
	}

	protected int caculate(int[] pixel, int[] maskPixel, int index, int width, int height) {
		return -1;
	}

}
