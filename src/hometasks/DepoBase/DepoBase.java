package hometasks.DepoBase;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.TreeMap;

public class DepoBase implements InterestInterface, Cloneable, Comparable<DepoBase> {
	private LocalDate startDate;
	private int dayLong;
	private double sum;
	private double interestRate;

	public static class DepoBySumComparator implements Comparator<DepoBase> {

		public int compare(DepoBase depo1, DepoBase depo2) {
			if (depo1.getSum() > depo2.getSum()) {
				return 1;
			}
			if (depo1.getSum() < depo2.getSum()) {
				return -1;
			}
			return 0;
		}
	}

	public DepoBase() {
	}

	public DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong) {
		this.sum = sum;
		this.interestRate = interestRate;
		this.startDate = startDate;
		this.dayLong = dayLong;
	}
	
	public DepoBase(DepoBase depo) {
		this.sum = depo.getSum();
		this.interestRate = depo.getInterestRate();
		this.startDate = depo.getStartDate();
		this.dayLong = depo.getDayLong();
	}

	/*
	 * public DepoBase clone() throws CloneNotSupportedException { DepoBase cln
	 * = (DepoBase)super.clone(); cln.startDate = (LocalDate)startDate.clone();
	 * return cln; }
	 */

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate value) {
		startDate = value;
	}

	public int getDayLong() {
		return dayLong;
	}

	public void setDayLong(int value) {
		dayLong = value;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double value) {
		sum = value;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double value) {
		interestRate = value;
	}
	
	public boolean isBelowListMinimalSum() {
		return this.sum < DepoList.MINIMAL_SUM;
	}

	public double getInterest() {
		// interest = sum * (interestRate / 100.0) * (days/365 or days/366)
		int[] dayLongByYear; // index is offset of year to openYear, value is
								// days of dayLong in certain year;
		double[] coef; // days/365 or days/366
		double coefSum = 0.0;
		LocalDate endOfYear;
		LocalDate startDate = this.startDate;
		LocalDate closeDate = this.startDate.plusDays(this.dayLong);
		int closeYear = closeDate.getYear();
		int openYear = this.startDate.getYear();
		dayLongByYear = new int[closeYear - openYear + 1];
		coef = new double[closeYear - openYear + 1];
		for (int i = 0; i <= closeYear - openYear; i++) {
			endOfYear = LocalDate.of(openYear + i, 12, 31);
			if (closeDate.isAfter(endOfYear)) {
				dayLongByYear[i] = (int) startDate.until(endOfYear, ChronoUnit.DAYS);
				startDate = endOfYear;
			} else {
				dayLongByYear[i] = (int) startDate.until(closeDate, ChronoUnit.DAYS);
			}
			coef[i] = 1.0 * dayLongByYear[i] / endOfYear.lengthOfYear();
			coefSum += coef[i];
		}
		double interest = this.sum * (this.interestRate / 100.0) * (coefSum);
		int temp = (int) Math.round(interest * 100);
		interest = (double) temp / 100;
		return interest;
	}
	
	protected TreeMap<LocalDate, Integer> divideDaysToMonths() {
		DepoBase temp = new DepoBase(this);
		TreeMap<LocalDate, Integer> daysLong = new TreeMap<>();
		int currentMonthDays = 0;
		LocalDate closeDate = temp.startDate.plusDays(temp.dayLong);
		LocalDate currentOpenDate = temp.getStartDate().plusDays(1);
		LocalDate nextOpenDate = currentOpenDate.plusMonths(1);
		nextOpenDate = LocalDate.of(nextOpenDate.getYear(), nextOpenDate.getMonthValue(), 1);
		//System.out.println("Starts: " + currentOpenDate + " Ends: " + closeDate + " Days long: " + temp.getDayLong() + " Starting sum: " + temp.getSum());
		
		if (closeDate.isBefore(nextOpenDate)) {
			daysLong.put(currentOpenDate, temp.dayLong);
		//	System.out.println("Starts: " + currentOpenDate + " Days long: " + temp.getDayLong() + " Starting sum: " + temp.getSum());
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
		daysLong.put(currentOpenDate, closeDate.getDayOfMonth());
		return daysLong;
	}

	@Override
	public boolean equals(DepoBase other) {
		if (this == other) { // How to implement? Equality tests should not be
								// made with floating point values
			return true;
		}
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		DepoBase depoObj = (DepoBase) other;
		boolean equal = (this.getInterest() == depoObj.getInterest());
		return equal;
	}

	@Override
	public int compareTo(DepoBase other) {
		if (this.getInterest() - other.getInterest() > 0) {
			return 1;
		} else if (this.getInterest() - other.getInterest() < 0) {
			return -1;
		}
		return 0;
	}

}
