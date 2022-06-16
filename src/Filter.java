import java.awt.image.BufferedImage;

public interface Filter {

	public BufferedImage process(BufferedImage image1, BufferedImage image2);

}
