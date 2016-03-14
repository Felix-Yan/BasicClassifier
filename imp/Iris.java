package imp;

/**
 * Iris holds attributes and label.
 * @author yanlong
 *
 */
public class Iris {
	public final double sepalLength;
	public final double sepalWidth;
	public final double petalLength;
	public final double petalWidth;
	private String label;

	/**
	 * Iris constructor with 4 attributes: sepalLength, sepalWidth, petalLength and petalWidth.
	 * @param sl
	 * @param sw
	 * @param pl
	 * @param pw
	 */
	public Iris(double sl, double sw, double pl, double pw){
		sepalLength = sl;
		sepalWidth = sw;
		petalLength = pl;
		petalWidth = pw;
	}

	/**
	 * Iris constructor with 4 attributes and the label.
	 * @param sl
	 * @param sw
	 * @param pl
	 * @param pw
	 * @param l
	 */
	public Iris(double sl, double sw, double pl, double pw, String l){
		sepalLength = sl;
		sepalWidth = sw;
		petalLength = pl;
		petalWidth = pw;
		label = l;
	}

	/**
	 * A getter for the label field.
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * A setter for the label field.
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}


}
