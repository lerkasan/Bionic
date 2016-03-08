package hometasks.DepoBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import hometasks.Exceptions.*;

import java.time.*;

public class DepoList {
	public static final double MINIMAL_SUM = 10000.0;
	private ArrayList<DepoBase> list;
	
	public ArrayList<DepoBase> getList() {
		return list;
	}

	public void setList(ArrayList<DepoBase> list) {
		if (list != null) {
			this.list = list;
		} else {
			throw new NullArgumentException("List argument is null");
		}
	}
	
	public void init() {
	    this.list = new ArrayList<DepoBase>();
	    //DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
	    this.list.add(new DepoBase(2500.0, 18.0, LocalDate.of(2013, 9, 8), 61));
	    this.list.add(new DepoMonthCapitalize(10000.0, 21.0, LocalDate.of(2012, 2, 1), 181));
	    this.list.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    this.list.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    this.list.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	}
	
	public ArrayList<DepoBase> remove() {
		for (Iterator<DepoBase> iter = this.getList().iterator(); iter.hasNext(); ) {
			if (iter.next().isBelowListMinimalSum()) {
				iter.remove();
			}
		}
	return this.getList();
	}
	
	public ArrayList<DepoBase> createListWithoutMinimalSum() {
		return (ArrayList<DepoBase>) this.getList().stream()
			.filter(d -> !d.isBelowListMinimalSum())
			.collect(Collectors.toList());
	}

	public double getPrincipal() {
		double totalSum = 0.0;
		for (DepoBase depo : this.list) {
			totalSum += depo.getSum();
		}
		return totalSum;
	}
	
	public static void main(String[] args) {
		DepoList depoList1 = new DepoList();
		DepoList depoList2 = new DepoList();
		depoList1.init();
		depoList2.init();
		
		ArrayList<DepoBase> list = depoList1.getList();
		Collections.sort(list);
		System.out.println("Ordered by interest:");
		for (DepoBase i : list) {
			System.out.format("sum = %1$8.2f   interest = %2$7.2f\n", i.getSum(), i.getInterest());
		}
		Collections.sort(list, new DepoBase.DepoBySumComparator());
		System.out.println("\nOrdered by sum:");
		for (DepoBase i : list) {
			System.out.format("sum = %1$8.2f   interest = %2$7.2f\n", i.getSum(), i.getInterest());
		}
		
		list.sort((depo1, depo2) -> (int)(depo1.getSum() * 100 - depo2.getSum() * 100));
		System.out.println("\nOrdered by sum with Lambda:");
		for (DepoBase i : list) {
			System.out.format("sum = %1$8.2f   interest = %2$7.2f\n", i.getSum(), i.getInterest());
		}
		
		depoList1.remove();
		System.out.println("\nRemoval:");
		for (DepoBase i : depoList1.getList()) {
			System.out.format("sum = %1$8.2f   interest = %2$7.2f\n", i.getSum(), i.getInterest());
		}
		
		ArrayList<DepoBase> list2 = depoList2.createListWithoutMinimalSum();
		System.out.println("\nCollection Filtered:");
		for (DepoBase i : list2) {
			System.out.format("sum = %1$8.2f   interest = %2$7.2f\n", i.getSum(), i.getInterest());
		}
	}

}
