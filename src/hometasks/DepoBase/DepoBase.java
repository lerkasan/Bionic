package hometasks.DepoBase;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Formatter;
import java.util.TreeMap;

public class DepoBase implements InterestInterface, Incomable, Cloneable, Comparable<DepoBase>, Serializable {
	private static final long serialVersionUID = -8434891959098838452L;
	private LocalDate startDate;
	private int dayLong;
	private double sum;
	private double interestRate;

	public static class DepoBySumComparator implements Comparator<DepoBase> {
		
		@Override
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
	
	@Override
	public String toString() {
		Formatter aFormat = new Formatter();
		String result = aFormat.format("|   Sum: %1$8.2f   |   Interest rate: %2$5.2f   |   Start date: %3$11tD   |   Interest: %4$7.2f   |   Days long: %5$5d   |", 
				getSum(), getInterestRate(), getStartDate(), getInterest(), getDayLong()).toString();
		aFormat.close();
		return result;
	}
	
	@Override
	public DepoBase clone() { 
		  return new DepoBase(sum, interestRate, startDate, dayLong);
	}
	 
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayLong;
		long temp;
		temp = Double.doubleToLongBits(interestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*@Override //Generated
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepoBase other = (DepoBase) obj;
		if (dayLong != other.dayLong)
			return false;
		if (Double.doubleToLongBits(interestRate) != Double.doubleToLongBits(other.interestRate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		return true;
	} */

	@Override 
	public boolean equals(Object other) {
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
	
	public double getIncome() {
		return getInterest();
	}

	@Override
	public int compareTo(DepoBase other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}

}
