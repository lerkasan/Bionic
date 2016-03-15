package hometasks.DepoBase;

import java.time.LocalDate;
import hometasks.Exceptions.WrongArgumentException;

public class TBill implements Incomable, Comparable<TBill> {
	private double  nominal;
	private double price;
	private int billsAmount;
	LocalDate maturityDate;

	public TBill() {
	}
	
	public TBill(double nominal, double price, int billsAmount, LocalDate maturityDate) {
		if ((nominal < 0) || (price < 0) || (billsAmount < 0)) {
			throw new WrongArgumentException("Nominal and Price and BillsAmount should be positive numbers.");
		}
		if (nominal < price) {
			throw new WrongArgumentException("Price shouldn't be bigger than nominal.");
		}
		this.nominal = nominal;
		this.price = price;
		this.billsAmount = billsAmount;
		this.maturityDate = maturityDate;
	}
	
	public TBill clone() { 
		  return new TBill(nominal, price, billsAmount, maturityDate);
	}

	public double getNominal() {
		return nominal;
	}

	public void setNominal(double nominal) {
		if (nominal < 0) {
			throw new WrongArgumentException("Nominal should be positive number.");
		}
		if (nominal < this.price) {
			throw new WrongArgumentException("Nominal shouldn't be smaller than price.");
		}
		this.nominal = nominal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0) {
			throw new WrongArgumentException("Price should be positive number.");
		}
		if (this.nominal < price) {
			throw new WrongArgumentException("Price shouldn't be bigger than nominal.");
		}
		this.price = price;
	}

	public int getBillsAmount() {
		return billsAmount;
	}

	public void setBillsAmount(int billsAmount) {
		if (billsAmount < 0) {
			throw new WrongArgumentException("BillsAmount should be positive number.");
		}
		this.billsAmount = billsAmount;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
	
	public double getIncome() {
		double income = (nominal - price)*billsAmount; 
		int temp = (int) Math.round(income * 100);
		income = (double) temp / 100;
		return income;  
	}
	
	@Override
	public int compareTo(TBill other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}


}
