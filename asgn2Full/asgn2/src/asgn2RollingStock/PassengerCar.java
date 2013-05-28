package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed seating capacity.
 * We assume that the train is a long-distance one in which all passengers are
 * assigned a seat (unlike your peak-hour, metropolitan commuting experience!).
 * 
 * @author Jackson Powell, Yeo Fei Wen
 */
public class PassengerCar extends RollingStock {

	private Integer numOfSeats;
	private Integer onBoardPassengers;

	/**
	 * Constructs a passenger car with a known weight and a fixed number of
	 * seats. (We allow a passenger car to have zero seats, although it would
	 * not be very useful.)
	 * 
	 * @param grossWeight
	 *            The carriage's gross weight in tonnes (ignoring the weight of
	 *            passengers, which we treat as negligible)
	 * @param numberOfSeats
	 *            The number of seats to be used in the carriage
	 * @throws TrainException
	 *             If the gross weight is not a positive Integer or if the
	 *             number of seats is negative
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats)
			throws TrainException {
		super(grossWeight);

		if (numberOfSeats < 0) {
			throw new TrainException(
					"Number of seats cannot be a negative. Only 0 or more seats can be set.");
		}

		this.numOfSeats = numberOfSeats;
		this.onBoardPassengers = 0;
	}

	/**
	 * Adds the given number of new passengers to the number on board the
	 * carriage. If there are too many new passengers for the number of spare
	 * seats the left over people are not boarded.
	 * 
	 * @param newPassengers
	 *            The number of people to board onto the carriage.
	 * @return The number of people who were unable to board the carriage
	 *         because they couldn't get a seat.
	 * @throws TrainException
	 *             If the number of new passengers is negative.
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		if (newPassengers < 0)
			throw new TrainException(
					"Number of passengers cannot be a negative. Only 0 or more can board.");

		Integer unableToBoardPassengers = 0;

		if ((onBoardPassengers + newPassengers) <= numOfSeats) {
			this.onBoardPassengers += newPassengers;
		} else {
			unableToBoardPassengers = ((onBoardPassengers + newPassengers) - numOfSeats);
			this.onBoardPassengers = numOfSeats;
		}
		return unableToBoardPassengers;
	}

	/**
	 * Removes the given number of passengers from this carriage. Attempting to
	 * remove more passengers than are on board will throw an exception.
	 * 
	 * @param departingPassengers
	 *            The number of passengers alighting from the carriage.
	 * @throws TrainException
	 *             If the number of departing passengers is negative or if the
	 *             number of departing passengers exceeds the number on board.
	 */
	public void alight(Integer departingPassengers) throws TrainException {
		if (departingPassengers < 0) {
			throw new TrainException(
					"The number of departing passengers cannot be less than 0.");
		}

		if (departingPassengers > onBoardPassengers) {
			throw new TrainException(
					"The number of departing passengers have to be less or equal to the"
							+ "number of people on board");
		}

		onBoardPassengers -= departingPassengers;
	}

	/**
	 * Returns the number of passengers currently on board this carriage.
	 * 
	 * @return The number of passengers on board.
	 */
	public Integer numberOnBoard() {
		return onBoardPassengers;
	}

	/**
	 * Returns the number of seats installed on this carriage.
	 * 
	 * @return The number of seats on this carriage.
	 */
	public Integer numberOfSeats() {
		return numOfSeats;
	}

	@Override
	/**
	 * Returns a human-readable description of the passenger car.  This has the form
	 * "<code>Passenger(</code><em>x</em><code>/</code><em>y</em><code>)</code>" where
	 * <em>x</em> is the number of passengers currently on
	 * board and <em>y</em> is the number of seats in the carriage.
	 * 
	 * @return a human-readable description of the passenger car
	 */
	public String toString() {
		return "Passenger(" + onBoardPassengers + "/" + numOfSeats + ")";
	}

}
