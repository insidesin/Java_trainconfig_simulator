package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author Jackson Powell, Yeo Fei Wen
 * 
 */
public class FreightCar extends RollingStock {

	private String goodsType;

	/**
	 * Constructs a FreightCar RollingStock with the supplied gross weight
	 * and goods type. Must comply with specified restrictions.
	 * 
	 * @param grossWeight
	 *            The gross weight associated with this carriage.
	 * @param goodsType
	 *            The type of goods associated with this carriage.
	 * @throws TrainException
	 *             If the gross weight is not a positive Integer or if the goodsType
	 *             is not one of; General, Refrigerated or Dangerous types.
	 */
	public FreightCar(Integer grossWeight, String goodsType)
			throws TrainException {
		super(grossWeight);

		if (goodsType != "G" && goodsType != "R" && goodsType != "D")
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	public String toString() {
		return "Freight(" + goodsType + ")";
	}

}
