package asgn2Train;

import java.util.Stack;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;

/**
 * <p>
 * A train is a sequence of carriages. This class defines various operations
 * that can be performed to prepare a long-distance train for departure. We
 * assume that a train can be assembled from any available rolling stock,
 * including locomotives, passenger cars and freight cars. However, they may be
 * configured in only a certain sequence:
 * <ol>
 * <li>The first carriage must be a locomotive (and there can be only one
 * locomotive per train).</li>
 * <li>This may be followed by zero or more passenger cars.</li>
 * <li>These may be followed by zero or more freight cars.</li>
 * </ol>
 * Any other configurations of rolling stock are disallowed.
 * </p>
 * <p>
 * The process of preparing the train for departure occurs in two stages:
 * <ol>
 * <li>The train is assembled from individual carriages. New carriages may be
 * added to the rear of the train only. (Similarly, carriages may be removed
 * from the rear of the train only.)</li>
 * <li>Passengers board the train. For safety reasons, no carriage shunting
 * operations may be performed when any passengers are on board the train.</li>
 * </ol>
 * </p>
 * 
 * @author Jackson Powell, Yeo Fei Wen
 * 
 */
public class DepartingTrain extends Object {

	Stack<RollingStock> trainConfiguration;

	private int configurationIndex = 0;

	/**
	 * Constructs a DepartingTrain object that initializes an empty stack of
	 * RollingStock objects. This class is used to organise the configuration of
	 * the train.
	 */
	public DepartingTrain() {
		trainConfiguration = new Stack<RollingStock>();
	}

	/**
	 * Adds a new carriage to the end of the train. However, a new carriage may
	 * be added only if the resulting train configuration is valid, as per the
	 * rules listed above. Furthermore, shunting operations may not be performed
	 * if there are passengers on the train.
	 * 
	 * @param newCarriage
	 *            the new carriage to be added
	 * @throws TrainException
	 *             if adding the new carriage would produce an invalid train
	 *             configuration, or if there are passengers on the train
	 */
	public void addCarriage(RollingStock newCarriage) throws TrainException {
		if (numberOnBoard() != 0)
			throw new TrainException(
					"Cannot add carriages whilst passengers on board");

		if (trainConfiguration.isEmpty()
				&& !(newCarriage instanceof Locomotive))
			throw new TrainException(
					"First carriage added to an empty setup must be a Locomotive.");

		if (!trainConfiguration.isEmpty()
				&& (newCarriage instanceof Locomotive))
			throw new TrainException(
					"Cannot add another Locomotive. Only one allowed per setup.");

		if (!trainConfiguration.isEmpty()
				&& (trainConfiguration.peek() instanceof FreightCar)
				&& (newCarriage instanceof PassengerCar))
			throw new TrainException(
					"Cannot add a PassengerCar. A FreightCar has already been "
							+ "added to the setup, please remove it before adding more PassengerCars.");

		trainConfiguration.push(newCarriage);
	}

	/**
	 * Removes the last carriage from the train. (This may be the locomotive if
	 * it is the only item of rolling stock on the train.) However, shunting
	 * operations may not be performed if there are passengers on the train.
	 * 
	 * @throws TrainException
	 *             if there is no rolling stock on the "train", or if there are
	 *             passengers on the train.
	 */
	public void removeCarriage() throws TrainException {
		if (this.numberOnBoard() != 0) {
			throw new TrainException(
					"Removal/Shunting of any carriages is not allowed as there are"
							+ " passengers on board.");
		}

		if (trainConfiguration.isEmpty())
			throw new TrainException("There are no carriages to be removed.");

		trainConfiguration.pop();
	}

	/**
	 * Returns the first carriage on the train (which must be a locomotive).
	 * Special value null is returned if there are no carriages on the train at
	 * all. NB: When combined with method nextCarriage, this method gives us a
	 * simple ability to iteratively examine each of the train's carriages.
	 * 
	 * @return the first carriage in the train, or null if there are no
	 *         carriages
	 */
	public RollingStock firstCarriage() {
		if (trainConfiguration.isEmpty())
			return null;

		configurationIndex = 1;

		return trainConfiguration.get(0);
	}

