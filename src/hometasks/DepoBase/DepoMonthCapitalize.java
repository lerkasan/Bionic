package hometasks.DepoBase;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class DepoMonthCapitalize extends DepoBase implements InterestInterface {

	public DepoMonthCapitalize() { 
		super();
	}
	
	public DepoMonthCapitalize(double sum, double interestRate, LocalDate startDate, int dayLong) {
		super(sum, interestRate, startDate, dayLong);
	}
	
	public DepoMonthCapitalize(DepoMonthCapitalize depo) { 
		super(depo);
	}
	
	@Override
	public double getInterest(){
		double complexInterest = 0.0;
		DepoBase temp = new DepoBase(this);
		TreeMap<LocalDate, Integer> days = temp.divideDaysToMonths();

		for (Map.Entry<LocalDate, Integer> entry : days.entrySet()) {
			//System.out.println("Start: "+entry.getKey()+" Days: "+entry.getValue());
			temp.setStartDate(entry.getKey());
			temp.setDayLong(entry.getValue());
			double newSum = temp.getSum()+temp.getInterest();
			int tmp = (int)Math.round(newSum*100);
			newSum = (double)tmp/100;
			temp.setSum(newSum);
		}
		complexInterest = temp.getSum() - this.getSum();
		int tmp = (int)Math.round(complexInterest*100);
		complexInterest = (double)tmp/100;
		//System.out.println("Sum with interest: " + temp.getSum() + " Complex interest: " + complexInterest);
		return complexInterest;
	}

}
