package hometasks.DepoBase;

import java.util.ArrayList;

import hometasks.Exceptions.NullArgumentException;

public class DealList<T extends Incomable> {
	ArrayList<T> deals;
	
	public DealList() {
		deals = new ArrayList<T>();
	}
	
	public DealList(ArrayList<T> deal) {
		this.deals = deal;
	}

	public ArrayList<T> getDeals() {
		return deals;
	}

	@SuppressWarnings("unchecked")
	public void setDeals(ArrayList<T> deals) {
		if (deals != null) {
			this.deals = new ArrayList<T>(deals.size());
			for (T depo : deals) {
				try {
					this.deals.add((T) depo.clone());
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

	public int compareTo(DealList<T> other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}
	
	public int compareTo(T other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}


}
