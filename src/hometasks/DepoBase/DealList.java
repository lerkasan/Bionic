package hometasks.DepoBase;

import java.util.ArrayList;

public class DealList<T extends DepoBase> {
	T deal;
	
	public DealList() {
	}
	
	public DealList(T deal) {
		this.deal = deal;
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
