package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * <p>
 * Freight cars are designed to handle a variety of goods. For the purposes of
 * this assignment we assume there are three freight car types of interest,
 * characterised by the kinds of goods they are designed to carry:
 * </p>
 * <ul>
 * <li>"G" - General goods</li>
 * <li>"R" - Refrigerated goods</li>
 * <li>"D" - Dangerous materials</li>
 * </ul>
 * 
 * @author Jackson Powell, Yeo Fei Wen
 * 
 */
public class FreightCar extends RollingStock {

	private String goodsType;

	/**
	 * Constructs a FreightCar RollingStock with the supplied gross weight and
	 * goods type. Must comply with specified restrictions.
	 * 
	 * @param grossWeight
	 *            The gross weight associated with this carriage.
	 * @param goodsType
	 *            The type of goods associated with this carriage.
	 * @throws TrainException
	 *             If the gross weight is not a positive Integer or if the
	 *             goodsType is not one of; General, Refrigerated or Dangerous
	 *             types.
	 */
	public FreightCar(Integer grossWeight, String goodsType)
			throws TrainException {
		super(grossWeight);

		if (!goodsType.equals("G") && !goodsType.equals("R")
				&& !goodsType.equals("D"))
			throw new TrainException("Invalid goods type. Must define using "
					+ "G (General), R (Refrigerated) or D (Dangerous) goods.");

		this.goodsType = goodsType;
	}

	/**
	 * Returns the type of goods the carriage is to carry.
	 * 
	 * @return The carriage's goods classification.
	 */
	public String goodsType() {
		return goodsType;
	}

	@Override
	/**
	 * Returns a human-readable description of the freight car.  This has the form
	 * "<code>Freight(</code><em>x</em><code>)</code>" where <em>x</em> is a character
	 * ("G", "R" or "D") indicating the type of goods the car is
	 * designed to carry.
	 * 
	 * @return a human-readable description of the freight car
	 */
	public String toString() {
		return "Freight(" + goodsType + ")";
	}

}
