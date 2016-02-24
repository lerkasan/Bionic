package hometasks;

import java.time.*;
import java.time.format.*;

public class Dateit {
		
	public static LocalDate getNextWorkDay(LocalDate myDate) {
		int weekDay = myDate.getDayOfWeek().ordinal();
		int nextWeekDay = weekDay;
		LocalDate nextDay;
		int counter = 0;
		
		do {
			nextWeekDay = (++nextWeekDay) % 7;
			counter++;
		} while (nextWeekDay >= 4); // or just > 4? 
		
		nextDay = myDate.plusDays(counter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String dateText = nextDay.format(formatter);
		System.out.println("\nNext working day is " + dateText);
		return nextDay;
	}

	public static void main(String[] args) {
		
		LocalDate dateNow = LocalDate.now();
		
		LocalDate dateInSixWeeks = dateNow.plusWeeks(6);
		LocalDate dateFourMonthBefore = dateNow.minusMonths(4);
		LocalDate dateIn45Days = dateNow.plusDays(45);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		String dateTextNow = dateNow.format(formatter);
		String dateTextInSixWeeks = dateInSixWeeks.format(formatter);
		String dateTextFourMonthBefore = dateFourMonthBefore.format(formatter);
		String dateTextIn45Days = dateIn45Days.format(formatter);
		
		System.out.println("Today is " + dateTextNow);
		System.out.println("Day in six weeks is " + dateTextInSixWeeks);
		System.out.println("Day four month ago is " + dateTextFourMonthBefore);
		System.out.println("Day in 45 days is " + dateTextIn45Days);
		Dateit.getNextWorkDay(LocalDate.of(2016,2,11));
	}
}
