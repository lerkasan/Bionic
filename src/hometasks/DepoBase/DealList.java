package hometasks.DepoBase;

import java.util.ArrayList;

public class DealList<T extends DepoBase> {
	ArrayList<T> deals;
	
	public DealList() {
	}
	
	public DealList(ArrayList<T> deal) {
		this.deals = deal;
	}

	public ArrayList<T> getDeals() {
		return deals;
	}

	public void setDeals(ArrayList<T> deals) {
		this.deals = deals;
	}
	
	public void addDeal(T deal) {
		deals.add(deal);
	}
	
	public void removeDeal(T deal) {
		deals.remove(deal);
	}
	
	public double getIncome() {
		double sum = 0.0;
		for ( T i : deals ) {
			sum += i.getIncome();
		}
		return sum;
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
