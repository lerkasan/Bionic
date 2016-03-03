package hometasks.DepoBase;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DepoBase implements InterestInterface, Cloneable {
	protected LocalDate startDate;
	protected int dayLong;
	protected double sum;
	protected double interestRate;

	public DepoBase() {
	}
	
	public DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong) {
		this.sum = sum;
		this.interestRate = interestRate;
		this.startDate = startDate;
		this.dayLong = dayLong;
	}
	
	/*public DepoBase clone() throws CloneNotSupportedException {
		DepoBase cln = (DepoBase)super.clone();
		cln.startDate = (LocalDate)startDate.clone();
		return cln;
	}*/
	
	public LocalDate getStartDate(){
		return startDate;
	}
	public void setStartDate(LocalDate value){
		startDate = value;
	}
	
	public int getDayLong(){
		return dayLong;
	}
	public void setDayLong(int value){
		dayLong = value;
	}
	
	public double getSum(){
		return sum;
	}
	public void setSum(double value){
		sum = value;
	}
	
	public double getInterestRate(){
		return interestRate;
	}
	public void setInterestRate(double value){
		interestRate = value;
	}
	
	public double getInterest(){
		double interest = 0.0;
		LocalDate start = startDate;
		LocalDate maturity = start.plusDays(dayLong);
		int startYear = start.getYear();
		int maturityYear = maturity.getYear();
		start = start.plusDays(1);
		if (startYear == maturityYear){
			interest = calculateInterest(start, maturity);
		} else {
			LocalDate endOfYear = LocalDate.of(startYear, 12, 31);
			interest = calculateInterest(start, endOfYear);
			LocalDate beginOfYear = endOfYear.plusDays(1);
			interest += calculateInterest(beginOfYear, maturity);
		}
		return interest;
	}
	
	public double GetInterest(){
		return 0.0;
	}

	public double calculateInterest(LocalDate start, LocalDate maturity){
		int startYear = start.getYear();
		int maturityYear = maturity.getYear();
		if (startYear != maturityYear){
			IllegalArgumentException depoException = new IllegalArgumentException("Different years for start and maturity dates");
			throw depoException;
		}
		int daysInYear = 365;
		if (start.isLeapYear()){
			daysInYear = 366;
		}
		double dayCf = start.until(maturity, ChronoUnit.DAYS) + 1;
        double interest = sum * (interestRate / 100.0) * (dayCf / daysInYear);	
        return interest;
	}	
}
