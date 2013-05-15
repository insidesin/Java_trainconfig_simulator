package asgn2Exceptions;

import java.io.Serializable;

/**
 * 
 * @author Jackson Powell, Yeo Fei Wen
 *
 */
public class TrainException extends Exception implements Serializable{

	/**
	 * Creates a new instance of TrainException.
	 * @author Jackson Powell, Yeo Fei Wen
	 * @param message An informative message about the problem found.
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}
	
}
