package hometasks.Birthday;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Birthday {
	private LocalDate birthDate;
	
	public Birthday() {
		this.birthDate = LocalDate.now();
	}
	
	public Birthday(LocalDate bDate) throws IllegalArgumentException {
		this.setBirthDate(bDate);
	}
	
	public Birthday(Birthday bDay) {
		this.birthDate = bDay.getBirthDate();
	}
	
	public Birthday copy() {
		return new Birthday(this);
	}
	
	public boolean isEqual(Birthday bDay) {
		if (this == bDay) { 
        	return true;
        }
        if (bDay == null) {
        	return false;
        }
        if (this.getClass() != bDay.getClass()) {
        	return false;
        }
		boolean equal = this.birthDate.isEqual(bDay.getBirthDate());
		return equal;
	}
	
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
	
	public void setBirthDate(LocalDate bDate) throws IllegalArgumentException {
		LocalDate now = LocalDate.now();
		if ((bDate.isBefore(now)) || (bDate.isEqual(now))) {
			this.birthDate = bDate;
		} else {
			throw new IllegalArgumentException("Given birthday argument is in the future.");
		}
	}
	
	public DayOfWeek getBirthDayOfWeek() {
		return this.birthDate.getDayOfWeek();
	}
	
	public long getDaysAge() {
		LocalDate now = LocalDate.now();
		return this.birthDate.until(now, ChronoUnit.DAYS);
	}
	
	public long getMonthsAge() {
		LocalDate now = LocalDate.now();
		return this.birthDate.until(now, ChronoUnit.MONTHS);
	}

	public static void main(String[] args) {
		Logger logger = Logger.getAnonymousLogger();
		try {
			Birthday personBirthday = new Birthday(LocalDate.of(1985, 9, 26));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale.US);
			System.out.println("Person was born on " + personBirthday.getBirthDayOfWeek().toString().toLowerCase() + " " + personBirthday.getBirthDate().format(formatter));
			System.out.println("Person's age in days is: " + personBirthday.getDaysAge());
			System.out.println("Person's age in months is: " + personBirthday.getMonthsAge());
		}
		catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			System.out.println("Given birthday argument is in the future.");
		}
		catch (DateTimeException e) {
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			System.out.println("Incorrect Date");
		}
	}
}
