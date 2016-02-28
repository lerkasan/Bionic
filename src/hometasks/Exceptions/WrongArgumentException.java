package hometasks.Exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WrongArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public WrongArgumentException() {
		super();
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "The following exception was thrown:", this);
	}

	public WrongArgumentException(String msg) {
		super(msg);
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "The following exception was thrown:", this);
	}
	

}
