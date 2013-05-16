package asgn2Train;

import java.util.Stack;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.*;

public class DepartingTrain extends Object {

	//private ArrayList<RollingStock> carriageList = new ArrayList<RollingStock>();
	Stack<RollingStock> trainConfiguration;
	
	/**
	 * Constructs a DepartingTrain object that initializes an empty stack
	 * of RollingStock objects. This class is used to organise the 
	 * configuration of the train.
	 * 
	 */
	public DepartingTrain() {
		trainConfiguration = new Stack<RollingStock>();
	}
	
	public void addCarriage(RollingStock newCarriage) throws TrainException {
		//if(numberOnBoard() != 0)
		//	throw new TrainException("Cannot add carriages whilst passengers on board");
		
	}

	public RollingStock nextCarriage() {
		// TODO Auto-generated method stub
		return null;
	}

}
