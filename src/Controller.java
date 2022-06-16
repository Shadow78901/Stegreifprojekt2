import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Controller {
	public static void main(String[] args) {
		BufferedImage picture = null;
		BufferedImage result = null;
		String filtername = args[0];
		String inputImage = args[1];
		String outputImage = args[2];
		Filter test;

		if (filtername.equals("ColorBandFilter")) {
			test = new ColorBandFilter();
		} else if (filtername.equals("MonochromeFilter")) {
			test = new MonochromeFilter();
		} else {
			test = new ThresholdFilter();
		}

		try {
			picture = ImageIO.read(new File(inputImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		result = test.process(picture, null);
		try {
			ImageIO.write(result, "bmp", new File(outputImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
