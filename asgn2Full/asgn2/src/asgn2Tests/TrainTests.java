package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;
import asgn2Train.DepartingTrain;

public class TrainTests {

	/* Variables for all test */
	final Integer VALID_TEST_WEIGHT = 50;
	final Integer ONE_VALUE = 1;
	final Integer ZERO_VALUE = 0;
	DepartingTrain testTrain;

	/* Variables for FreightCar test */
	final String VALID_GOODS_TYPE = "G";

	/* Variables for Locomotive test */
	final String VALID_CLASSIFICATION_TYPE = "4S";

	/* Variables for PassengerCar test */
	final Integer VALID_NUM_OF_EMPTY_SEATS = 20;
	final Integer VALID_NUM_OF_BOARDING_PASSENGERS = 15;
	final Integer VALID_OVER_NUM_OF_BOARDING_PASSENGERS = 21;

	@Test
	/*
	 * Attempt to construct a new (and blank) DepartingTrain object.
	 * 
	 * @author Jackson Powell
	 */
	public void testSuccesfulDepartingTrainConstructor() throws TrainException {
		testTrain = new DepartingTrain();
	}

	@Before
	@Test
	/*
	 * Attempt to construct a new (and blank) DepartingTrain object.
	 * 
	 * @author Jackson Powell
	 */
	public void testStandardTestObjectsConstructed() throws TrainException {
		testTrain = new DepartingTrain();
	}

