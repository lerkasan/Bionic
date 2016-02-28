package hometasks.Exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullArgumentException extends NullPointerException {
	private static final long serialVersionUID = 2L;

	public NullArgumentException() {
		super();
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "The following exception was thrown:", this);
	}

	public NullArgumentException(String msg) {
		super(msg);
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "The following exception was thrown:", this);
	}
	
	

}
