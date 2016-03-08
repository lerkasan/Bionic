package hometasks.DepoBase;

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
	
	public DepoBarrier(DepoBarrier depo) { 
		super(depo);
	}
	
	@Override
	public double getInterest(){
		double interestRate = this.getInterestRate();
		if (this.getSum() > MIDDLE_SUM){
			interestRate++;
		}
		if (this.getSum() > UPPER_SUM){
			interestRate++;
		}
		DepoBase depo = new DepoBase(this);
		depo.setInterestRate(interestRate);
		return depo.getInterest();
	}

}
