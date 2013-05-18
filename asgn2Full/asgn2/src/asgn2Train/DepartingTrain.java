package asgn2Train;

import java.util.Stack;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;

public class DepartingTrain extends Object {

	Stack<RollingStock> trainConfiguration;

	private int configurationIndex = 0;

	/**
	 * Constructs a DepartingTrain object that initializes an empty stack of
	 * RollingStock objects. This class is used to organise the configuration of
	 * the train.
	 * 
	 */
	public DepartingTrain() {
		trainConfiguration = new Stack<RollingStock>();
	}

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

	public RollingStock firstCarriage() {
		if (trainConfiguration.isEmpty())
			return null;

		configurationIndex = 0;
		configurationIndex++;

		return trainConfiguration.get(0);
	}

	public RollingStock nextCarriage() {
		if (trainConfiguration.isEmpty())
			return null;

		if (configurationIndex == 0)
			return firstCarriage();

		if (configurationIndex > (trainConfiguration.size() - 1))
			return null;

		configurationIndex++;
		return trainConfiguration.get(configurationIndex - 1);
	}

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

	public Integer board(Integer newPassengers) throws TrainException {
		if (newPassengers < 0)
			throw new TrainException("Cannot board a negative passenger count.");

		Integer passengersToBoardTrain = newPassengers;

		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof PassengerCar) {
				passengersToBoardTrain = ((PassengerCar) trainConfiguration
						.get(i)).board(passengersToBoardTrain);
			}
		}
		return passengersToBoardTrain;
	}

}
