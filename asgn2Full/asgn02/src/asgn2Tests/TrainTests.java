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
	final Integer ZERO_VALUE = 0;
	DepartingTrain testTrain;
	RollingStock standardLocomotive, standardPassengerCar, standardFreightCar;

	/* Variables for FreightCar test */
	final String VALID_GOODS_TYPE = "G";

	/* Variables for Locomotive test */
	final String VALID_CLASSIFICATION_TYPE = "4S";

	/* Variables for PassengerCar test */
	final Integer VALID_NUM_OF_EMPTY_SEATS = 20;
	final Integer VALID_NUM_OF_BOARDING_PASSENGERS = 18;
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
		standardLocomotive = new Locomotive(VALID_TEST_WEIGHT,
				VALID_CLASSIFICATION_TYPE);
		standardPassengerCar = new PassengerCar(VALID_TEST_WEIGHT,
				VALID_NUM_OF_EMPTY_SEATS);
		standardFreightCar = new FreightCar(VALID_TEST_WEIGHT, VALID_GOODS_TYPE);
	}

	@Test
	/*
	 * Attempt to add a Locomotive carriage as the first carriage, to the empty
	 * DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddLocomotiveFirstCarriage() throws TrainException {
		testTrain.addCarriage(standardLocomotive);
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a PassengerCar carriage as the first carriage, to the
	 * empty DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddPassengerCarFirstCarriage() throws TrainException {
		testTrain.addCarriage(standardPassengerCar);
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a FreightCar carriage as the first carriage, to the empty
	 * DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddFreightCarFirstCarriage() throws TrainException {
		testTrain.addCarriage(standardFreightCar);
	}

	@Test(expected = TrainException.class)
	/*
	 * Attempt to add a Locomotive carriage as the first carriage, then to add a
	 * second Locomotive carriage to the empty DepartingTrain.
	 * 
	 * @author Jackson Powell
	 */
	public void testAddDoubleLocomotiveFirstCarriage() throws TrainException {
		testTrain.addCarriage(standardLocomotive);
		testTrain.addCarriage(standardLocomotive);
	}

//	@Test
//	/*
//	 * Attempt to add a Locomotive carriage and then check that it was added to
//	 * the very end of the DepartingTrain.
//	 * 
//	 * @author Jackson Powell
//	 */
//	public void testAddLocomotiveToEnd() throws TrainException {
//		testTrain.addCarriage(standardLocomotive);
//		RollingStock endCarriage, currentCarriage;
//		do {
//			currentCarriage = testTrain.nextCarriage();
//			//If we reached the end, don't save over last saved.
//			if(currentCarriage != null)
//				endCarriage = currentCarriage;
//		} while(currentCarriage != null);
//		assertEquals(endCarriage, standardLocomotive);
//	}
//
//	@Test
//	/*
//	 * Attempt to add a FreightCar carriage and then check that it was added to
//	 * the very end of the DepartingTrain.
//	 * 
//	 * @author Jackson Powell
//	 */
//	public void testAddFreightCarToEnd() throws TrainException {
//		testTrain.addCarriage(standardLocomotive);
//		testTrain.addCarriage(standardFreightCar);
//		RollingStock endCarriage, currentCarriage;
//		do {
//			currentCarriage = testTrain.nextCarriage();
//			// If we reached the end, don't save over last saved.
//			if (currentCarriage != null)
//				endCarriage = currentCarriage;
//		} while (currentCarriage != null);
//		assertEquals(endCarriage, standardFreightCar);
//	}

}
