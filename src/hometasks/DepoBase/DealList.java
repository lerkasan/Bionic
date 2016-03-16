package hometasks.DepoBase;

import java.util.ArrayList;
import java.util.List;
import hometasks.Exceptions.NullArgumentException;

public class DealList<T extends Incomable> {
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
				newDeals.add((T) depo.clone());
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


}
