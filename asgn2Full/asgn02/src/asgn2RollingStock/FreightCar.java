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
	 * Creates a new FreightCar carriage/instance.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @param grossWeight The gross weight associated with this car.
	 * @param goodsType The type of goods associated with this car.
	 */
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException {
		super(grossWeight);
		
		if(goodsType != "G" && goodsType != "R" && goodsType != "D")
			throw new TrainException("Invalid goods type. Must define using " +
					"G (General), R (Refrigerated) or D (Dangerous) goods.");
		
		this.goodsType = goodsType;
	}
	
	public String goodsType() {
		return goodsType;
	}

	@Override
	public String toString() {
		return "Freight(" + goodsType + ")";
	}
	
}
