package hometasks.DepoBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import hometasks.Exceptions.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.*;

public class DepoList implements Serializable {
	private static final long serialVersionUID = -2322919268184612766L;
	public static final double MINIMAL_SUM = 10000.0;
	private List<DepoBase> list;
	
	public DepoList() {
		this.list = new ArrayList<>();
	}
	
	public List<DepoBase> getList() {
		List<DepoBase> newList = new ArrayList<>(list.size());
		for (DepoBase depo : list) {
			newList.add((DepoBase) depo.clone());
		}
		return newList;
	}

	public void setList(List<DepoBase> list) throws NullArgumentException {
		if (list != null) {
			this.list = new ArrayList<>(list.size());
			for (DepoBase depo : list) {
				this.list.add((DepoBase) depo.clone());
			}
		} else {
			throw new NullArgumentException("List argument is null");
		}
	}
	
	public void init() {
	    this.list = new ArrayList<>();
	    //DepoBase(double sum, double interestRate, LocalDate startDate, int dayLong)
	    this.list.add(new DepoBase(2500.0, 18.0, LocalDate.of(2013, 9, 8), 61));
	    this.list.add(new DepoMonthCapitalize(10000.0, 21.0, LocalDate.of(2012, 2, 1), 181));
	    this.list.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    this.list.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    this.list.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	}
	
	public List<DepoBase> remove() {
		for (Iterator<DepoBase> iter = this.list.iterator(); iter.hasNext(); ) {
			if (iter.next().isBelowListMinimalSum()) {
				iter.remove();
			}
		}
	return this.getList();
	}
	
	public List<DepoBase> createListWithoutMinimalSum() {
		return (ArrayList<DepoBase>) this.getList().stream()
			.filter(d -> !d.isBelowListMinimalSum())
			.collect(Collectors.toList());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepoList other = (DepoList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (DepoBase depo : list) {
			result.append(depo);
		}
		return result.toString();
	}

	public double getPrincipal() {
		double totalSum = 0.0;
		for (DepoBase depo : this.list) {
			totalSum += depo.getSum();
		}
		return totalSum;
	}
	
	public void sortDeposByInterest() {
		Collections.sort(list);
	}
	
	public void sortDeposBySum() {
		Collections.sort(list, new DepoBase.DepoBySumComparator());
	}
	
	public void saveReportToFile(String filePath) {
		/*try (PrintStream outStream = new PrintStream(filePath)) {
			for (DepoBase i : list) {
				outStream.println(i.toString());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}*/
		try (PrintWriter outputWriter = new PrintWriter(new FileWriter(filePath));) {
			for (DepoBase i : list) {
				outputWriter.println(i);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveSerializedToFile(String filePath) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
			oos.writeObject(this);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DepoList readSerializedFromFile(String filePath) {
		DepoList newList = new DepoList();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
			newList = (DepoList) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return newList;
	} 
	
	public void add100(int index) {
		double sum = this.getList().get(index).getSum();
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		this.getList().get(index).setSum(sum+100);
	}
	
	public static void main(String[] args) {
		DepoBarrier depo0 = new DepoBarrier(2000,14,LocalDate.of(2013,9,8),80);
		System.out.println(depo0);
		
		DepoList depoList1 = new DepoList();
		DepoList depoList2 = new DepoList();
		depoList1.init();
		depoList2.init();
		List<DepoBase> list = depoList1.getList();
		
		depoList1.sortDeposByInterest();
		System.out.println("Ordered by interest:");
		System.out.println(depoList1);
		depoList2.sortDeposBySum();
		System.out.println("\nOrdered by sum:");
		System.out.println(depoList2);
		
		list.sort((depo1, depo2) -> (int)(depo1.getSum() * 100 - depo2.getSum() * 100));
		System.out.println("\nOrdered by sum with Lambda:");
		for (DepoBase i : list) {
			System.out.print(i);
		}
		
		depoList1.remove();
		System.out.println("\nRemoval:");
		System.out.println(depoList1);
		
		List<DepoBase> list2 = depoList2.createListWithoutMinimalSum();
		System.out.println("\nCollection Filtered:");
		for (DepoBase i : list2) {
			System.out.print(i);
		}
		
		depoList1.saveReportToFile("results\\depo1.txt");
		depoList2.saveReportToFile("results\\depo2.txt");
		List<DepoBase> sortedList1 = depoList1.getList();
		Collections.sort(sortedList1);
		depoList1.setList(sortedList1);
		depoList1.saveReportToFile("results\\sortedDepo1.txt");
		List<DepoBase> sortedList2 = depoList2.getList();
		Collections.sort(sortedList2, new DepoBase.DepoBySumComparator());
		depoList2.setList(sortedList2);
		depoList2.saveReportToFile("results\\sortedDepo2.txt");
		depoList1.saveSerializedToFile("results\\serializedDepo1.txt");
		depoList2.saveSerializedToFile("results\\serializedDepo2.txt");
		DepoList depoList3 = DepoList.readSerializedFromFile("results\\serializedDepo1.txt");
		DepoList depoList4 = DepoList.readSerializedFromFile("results\\serializedDepo2.txt");
		System.out.println("\nDepoList1 read from serialization:\n"+depoList3);
		System.out.println("\nDepoList2 read from serialization:\n"+depoList4);
	}

}
