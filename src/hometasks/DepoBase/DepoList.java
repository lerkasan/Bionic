package hometasks.DepoBase;

import java.util.ArrayList;
import java.util.Collections;

import java.time.*;

public class DepoList {
	private ArrayList<DepoBase> list;
	
	public void init() {
	    this.list = new ArrayList<DepoBase>();
	    //DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
	    this.list.add(new DepoBase(2500.0, 18.0, LocalDate.of(2013, 9, 8), 61));
	    this.list.add(new DepoMonthCapitalize(10000.0, 21.0, LocalDate.of(2012, 2, 1), 181));
	    this.list.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    this.list.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    this.list.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	}
	
	public double getPrincipal() {
		double totalSum = 0.0;
		for (DepoBase depo : this.list) {
			totalSum += depo.getSum();
		}
		return totalSum;
	}
	
	public static void main(String[] args) {
		DepoList list = new DepoList();
		//ArrayList<DepoBase> list;
		list.init();
		//Collections.sort(list);
	}

}
