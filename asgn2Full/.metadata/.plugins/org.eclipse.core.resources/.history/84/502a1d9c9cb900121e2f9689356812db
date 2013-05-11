package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public abstract class RollingStock extends Object {

	private Integer grossWeight;
	
	/**
	 * Creates a new RollingStock carriage/instance.
	 * 
	 * @param grossWeight The gross weight associated with this object.
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight <= 0)
			throw new TrainException("Invalid gross weight. Must be greater than 0.");
		this.grossWeight = grossWeight;
	}
	
	public Integer getGrossWeight() {
		return grossWeight;
	}
	
	@Override
	public abstract String toString();

}
