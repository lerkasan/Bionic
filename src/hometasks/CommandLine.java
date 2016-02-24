package hometasks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLine {
	
	private CommandLine() {
	}
	
	static int getArgsSum(String[] args) {
		int sum = 0;
		for (String i : args) {
			try {
				sum += Integer.parseInt(i);
			}
			catch (NumberFormatException e) {
				Logger logger = Logger.getAnonymousLogger();
				logger.log(Level.SEVERE, i + " isn't an integer. Omitting it. Proceeding with next argument.");
				logger.log(Level.SEVERE, "The following exception was thrown:", e);
				continue;	
			}	
		}
	return sum;	
	}

	public static void main(String[] args) {
		System.out.println(CommandLine.getArgsSum(args));

	}

}
