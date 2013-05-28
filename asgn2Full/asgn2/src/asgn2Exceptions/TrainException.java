package asgn2Exceptions;

import java.io.Serializable;

/**
 * <p>A simple class for exceptions thrown by
 * railway shunting and boarding operations.
 * </p>
 * 
 * @author Jackson Powell, Yeo Fei Wen
 * 
 */
public class TrainException extends Exception implements Serializable {

	/**
	 * Constructs a new TrainException object.
	 * 
	 * @param message an informative message describing the
	 * cause of the problem
	 */
	public TrainException(String message) {
		super("Train Exception: " + message);
	}

}