	@Test
	/*
	 * Attempt to add a Locomotive carriage as the first carriage, to the empty
	 * DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveFirstCarriage() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a PassengerCar carriage as the first carriage, to the
	 * empty DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddPassengerCarFirstCarriage() throws TrainException {
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a FreightCar carriage as the first carriage, to the empty
	 * DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddFreightCarFirstCarriage() throws TrainException {
		testTrain.addCarriage(new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE));
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a Locomotive carriage as the first carriage, then to add a
	 * second Locomotive carriage to the empty DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddDoubleLocomotiveFirstCarriage() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
	}

	@Test
	/*
	 * Attempt to add a Locomotive carriage and then check that it was added to
	 * the very end of the DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveToEnd() throws TrainException {
		RollingStock lastCarriage = new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE);

		testTrain.addCarriage(lastCarriage);
		RollingStock endCarriage = null, currentCarriage = null;
		do {
			currentCarriage = testTrain.nextCarriage();
			// If we reached the end, don't save over last saved.
			if (currentCarriage != null)
				endCarriage = currentCarriage;
		} while (currentCarriage != null);
		assertEquals(lastCarriage, endCarriage);
	}

	@Test
	/*
	 * Attempt to add a PassengerCar carriage and then check that it was added
	 * to the very end of the DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddPassengerCarToEnd() throws TrainException {
		RollingStock lastCarriage = new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS);

		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(lastCarriage);
		RollingStock endCarriage = null, currentCarriage = null;
		do {
			currentCarriage = testTrain.nextCarriage();
			// If we reached the end, don't save over last saved.
			if (currentCarriage != null)
				endCarriage = currentCarriage;
		} while (currentCarriage != null);
		assertEquals(lastCarriage, endCarriage);
	}

	@Test
	/*
	 * Attempt to add a FreightCar carriage and then check that it was added to
	 * the very end of the DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddFreightCarToEnd() throws TrainException {
		RollingStock lastCarriage = new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE);

		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(lastCarriage);
		RollingStock endCarriage = null, currentCarriage = null;
		do {
			currentCarriage = testTrain.nextCarriage();
			// If we reached the end, don't save over last saved.
			if (currentCarriage != null)
				endCarriage = currentCarriage;
		} while (currentCarriage != null);
		assertEquals(lastCarriage, endCarriage);
	}

	@Test
	/*
	 * Attempt to add a FreightCar carriage and then check that it was added to
	 * the very end of the DepartingTrain. This method uses one call to
	 * firstCarriage and then all subsequent calls to nextCarriage to retrieve
	 * the final carriage and check.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddFreightCarToEndUsingFirstCarriage()
			throws TrainException {
		RollingStock lastCarriage = new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE);

		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		testTrain.addCarriage(lastCarriage);

		RollingStock currentCarriage = null;
		currentCarriage = testTrain.firstCarriage();
		currentCarriage = testTrain.nextCarriage();
		currentCarriage = testTrain.nextCarriage();
		assertEquals(lastCarriage, currentCarriage);
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a carriage of null value to the end of the configuration.
	 * Expect exception before carriage is added.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddNullCarriage() throws TrainException {
		testTrain.addCarriage(null);
	}

	@Test
	/*
	 * Attempt to add a Locomotive, then a PassengerCar to the train
	 * configuration. Expect no exceptions as the setup is valid.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveThenPassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
	}

	@Test
	/*
	 * Attempt to add a Locomotive, then a PassengerCar, then a FreightCar to
	 * the train configuration. Expect no exceptions as the setup is valid.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveThenPassengerThenFreightCar()
			throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		testTrain.addCarriage(new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE));
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a Locomotive, then a PassengerCar, then a FreightCar, then
	 * a PassengerCar to the train configuration. Expect an exception as
	 * Passenger cars cannot come behind Freight cars.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveThenPassengerThenFreightThenPassengerCar()
			throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		testTrain.addCarriage(new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
	}

	@Test
	/*
	 * Attempt to add a Locomotive, then a FreightCar to the train
	 * configuration. Expect no exceptions as the setup is valid.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveThenFreightCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE));
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a Locomotive, then a FreightCar, then a PassengerCar to
	 * the train configuration. Expect an exception as Passenger cars cannot
	 * come behind Freight cars.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveThenFreightThenPassengerCar()
			throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new FreightCar(VALID_TEST_WEIGHT,
				VALID_GOODS_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
	}

	@Test
	/*
	 * Attempt to board 0 passengers on a train consisting of 20 seats and then
	 * check that there are no passengers on board with numberOnBoard.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardZeroPassengersValid() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to 0.
		final Integer boardingCount = ZERO_VALUE;
		testTrain.board(boardingCount);
		assertEquals(boardingCount, testTrain.numberOnBoard());
	}

	@Test
	/*
	 * Attempt to board 15 passengers on a train consisting of 20 seats and then
	 * check that they are accounted for with numberOnBoard.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardFifteenPassengersWithOnePassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to 15.
		final Integer boardingCount = VALID_NUM_OF_BOARDING_PASSENGERS;
		testTrain.board(boardingCount);
		assertEquals(boardingCount, testTrain.numberOnBoard());
	}

	@Test
	/*
	 * Attempt to board 20 passengers on a train consisting of 20 seats and then
	 * check that they are accounted for with numberOnBoard.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardMaxPassengersWithOnePassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to 20.
		final Integer boardingCount = VALID_NUM_OF_EMPTY_SEATS;
		testTrain.board(boardingCount);
		assertEquals(boardingCount, testTrain.numberOnBoard());
	}

	@Test
	/*
	 * Attempt to board 30 passengers on a train consisting of 60 seats and then
	 * board another 30 passengers on the same train, check that they are
	 * accounted for with numberOnBoard.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardTwiceThirtyPassengersWithTwoPassengerCars()
			throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to 15.
		final Integer boardingCount = VALID_NUM_OF_BOARDING_PASSENGERS;
		testTrain.board(boardingCount);
		testTrain.board(boardingCount);
		assertTrue((boardingCount + boardingCount) == testTrain.numberOnBoard());
	}
	
	@Test
	/*
	 * Attempt to board 21 passengers on a train consisting of 
	 * 20 seats which should expect 1 not to be boarded.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardOverMaxPassengersWithOnePassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to 21.
		final Integer boardingCount = VALID_OVER_NUM_OF_BOARDING_PASSENGERS;
		Integer leftOverPassengers = testTrain.board(boardingCount);
		assertTrue(leftOverPassengers == ONE_VALUE);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to board -1 passengers on a train consisting of 
	 * 20 seats which should expect an exception.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardNegativePassengersWithOnePassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to -1.
		final Integer boardingCount = -ONE_VALUE;
		testTrain.board(boardingCount);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to board -1 passengers on a train consisting of 
	 * 20 seats which should expect an exception.
	 * 
	 * @author Jackson Powell
	 */
	public void testBoardNullPassengersWithOnePassengerCar() throws TrainException {
		testTrain.addCarriage(new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE));
		testTrain.addCarriage(new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS));
		// board count = to null.
		final Integer boardingCount = null;
		testTrain.board(boardingCount);
	}

}
