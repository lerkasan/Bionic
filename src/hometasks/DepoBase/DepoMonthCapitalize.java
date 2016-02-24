package hometasks.DepoBase;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepoMonthCapitalize extends DepoBase implements InterestInterface {

	public DepoMonthCapitalize() { 
		super();
	}
	
	public DepoMonthCapitalize(double sum, double interestRate, LocalDate startDate, int dayLong) {
		super(sum, interestRate, startDate, dayLong);
	}
	
	@Override
	public double getInterest(){
		double interest = 0.0;          // проценты за текущий период
		double capital = 0.0;           // начисленные проценты
		double tempSum = sum;
		
		LocalDate beginPeriod = startDate;
		LocalDate start = beginPeriod;
		LocalDate end = start.plusDays(dayLong);
		LocalDate endPeriod = beginPeriod;

		while (true){
            beginPeriod = endPeriod.plusDays(1);
			endPeriod = beginPeriod.plusMonths(1);
			endPeriod = LocalDate.of(endPeriod.getYear(), endPeriod.getMonth(), 1);
            endPeriod = endPeriod.minusDays(1);
			if (endPeriod.isAfter(end)) {
				break;
			}
			interest = calculateInterest(beginPeriod, endPeriod);
			capital += interest;
			//System.out.println("cap = " + capital + "   interest = " + interest + "  beginPeriod=" + beginPeriod + "  endPeriod= " + endPeriod);
			sum += interest;
		}
		interest = calculateInterest(beginPeriod, end);
		capital += interest;
		//System.out.println("cap = " + capital + "   interest = " + interest + "  beginPeriod=" + beginPeriod + "  endPeriod= " + endPeriod);
		sum = tempSum;
		return capital;
	}
	
	@Override
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
