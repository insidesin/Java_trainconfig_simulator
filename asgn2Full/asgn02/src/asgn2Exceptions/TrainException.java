package asgn2Exceptions;

import java.io.Serializable;

public class TrainException extends Exception {

	/**
	 * Creates a new instance of TrainException.
	 * 
	 * @param message An informative message about the problem found.
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}
	
}
