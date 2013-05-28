package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which a train is constructed.
 * This abstract class defines characteristics which they all share, most
 * notably having a known gross weight, measured here in tonnes. (There are, of
 * course many other important shared characteristics of railway carriages, such
 * as identifying codes, a certain number of wheels, the track gauge they're
 * designed for, etc, but we don't need these for this assignment.)
 * 
 * @author Jackson Powell, Yeo Fei Wen
 */
public abstract class RollingStock extends Object {

	private Integer grossWeight;

	/**
	 * <p>
	 * Constructs a railway carriage with a specific gross weight (i.e., the
	 * carriage's weight when fully laden). We assume that this weight does not
	 * change once shunting operations have begun. (Freight carriages are
	 * assumed to arrive at the marshalling yard already loaded, and we consider
	 * the weight of passengers to be negligible compared to the weight of the
	 * carriage itself.)
	 * </p>
	 * <p>
	 * We require a railway carriage to have at least some weight, but put no
	 * arbitrary upper limit on its weight. (In practice, though, locomotives
	 * normally weigh around 90 to 180 tonnes, passenger carriages weigh around
	 * 50 to 100 tonnes and laden freight cars weigh around 40 to 90 tonnes.)
	 * </p>
	 * 
	 * @param grossWeight
	 *            the carriage's gross weight in tonnes
	 * @throws TrainException
	 *             if the gross weight is not positive
	 */
	public RollingStock(Integer grossWeight) throws TrainException {
		if (grossWeight <= 0)
			throw new TrainException(
					"Invalid gross weight. Must be greater than 0.");

		this.grossWeight = grossWeight;
	}

	/**
	 * Returns the railway carriage's gross weight.
	 * 
	 * @return Returns the carriage's gross weight, in tonnes
	 */
	public Integer getGrossWeight() {
		return grossWeight;
	}

	@Override
	/**
	 * <p>
	 * Returns a human-readable description of this railway carriage.
	 * </p><p>
	 * In the context of the assignment, this method provides you with a textual
	 * description of the carriage which you can use to display the current
	 * train configuration in your user interface.
	 * </p>
	 * 
	 * @return a printable description of the rolling stock
	 */
	public abstract String toString();

}
