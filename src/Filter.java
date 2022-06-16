import java.awt.image.BufferedImage;

/**
 * Ein Interface das für alle Filter verwendet wird
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 * 
 */
public interface Filter {

	/**
	 * 
	 * @param value ein Wert der von den einzelnen Filtern jeweils anders
	 *              interpretiert wird
	 * @param image die zu übergebenden Bilder
	 * @return das gefilterte Bild
	 */
	public BufferedImage process(String value, BufferedImage... image);

}
