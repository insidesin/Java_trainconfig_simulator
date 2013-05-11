package asgn2Tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.RollingStock;

public class RollingStockTests {

	/* Variables for all test  */
	private final Integer STANDARD_TEST_WEIGHT = 50;
	private final Integer ZERO_TEST_WEIGHT = 0;
	
	/* Variables for FreightCar test */
	private final String STANDARD_GOODS_TYPE = "G";
	private final String INVALID_GOODS_TYPE = "CAR";
	
	/* Variables for Locomotive test */
	private final String VALID_CLASSIFICATION_TYPE = "4S";
	private final String INVALID_CLASSIFICATION_LENGTH_OF_3 = "4SS";
	private final String INVALID_CLASSIFICATION_LENGTH_OF_1 = "4";
	private final String INVALID_CLASSIFICATION_ENGINE_TYPE = "4K";
	private final String INVALID_CLASSIFICATION_POWER_TYPE = "0S";
	
	private final Integer OVER_STANDARD_TEST_WEIGHT = 400;
	private final Integer MAXIMUM_LIMIT_LOCOMOTIVE_WEIGHT = 900;
	
	/*TEST IF FreightCar1 == FreightCar2???*/
	private boolean objectsEqual(FreightCar subject1, FreightCar subject2) {
		if(subject1 instanceof FreightCar && subject2 instanceof FreightCar) {
			if(subject1.getGrossWeight() == subject2.getGrossWeight() && 
					subject1.goodsType() == subject2.goodsType())
				return true;
		}
		return false;
	}
	
	@Test
	/*
	 * Attempt to construct a new FreightCar object with valid args.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithValidArguments() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE);
		assertTrue(objectsEqual(newFreightCar, new FreightCar(STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE)));
	}
    
	@Test(expected = TrainException.class) 
	/*
	 * Attempt to construct a new FreightCar object with a gross
	 * weight of negative value, which should throw a TrainException.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithNegativeGrossWeight() throws TrainException {
		FreightCar newFreightCar = new FreightCar(-STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE);
	}
	
	@Test(expected = TrainException.class) 
	/*
	 * Attempt to construct a new FreightCar object with a gross
	 * weight of 0, which should throw a TrainException.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithZeroGrossWeight() throws TrainException {
		FreightCar newFreightCar = new FreightCar(ZERO_TEST_WEIGHT, STANDARD_GOODS_TYPE);
	}
	
	@Test(expected = TrainException.class) 
	/*
	 * Attempt to construct a new FreightCar object with a valid
	 * gross weight but an invalid goods type.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithInvalidGoodsType() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, INVALID_GOODS_TYPE);
	}
	
	@Test(expected = TrainException.class) 
	/*
	 * Attempt to construct a new FreightCar object with a valid
	 * gross weight but a goods type of null.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithNullGoodsType() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, null);
	}

	@Test(expected = TrainException.class) 
	/*
	 * Attempt to construct a new FreightCar object with an invalid
	 * gross weight and an invalid goods type.
	 * @author Jackson Powell
	 */
	public void testFreightCarConstructorWithInvalidArgs() throws TrainException {
		FreightCar newFreightCar = new FreightCar(ZERO_TEST_WEIGHT, INVALID_GOODS_TYPE);
	}
	
	@Test
	/*
	 * Attempt to retrieve the gross weight of a FreightCar object.
	 * @author Jackson Powell
	 */
	public void testFreightCarGetGrossWeight() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE);
		assertEquals(STANDARD_TEST_WEIGHT, newFreightCar.getGrossWeight());
	}
	
	@Test
	/*
	 * Attempt to retrieve a readable String output of a FreightCar.
	 * @author Jackson Powell
	 */
	public void testFreightCarGetGoodsType() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE);
		assertEquals(STANDARD_TEST_WEIGHT, newFreightCar.getGrossWeight());
	}
	
	@Test
	/*
	 * Attempt to retrieve a readable String output of a FreightCar.
	 * @author Jackson Powell
	 */
	public void testFreightCarToString() throws TrainException {
		FreightCar newFreightCar = new FreightCar(STANDARD_TEST_WEIGHT, STANDARD_GOODS_TYPE);
		assertEquals("Freight(" + STANDARD_GOODS_TYPE + ")", newFreightCar.toString());
	}
	
	@Test
	/*
	 * Attempt to construct a new FreightCar object with valid args.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithvalidArguments() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with negative gross weight.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithNegativeGrossWeight() throws TrainException{
		Locomotive newLocomotive = new Locomotive(-STANDARD_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with zero gross weight.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithZeroGrossWeight() throws TrainException{
		Locomotive newLocomotive = new Locomotive(ZERO_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with null for classification.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithNullClassification() throws TrainException{
		Locomotive newLocomotive = new Locomotive(ZERO_TEST_WEIGHT, null);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with null for classification.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithInvalidClassificationLengthOfOne() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, INVALID_CLASSIFICATION_LENGTH_OF_1);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with null for classification.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithInvalidClassificationLengthOfThree() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, INVALID_CLASSIFICATION_LENGTH_OF_3);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with null for classification.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithInvalidClassificationPowerType() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, INVALID_CLASSIFICATION_POWER_TYPE);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to construct a new Locomotive object with null for classification.
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveConstructorWithInvalidClassificationEngineType() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, INVALID_CLASSIFICATION_ENGINE_TYPE);
	}
	
	@Test
	/*
	 * Attempt to get the Integer value from Locomotive.power() method with valid args
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveGetPower() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
		Integer locomotivePower = null;
		locomotivePower = (4*100) - STANDARD_TEST_WEIGHT;
		Integer returnedLocomotivePower = null;
		returnedLocomotivePower = newLocomotive.power();
		assertEquals(locomotivePower,returnedLocomotivePower);
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to get the Integer value from Locomotive.power() method with same gross weight to amount of weight
	 * locomotive is able to pull
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveGetPowerSameAsGrossWeight() throws TrainException{
		Locomotive newLocomotive = new Locomotive(OVER_STANDARD_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
		Integer returnedLocomotivePower = null;
		returnedLocomotivePower = newLocomotive.power();
	}
	
	@Test(expected = TrainException.class)
	/*
	 * Attempt to get the Integer value from Locomotive.power() method with same gross weight to amount of weight
	 * locomotive is able to pull
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveGetPowerLowerToGrossWeight() throws TrainException{
		Locomotive newLocomotive = new Locomotive(MAXIMUM_LIMIT_LOCOMOTIVE_WEIGHT, VALID_CLASSIFICATION_TYPE);
		Integer returnedLocomotivePower = null;
		returnedLocomotivePower = newLocomotive.power();
	}
	
	@Test
	/*
	 * Attempt to get valid string statement from Locomotive.toString() method
	 * @author Yeo Fei Wen
	 */
	public void testLocomotiveToString() throws TrainException{
		Locomotive newLocomotive = new Locomotive(STANDARD_TEST_WEIGHT, VALID_CLASSIFICATION_TYPE);
		String testString = "Loco("+VALID_CLASSIFICATION_TYPE+")";
		assertEquals(testString,newLocomotive.toString());
	}
}