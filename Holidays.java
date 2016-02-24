package hometasks;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Holidays {
	private int year;
	private LocalDate easter;
	private LocalDate trinity;
	
	public Holidays() {
		this.year = LocalDate.now().getYear();
	}
	
	public Holidays(int year) throws IllegalArgumentException {
		if (year > 0) {
			this.year = year;
		} else {
			throw new IllegalArgumentException("Year can't be represented by a negative number.");
		}
	} 
	
	public Holidays(Holidays holiday) {
		this.year = holiday.getYear();
		this.easter = holiday.getEaster();
		this.trinity = holiday.getTrinity();
	}
	
	public Holidays copy() {
		return new Holidays(this);
	}
	
	public boolean isEqual(Holidays holiday) {
		if (this == holiday) {  
        	return true;
        }
        if (holiday == null) {
        	return false;
        }
        if (this.getClass() != holiday.getClass()) {
        	return false;
        }
		boolean result = this.year == holiday.getYear();
		result = result && (this.getEaster().isEqual(holiday.getEaster()));
		result = result && (this.getTrinity().isEqual(holiday.getTrinity()));
		return result;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) throws IllegalArgumentException {
		if (year > 0) {
			this.year = year;
			this.easter = null;
			this.trinity = null;
		} else {
			throw new IllegalArgumentException("Year can't be represented by a negative number.");
		}
	}
	
	public static LocalDate convertJulianToGregorian(LocalDate day) throws IllegalArgumentException {
		if ( day.isAfter(LocalDate.of(1582, 10, 4)) && day.isBefore(LocalDate.of(1700, 3, 1)) ) {
			return day.plusDays(10);
		} else 
		if ( day.isAfter(LocalDate.of(1700, 2, 28)) && day.isBefore(LocalDate.of(1800, 3, 1)) ) {
			return day.plusDays(11);
		} else
		if ( day.isAfter(LocalDate.of(1800, 2, 28)) && day.isBefore(LocalDate.of(1900, 3, 1)) ) {
			return day.plusDays(12);
		} else
		if ( day.isAfter(LocalDate.of(1900, 2, 28)) && day.isBefore(LocalDate.of(2100, 3, 1)) ) {
			return day.plusDays(13);
		} else
		if ( day.isAfter(LocalDate.of(2100, 2, 28)) && day.isBefore(LocalDate.of(2200, 3, 1)) ) {
			return day.plusDays(14);
		} else
		if ( day.isAfter(LocalDate.of(2200, 2, 28)) && day.isBefore(LocalDate.of(2300, 3, 1)) ) {
			return day.plusDays(15);
		} else
		if ( day.isBefore(LocalDate.of(1582, 10, 5)) ) {
			return day;
		} else {
			throw new IllegalArgumentException("Given date is far far in the future after 01 March 2300.");
		}
	}
	
	public static LocalDate convertToGregorianSince1918(LocalDate day) throws IllegalArgumentException {
		if (day.isAfter(LocalDate.of(1918, 2, 1))) {
			return Holidays.convertJulianToGregorian(day);
		} else {
			return day;
		}
	}

	public LocalDate getEaster() {
		if (this.easter != null) {
			return this.easter;
		}
		int a = this.year % 19;
		int b = this.year % 4;
		int c = this.year % 7;
		int d = (19*a + 15) % 30;
		int e = (2*b + 4*c + 6*d + 6) % 7;
		int f = d + e;
		if (f <= 9) {
			this.easter = Holidays.convertToGregorianSince1918(LocalDate.of(this.year, 3, 22+f));
		} else {
			this.easter = Holidays.convertToGregorianSince1918(LocalDate.of(this.year, 4, f-9));
		}
		return this.easter;
	}

	public LocalDate getTrinity() {
		if (this.trinity != null) {
			return this.trinity;
		}
		return this.getEaster().plusDays(49);
	}

	public static void main(String[] args) {
		Holidays holiday = new Holidays();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy").withLocale(Locale.US);
		System.out.println("Year \t Orthodox Easter \t Orthodox Trinity");
		for (int i = 2000; i <= 2020; i++) {
			holiday.setYear(i);
			System.out.println(i + "\t" + holiday.getEaster().format(formatter) + "\t" + holiday.getTrinity().format(formatter));
		}
	}
}
