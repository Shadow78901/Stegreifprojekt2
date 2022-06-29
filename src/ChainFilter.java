import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ChainFilter implements Filter {

	private ArrayList<Filter> filter;

	public ChainFilter() {
		filter = new ArrayList(0);
	}

	@Override
	public BufferedImage process(BufferedImage... input) {
		BufferedImage result = null;

		for (int i = 0; i < filter.size(); i++) {
			if (i == 0) {
				result = filter.get(i).process(input);
			} else {
				result = filter.get(i).process(result,input[1]);
			}
		}

		return result;
	}

	public void add(Filter filter) {
		this.filter.add(filter);
	}

}
