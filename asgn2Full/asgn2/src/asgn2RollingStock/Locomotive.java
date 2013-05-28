package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author Jackson Powell, Yeo Fei Wen
 * 
 **/
public class Locomotive extends RollingStock {

	private int powerValue;
	private String locomotiveClassification;
	private String engineClass;

	private Integer enginePower = 100;

	private final String classificationError = "Starting with a power value of (1-9), followed by"
			+ "an engine type of either 'E' for Electrical, 'D' for Diesel or 'S' for Steam. "
			+ "An example will be '4S'.";

	/**
	 * Constructs a Locomotive RollingStock with the supplied gross weight
	 * and power classification. Must comply with specified restrictions and
	 * regulations in power to weight ratios.
	 * 
	 * @param grossWeight
	 *            The gross weight associated with this type of locomotive.
	 * @param classification
	 *            The type of engine this locomotive have.
	 * @throws TrainException
	 *             If the gross weight is not a positive Integer or the power
	 *             classification is invalid.
	 */
	public Locomotive(Integer grossWeight, String classification)
			throws TrainException {
		super(grossWeight);

		if (classification.length() != 2) {
			throw new TrainException(
					"A locomotive classification have to consist of only two characters. "
							+ classificationError);
		}

		int powerValue = Integer.parseInt(classification.substring(0, 1));
		String engineClass = classification.substring(1, 2);

		if (powerValue < 1 || powerValue > 9) {
			throw new TrainException(
					"Invalid power class in classification. Must define using the guidelines. "
							+ classificationError);
		}

		if (!engineClass.equals("S") && !engineClass.equals("D")
				&& !engineClass.equals("E")) {
			throw new TrainException("Invalid engine type in classification."
					+ engineClass + " Must define using the guidelines. "
					+ classificationError);
		}

		/*if (((powerValue * enginePower) - grossWeight) <= 0) {
			throw new TrainException(
					"Locomotive cannot generate the amount of power to pull and move. "
							+ "Please use a higher power class higher than : "
							+ powerValue);
		}*/

		this.locomotiveClassification = classification;

		// PowerClass (0-9)
		this.powerValue = powerValue;

		// Engine Type (Steam / Diesel / Electric)
		this.engineClass = engineClass;
	}

	/**
	 * Returns how much total weight the locomotive can pull (including itself)
	 * as classified by locomotive power specifications.
	 * 
	 * @return The locomotive's "pulling power" in tonnes.
	 */
	public Integer power() {
		return ((powerValue * enginePower) - this.getGrossWeight());
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	public String toString() {
		return "Loco(" + locomotiveClassification + ")";
	}

}
