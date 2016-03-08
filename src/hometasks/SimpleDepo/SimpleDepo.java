package hometasks.SimpleDepo;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class SimpleDepo {
	public static final double MIDDLE_SUM = 50000.0;
	public static final double UPPER_SUM = 100000.0;
	
	private double sum;
	private double interestRate;
	private LocalDate openDate;
	private int dayLong;
	
	public SimpleDepo() {
		this.sum = 0;
		this.interestRate = 0;
		this.openDate = LocalDate.now();
		this.dayLong = 0;
	}
	
	public SimpleDepo(double sum, double interestRate, LocalDate openDate, int dayLong) {
		this.sum = sum;
		this.interestRate = interestRate;
		this.openDate = openDate;
		this.dayLong = dayLong;
	}
	
	public SimpleDepo(SimpleDepo depo) {
		this.sum = depo.getSum();
		this.interestRate = depo.getInterestRate();
		this.openDate = depo.getOpenDate();
		this.dayLong = depo.getDayLong();
	}
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public int getDayLong() {
		return dayLong;
	}

	public void setDayLong(int dayLong) {
		this.dayLong = dayLong;
	}

	public double getInterest() {
		//interest =  sum * (interestRate / 100.0) * (days/365 or days/366)
		int[] dayLongByYear; //index is offset of year to openYear, value is days of dayLong in certain year;
		double[] coef; // days/365 or days/366
		double coefSum = 0.0;
		double interestRate = this.interestRate;
		if (this.sum >= SimpleDepo.UPPER_SUM) {
			interestRate += 2.0;
		}
		if ( (this.sum > SimpleDepo.MIDDLE_SUM) && (this.sum < SimpleDepo.UPPER_SUM) ) {
			interestRate++;
		}
		LocalDate endOfYear;
		LocalDate startDate = this.openDate;
		LocalDate closeDate = this.openDate.plusDays(this.dayLong);
		int closeYear = closeDate.getYear();
		int openYear = this.openDate.getYear();
		dayLongByYear = new int[closeYear - openYear + 1];
		coef = new double[closeYear - openYear + 1];
		for (int i = 0; i <= closeYear - openYear; i++) {
			endOfYear = LocalDate.of(openYear+i, 12, 31); 
			if (closeDate.isAfter(endOfYear)) {
				dayLongByYear[i] = (int)startDate.until(endOfYear, ChronoUnit.DAYS);
				startDate = endOfYear;
			} else {
				dayLongByYear[i] = (int)startDate.until(closeDate, ChronoUnit.DAYS);
			}
			coef[i] = 1.0*dayLongByYear[i]/endOfYear.lengthOfYear();
			coefSum+=coef[i];
		}
		double interest = this.sum*(interestRate/100.0)*(coefSum);
		int temp = (int)Math.round(interest*100);
		interest = (double)temp/100;
		System.out.println("Starts: " + this.getOpenDate() + "   Days long: " + this.getDayLong() + "   This sum: " + this.getSum() + "   Interest: " + interest);
		return interest;
	}
	
	private TreeMap<LocalDate, Integer> divideDaysToMonths() {
		SimpleDepo temp = new SimpleDepo(this);
		TreeMap<LocalDate, Integer> daysLong = new TreeMap<>();
		int currentMonthDays = 0;
		LocalDate closeDate = temp.openDate.plusDays(temp.dayLong);
		LocalDate currentOpenDate = temp.getOpenDate();
		LocalDate nextOpenDate = currentOpenDate.plusMonths(1);
		nextOpenDate = LocalDate.of(nextOpenDate.getYear(), nextOpenDate.getMonthValue(), 1);
		
		System.out.println("Starts: " + currentOpenDate + " Ends: " + closeDate + " Days long: " + temp.getDayLong() + " Starting sum: " + temp.getSum());
		
		if (closeDate.isBefore(nextOpenDate)) {
			daysLong.put(currentOpenDate, temp.dayLong);
			return daysLong;
		}
		while (currentOpenDate.isBefore(closeDate)) {	
			currentMonthDays = currentOpenDate.getMonth().length(currentOpenDate.isLeapYear());
			currentMonthDays -= currentOpenDate.getDayOfMonth()-1;
			daysLong.put(currentOpenDate, currentMonthDays);
			nextOpenDate = LocalDate.of(currentOpenDate.getYear(), currentOpenDate.getMonthValue(), 1);
			currentOpenDate = nextOpenDate.plusMonths(1);
		}
		currentOpenDate = currentOpenDate.minusMonths(1);
		daysLong.put(currentOpenDate, closeDate.getDayOfMonth()-1);
		return daysLong;
	}
	
	public double getComplexInterest() {
		double complexInterest = 0.0;
		SimpleDepo temp = new SimpleDepo(this);
		TreeMap<LocalDate, Integer> days = temp.divideDaysToMonths();

		for (Map.Entry<LocalDate, Integer> entry : days.entrySet()) {
			temp.setOpenDate(entry.getKey());
			temp.setDayLong(entry.getValue());
			double newSum = temp.getSum()+temp.getInterest();
			int tmp = (int)Math.round(newSum*100);
			newSum = (double)tmp/100;
			temp.setSum(newSum);
		}
		complexInterest = temp.getSum() - this.getSum();
		int tmp = (int)Math.round(complexInterest*100);
		complexInterest = (double)tmp/100;
		System.out.println("Sum with interest: " + temp.getSum() + " Complex interest: " + complexInterest);
		return complexInterest;
	}
	
	public static void main(String[] args) {
		SimpleDepo deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2012, 9, 8),20);	
		System.out.println(deposit1.divideDaysToMonths());
		
		deposit1.setDayLong(180);
		System.out.println();
		System.out.println(deposit1.divideDaysToMonths());
		
		deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2014, 9, 8),20);
		System.out.println();
		System.out.println(deposit1.divideDaysToMonths());
		
		deposit1.setDayLong(180);
		System.out.println();
		deposit1.setOpenDate(LocalDate.of(2014, 9, 12));
		System.out.println(deposit1.divideDaysToMonths());
		
		
		deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2013, 9, 8), 20);
		System.out.println();
		System.out.println(deposit1.divideDaysToMonths());
		
		deposit1.setDayLong(30);
		System.out.println();
		System.out.println(deposit1.divideDaysToMonths());
		
		deposit1.setDayLong(180);
		deposit1.setOpenDate(LocalDate.of(2014, 5, 12));
		System.out.println();
		System.out.println(deposit1.divideDaysToMonths());
		
		System.out.println();
		deposit1 = new SimpleDepo(1000.0, 15.0, LocalDate.of(2013, 9, 8), 20);
		deposit1.getComplexInterest();
		
		System.out.println();
		deposit1.setDayLong(30);
		deposit1.getComplexInterest();
		
		System.out.println();
		deposit1.setDayLong(180);
		deposit1.setOpenDate(LocalDate.of(2014, 5, 12));
		deposit1.getComplexInterest();	
	}
}
