package hometasks.DepoBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hometasks.Exceptions.NullArgumentException;

public class DealList<T extends Incomable & Comparable<Incomable>> implements Comparable<T>, Serializable {
	private static final long serialVersionUID = -7674214312882926186L;
	List<T> deals;
	
	public DealList() {
		deals = new ArrayList<>();
	}
	
	public DealList(List<T> deal) {
		this.deals = deal;
	}

	@SuppressWarnings("unchecked")
	public List<T> getDeals() {
		List<T> newDeals = new ArrayList<>(deals.size());
		for (T depo : deals) {
			try {
				newDeals.add((T) depo.cloneObj());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return newDeals;
	}

	@SuppressWarnings("unchecked")
	public void setDeals(List<T> deals) {
		if (deals != null) {
			this.deals = new ArrayList<>(deals.size());
			for (T depo : deals) {
				try {
					this.deals.add((T) depo.cloneObj());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new NullArgumentException("List argument is null");
		}
	}
	
	public void add(T deal) {
		deals.add(deal);
	}
	
	public void remove(T deal) {
		deals.remove(deal);
	}
	
	public double getIncome() {
		double sum = 0.0;
		for ( T i : deals ) {
			sum += i.getIncome();
		}
		return sum;
	}

	public int compareIncome(DealList<T> other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}
	
	public int compareIncome(T other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}

	@Override
	public int compareTo(T other) {
		return compareIncome(other);
	}
	
	public void sortDeposByIncome() {
		Collections.sort(deals);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Incomable deal : deals) {
			result.append(deal);
		}
		return result.toString();
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
			for (Incomable i : deals) {
				outputWriter.println(i);
			}
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveSerializedToFile(String filePath) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static DealList<Incomable> readSerializedFromFile(String filePath) {
		DealList<Incomable> newList = new DealList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
			newList = (DealList<Incomable>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newList;
	}
	
	public static void main (String[] args) {
		DealList<Incomable> list1 = new DealList<>();
		list1.add(new DepoMonthCapitalize(10000.0, 21.0, LocalDate.of(2012, 2, 1), 181));
	    list1.add(new DepoBase(5500.0, 15.3, LocalDate.of(2013, 11, 12), 30));
	    list1.add(new DepoBarrier(43000.0, 19.56, LocalDate.of(2011, 12, 18), 370));
	    list1.add(new DepoMonthCapitalize(12000.0, 26.0, LocalDate.of(2013, 7, 12), 91));
	    list1.add(new TBill(500, 495.87, 195, LocalDate.of(2011, 12, 18)));
	    list1.add(new TBill(400, 380.75, 1024, LocalDate.of(2011, 12, 18)));
	       
		System.out.println(list1);
		list1.sortDeposByIncome();
		System.out.println("Sorted by Income/Interest:");
		System.out.println(list1);
		list1.saveReportToFile("results\\dealList.txt");
		list1.saveSerializedToFile("results\\serializedDepo1.txt");
		DealList<Incomable> list4 = DealList.readSerializedFromFile("results\\serializedDepo1.txt");
		System.out.println("\nDealList1 read from serialization:\n"+list4);
	}

}
