import java.awt.image.BufferedImage;

/**
 * Ein Pixelfilter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class PixelFilter implements Filter {

	@Override
	/**
	 * 
	 * @param value ein Wert der von den einzelnen Filtern jeweils anders
	 *              interpretiert wird
	 * @param image die zu übergebenden Bilder
	 * @return das gefilterte Bild
	 */
	public BufferedImage process(String value, BufferedImage... image) {
		// TODO Auto-generated method stub
		return null;
	}

	protected int caculate(int[] pixel, int[] maskPixel, int index, int width, int height) {
		return -1;
	}

}
