package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * 
 * @author Jackson Powell, Yeo Fei Wen
 *
 */
public class PassengerCar extends RollingStock {
	
	//Variables
	private Integer weightOfPassengerCar;
	private Integer numOfSeats;
	private Integer onBoardPassengers;
	
	/**
	 * Constructs a passenger car with a known weight and a fixed number of seats. 
	 * (We allow a passenger car to have zero seats, although it would not be very useful.)
	 * @author Jackson Powell, Yeo Fei Wen
	 * @param the carriage's gross weight in tonnes (ignoring the weight of passengers, which we treat as negligible)
	 * @param how many seats are available in the carriage
	 * @throws if the gross weight is not positive or if the number of seats is negative
	 */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException {
		super(grossWeight);
		
		if(numberOfSeats == null){
			throw new TrainException("Please set a value of 0 or more for the number of seats.");
		}
		
		
		if(numberOfSeats < 0){
			throw new TrainException("Number of seats cannot be a negative. Only 0 or more seats can be set.");
		}
		
		this.numOfSeats = numberOfSeats;
		this.weightOfPassengerCar = grossWeight;
		this.onBoardPassengers = 0;
	}
	
	/**
	 * Adds the given number of new passengers to the number on board the carriage.
	 * If there are too many new passengers for the number of spare seats the left over people are not boarded.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @param the number of people who wish to board the carriage
	 * @return the number of people who were unable to board the carriage because they couldn't get a seat
	 * @throws if the number of new passengers is negative
	 */
	public Integer board(Integer newPassengers) throws TrainException{
		if(newPassengers < 0){
			throw new TrainException("Number of passengers boarding have to be 0 or more.");
		}
		
		Integer unableToBoardPassengers = 0;
		
		if(newPassengers > numOfSeats){
			unableToBoardPassengers = newPassengers - numOfSeats;
			this.onBoardPassengers = numOfSeats;
			
			return unableToBoardPassengers;
		}
		else
		{
			this.onBoardPassengers = newPassengers;
			return 0;
		}
	}
	
	/**
	 * Removes the given number of passengers from this carriage. 
	 * Attempting to remove more passengers than are on board is not allowed.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @param the number of passengers alighting from the carriage
	 * @throws  if the number of departing passengers is negative or 
	 * if the number of departing passengers exceeds the number on board
	 */
	public void alight(Integer departingPassengers) throws TrainException{
		if(departingPassengers < 0){
			throw new TrainException("The number of departing passengers cannot be less than 0.");
		}
		
		if(departingPassengers > onBoardPassengers){
			throw new TrainException("The number of departing passengers have to be less or equal to the" +
					"number of people on board");
		}
		
		this.onBoardPassengers = onBoardPassengers - departingPassengers;
	}
	
	/**
	 * Returns the number of passengers currently on board this carriage.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @return the number of passengers on board
	 */
	public Integer numberOnBoard(){
		return onBoardPassengers;
	}
	
	/**
	 * Returns the number of seats installed on this carriage.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @return the number of seats on this carriage
	 */
	public Integer numberOfSeats(){
		return numOfSeats;
	}
	
	@Override
	/*
	 * Returns a human-readable description of the passenger car. This has the form "Passenger(x/y)" 
	 * where x is the number of passengers currently on board and y is the number of seats in the carriage.
	 * (non-Javadoc)
	 * @see asgn2RollingStock.RollingStock#toString()
	 */
	public String toString() {
		return "Passenger("+onBoardPassengers+"/"+ numOfSeats + ")";
	}	

}
