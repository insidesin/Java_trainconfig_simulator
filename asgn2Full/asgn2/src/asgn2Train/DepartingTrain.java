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

	/**
	 * 
	 * @param newCarriage
	 * @throws TrainException
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
	 * 
	 * @throws TrainException
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
	 * 
	 * @return
	 */
	public RollingStock firstCarriage() {
		if (trainConfiguration.isEmpty())
			return null;

		configurationIndex = 1;

		return trainConfiguration.get(0);
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public Integer numberOnBoard() {
		int totalPassengerCount = 0;
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof PassengerCar) {
				totalPassengerCount += ((PassengerCar) 
						trainConfiguration.get(i)).numberOnBoard();
			}
		}
		return totalPassengerCount;
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param newPassengers
	 * @return
	 * @throws TrainException
	 */
	public Integer board(Integer newPassengers) throws TrainException {
		if (newPassengers < 0)
			throw new TrainException("Cannot board a negative passenger count.");
		
		Integer passengersToBoardTrain = newPassengers;		
		
		for (int i = 0; i < trainConfiguration.size(); i++) {			
			
			if (trainConfiguration.get(i) instanceof PassengerCar ) {
				passengersToBoardTrain = ((PassengerCar) trainConfiguration
						.get(i)).board(passengersToBoardTrain);
			}
		}
		return passengersToBoardTrain;
	}

	public boolean trainCanMove() {
		Integer totalTrainWeight = 0;
		Integer TrainPower = 0;
		for (int i = 0; i < trainConfiguration.size(); i++) {
			if (trainConfiguration.get(i) instanceof Locomotive) {
				TrainPower = ((Locomotive) trainConfiguration.get(i)).power();
			}
			totalTrainWeight += trainConfiguration.get(i).getGrossWeight();
		}

		if ((TrainPower == 0 && totalTrainWeight == 0)
				|| (TrainPower > totalTrainWeight) && TrainPower > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		String departingTrainString = "";
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
