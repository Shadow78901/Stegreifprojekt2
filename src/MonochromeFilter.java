import java.awt.image.BufferedImage;

/**
 * Ein Monochrome Filter
 * 
 * @author Artur Kechter, Nico Hunsicker, Atta Farsimadan
 *
 */
public class MonochromeFilter extends PixelFilter {

	private boolean gotMask = false;

	/**
	 * Diese Methode wird benutzt um das Bild zu filtern
	 *
	 * @param value dieses Argument wird nicht berücksichtigt
	 * @param image ein Array von BufferedImages, das benutzt wird
	 * @return das gefiltertete Bild
	 */
	public BufferedImage process(String value, BufferedImage... image) {

		if (image.length >= 2) {
			gotMask = true;
		} 
		
		int width = image[0].getWidth();
		int height = image[0].getHeight();
		
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		for (int i = 1; i < width; i++) {
			for (int j = 1; j < height; j++) {
				
				if(gotMask) {
					int tempCol = image[1].getRGB(i,j);
					if (tempCol == -1) {
						int grayPixel = rgbToGray(image[0].getRGB(i, j));
						result.setRGB(i, j, grayPixel);

					} else {
						result.setRGB(i, j, image[0].getRGB(i, j));
					}
				} else {
					result.setRGB(i, j, rgbToGray(image[0].getRGB(i, j)));
				}
			}
		}

		return result;
	}
	private int rgbToGray(int rgbDec) {
		int r = (rgbDec >> 16) & 0xFF;
        int g = (rgbDec >> 8) & 0xFF;
        int b = (rgbDec & 0xFF);
        
        float rr = (float) Math.pow(r / 255.0, 2.2);
        float gg = (float) Math.pow(g / 255.0, 2.2);
        float bb = (float) Math.pow(b / 255.0, 2.2);
        
        float lum = (float) (0.2126 * rr + 0.7152 * gg + 0.0722 * bb);
        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 

		return gray; 
		
	}

}
