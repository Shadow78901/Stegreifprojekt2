import java.awt.image.BufferedImage;

public interface Filter {

	public BufferedImage process(String value, BufferedImage ...image);

}
