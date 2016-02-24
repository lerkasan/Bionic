package hometasks;

import java.time.*;

public class DepoBarrier extends DepoBase implements InterestInterface {
	public static final double MIDDLE_SUM = 50000.0;
	public static final double UPPER_SUM = 100000.0;
	
	public DepoBarrier() {
		super();
	}
	
	public DepoBarrier(double sum, double interestRate, LocalDate startDate, int dayLong) {
		super(sum, interestRate, startDate, dayLong);
	}
	
	@Override
	public double getInterest(){
		double localRate = interestRate;
		if (sum > MIDDLE_SUM){
			interestRate++;
		}
		if (sum > UPPER_SUM){
			interestRate++;
		}
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
		interestRate = localRate;
		return interest;
	}

}
