package hometasks;

import java.time.LocalDate;
import static org.junit.Assert.*;

public class DepoTest {

	public static void main(String[] args) {
		InterestInterface depos[] = new InterestInterface[6];
		//DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
		depos[0] = (InterestInterface) new DepoBase(1000,15,LocalDate.of(2013,9,8),20);
		depos[1] = (InterestInterface) new DepoBase(2500,18,LocalDate.of(2013,9,8),20);
		depos[2] = (InterestInterface) new DepoBarrier(15000,11.5,LocalDate.of(2013,9,8),40);
		depos[3] = (InterestInterface) new DepoBarrier(5000,14,LocalDate.of(2013,9,8),80);
		depos[4] = (InterestInterface) new DepoMonthCapitalize(2000,16.5,LocalDate.of(2013,9,8),180);
		depos[5] = (InterestInterface) new DepoMonthCapitalize(40000,12.1,LocalDate.of(2013,9,8),91);
		
		double totalInterest = 0;
		try {
			for (InterestInterface depo : depos) {
				totalInterest += depo.getInterest();
			}
		}
		catch (Exception e) { //TO DO
		}
		int temp = (int)Math.round(totalInterest*100);
		totalInterest = (double)temp/100;
		
		assertEquals(1763.41, totalInterest, 0.00001);
	}
}
