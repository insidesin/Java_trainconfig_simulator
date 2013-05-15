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
		if (grossWeight == null)
			throw new TrainException("Gross weight must be entered.");
		
		if (grossWeight <= 0)
			throw new TrainException("Invalid gross weight. Must be greater than 0.");
		this.grossWeight = grossWeight;
	}
	
	/**
	 * Returns the railway carriage's gross weight.
	 * @return Returns the carriage's gross weight, in tonnes
	 */
	public Integer getGrossWeight() {
		return grossWeight;
	}
	
	@Override
	/**
	 * Returns a human-readable description of this railway carriage.
	 * @return Returns a textual description of the carriage which you can use to display 
	 * the current train configuration in your user interface.
	 */
	public abstract String toString();

}
