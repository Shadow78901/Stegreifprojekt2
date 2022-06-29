import java.awt.image.BufferedImage;

/**
 * Ein Interface das fuer alle Filter verwendet wird
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 * 
 */
public interface Filter {

	/**
	 * 
	 * @param value ein Wert der von den einzelnen Filtern jeweils anders
	 *              interpretiert wird
	 * @param input das zu uebergebende Bild und die Maske
	 * @return das gefilterte Bild
	 */
	public BufferedImage process(BufferedImage... input);

}