	/**
	 * Returns the next carriage in the train after the one returned by the
	 * immediately preceding call to either this method or method firstCarriage.
	 * Special value null is returned if there is no such carriage. If there has
	 * been no preceding call to either firstCarriage or nextCarriage, this
	 * method behaves like firstCarriage, i.e., it returns the first carriage in
	 * the train, if any.
	 * 
	 * NB: When combined with method firstCarriage, this method gives us a
	 * simple ability to iteratively examine each of the train's carriages.
	 * 
	 * @return the train's next carriage after the one returned by the
	 *         immediately preceding call to either firstCarriage or
	 *         nextCarriage, or null if there is no such carriage
	 */
	public RollingStock nextCarriage() {
		if (trainConfiguration.isEmpty())
			return null;

		if (configurationIndex == 0)
			return firstCarriage();

		// If we've gone past the last carriage.
		if (configurationIndex > (trainConfiguration.size() - 1))
			return null;

		configurationIndex++;
		return trainConfiguration.get(configurationIndex - 1);
	}

	/**
	 * Returns the total number of passengers currently on the train, counting
	 * all passenger cars.
	 * 
	 * @return the number of passengers on the train
	 */
	public Integer numberOnBoard() {
		int totalPassengerCount = 0;
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof PassengerCar) {
				totalPassengerCount += ((PassengerCar) trainConfiguration
						.get(i)).numberOnBoard();
			}
		}
		return totalPassengerCount;
	}

	/**
	 * Returns the total number of seats on the train (whether occupied or not),
	 * counting all passenger cars.
	 * 
	 * @return the number of seats on the train
	 */
	public Integer numberOfSeats() {
		int totalSeatCount = 0;
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof PassengerCar) {
				totalSeatCount += ((PassengerCar) trainConfiguration.get(i))
						.numberOfSeats();
			}
		}
		return totalSeatCount;
	}

	/**
	 * Adds the given number of people to passenger carriages on the train. We
	 * do not specify where the passengers must sit, so they can be allocated to
	 * any vacant seat in any passenger car.
	 * 
	 * @param newPassengers
	 *            the number of people wish to board the train
	 * @return the number of people who were unable to board the train because
	 *         they couldn't get a seat
	 * @throws TrainException
	 *             if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		if (newPassengers < 0)
			throw new TrainException("Cannot board a negative passenger count.");

		Integer passengersToBoardTrain = newPassengers;

		// Attempt to board the train carriage by carriage.
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof PassengerCar) {
				passengersToBoardTrain = ((PassengerCar) trainConfiguration
						.get(i)).board(passengersToBoardTrain);
			}
		}
		return passengersToBoardTrain;
	}

	/**
	 * Returns whether or not the train is capable of moving. A train can move
	 * if its locomotive's pulling power equals or exceeds the train's total
	 * weight (including the locomotive itself).
	 * 
	 * In the degenerate case of a "train" which doesn't have any rolling stock
	 * at all yet, the method returns true.
	 * 
	 * @return true if the train can move (or contains no carriages), false
	 *         otherwise
	 */
	public boolean trainCanMove() {
		Integer totalTrainWeight = 0;
		Integer TrainPower = 0;

		// For each carriage in the setup, check it's weight and add to total.
		// If the carriage is the Locomotive, store it's power value.
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof Locomotive) {
				TrainPower = ((Locomotive) trainConfiguration.get(i)).power();
			}
			totalTrainWeight += trainConfiguration.get(i).getGrossWeight();
		}

		// Compare train's pulling power against the total weight.
		if ((TrainPower == 0 && totalTrainWeight == 0)
				|| ((TrainPower > totalTrainWeight) && TrainPower > 0)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Returns a human-readable description of the entire train. This has the 
	 * form of a hyphen-separated list of carriages, starting with the 
	 * locomotive on the left. The description is thus a string "a-b-...-z", 
	 * where a is the human-readable description of the first carriage (the 
	 * locomotive), b is the description of the second carriage, etc, until 
	 * the description of the last carriage z. (Note that there should be no 
	 * hyphen after the last carriage.) For example, a possible train 
	 * description may be "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages, the empty 
	 * string is returned. 
	 * 
	 * @return a human-readable description of the entire train
	 */
	public String toString() {
		String departingTrainString = "";
		// For each carriage in the setup, add it to the string.
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (i == 0) {
				departingTrainString += trainConfiguration.get(i).toString();
			} else {
				departingTrainString += "-"
						+ trainConfiguration.get(i).toString();
			}
		}
		return departingTrainString;
	}

}
