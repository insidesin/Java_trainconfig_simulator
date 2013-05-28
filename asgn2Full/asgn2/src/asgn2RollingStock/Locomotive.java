package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * <p>
 * A locomotive is a railway carriage with the ability to propel itself and pull
 * (or push) other carriages. Thus the primary distinguishing characteristic of
 * a locomotive is how much weight it can pull.
 * </p>
 * <p>
 * However, calculating the total amount of weight a locomotive can move depends
 * on how much "tractive force" it can generate, which in turn depends on the
 * raw horsepower of the engine, the amount of friction between the train's
 * wheels and the track, the track's grade, and whether we are referring to the
 * "starting" force or the "continuous" force. (As anyone who has ever pushed a
 * stalled car knows, it takes much more effort to get a vehicle moving than to
 * keep it moving once it is already in motion.)
 * </p>
 * <p>
 * Therefore, to keep things simple, most railway operators use a system of
 * discrete "classification codes" to describe how powerful a locomotive is. For
 * our purposes we adopt a model similar to that used by various UK regional
 * railways in which locomotives are classified by a two-character code:
 * </p>
 * <p>
 * <ol>
 * <li>A numeric "power class" in the range 1 to 9.</li>
 * <li>An alphabetic "engine type" consisting of either "E" for electric, "D"
 * for diesel or "S" for steam.</li>
 * </ol>
 * </p>
 * <p>
 * For instance, a locomotive with classification code "4S" is a steam engine in
 * power class 4.
 * </p>
 * <p>
 * To determine how much weight a locomotive can move, we shall use a simple
 * formula in which the maximum weight the locomotive can pull, in tonnes, is
 * its power class times 100. For instance, a locomotive with classification
 * "4S" can pull at most 4&nbsp;X&nbsp;100&nbsp;=&nbsp;400&nbsp;tonnes.
 * <strong>NB:</strong> This figure includes the weight of the locomotive
 * itself. Thus a locomotive classified as only "1D" which weighs 180 tonnes
 * cannot move!
 * </p>
 * 
 * @author Jackson Powell, Yeo Fei Wen
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
	 * Constructs a Locomotive RollingStock with the supplied gross weight and
	 * power classification. Must comply with specified restrictions and
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

		/*
		 * if (((powerValue * enginePower) - grossWeight) <= 0) { throw new
		 * TrainException(
		 * "Locomotive cannot generate the amount of power to pull and move. " +
		 * "Please use a higher power class higher than : " + powerValue); }
		 */

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
	/**
	 * Returns a human-readable description of the locomotive.  This has the form
	 * "<code>Loco(</code><em>x</em><code>)</code>" where <em>x</em>
	 * is the locomotive's two-character classification code.
	 * 
	 * @return a human-readable description of the locomotive
	 */
	public String toString() {
		return "Loco(" + locomotiveClassification + ")";
	}

}
