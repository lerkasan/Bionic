package hometasks.DepoBase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Formatter;

import hometasks.Exceptions.WrongArgumentException;

public class TBill implements Incomable, Cloneable, Serializable  {
	private static final long serialVersionUID = -475529978177489724L;
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
	
	@Override
	public TBill clone() { 
		  return new TBill(nominal, price, billsAmount, maturityDate);
	}
	
	@Override
	public TBill cloneObj() { 
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
	
	public int compareIncome(Incomable other) {
		if (this.getIncome() - other.getIncome() > 0) {
			return 1;
		} else if (this.getIncome() - other.getIncome() < 0) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public int compareTo(Incomable other) {
		return compareIncome(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + billsAmount;
		result = prime * result + ((maturityDate == null) ? 0 : maturityDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(nominal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TBill))
			return false;
		TBill other = (TBill) obj;
		if (billsAmount != other.billsAmount)
			return false;
		if (maturityDate == null) {
			if (other.maturityDate != null)
				return false;
		} else if (!maturityDate.equals(other.maturityDate))
			return false;
		if (Double.doubleToLongBits(nominal) != Double.doubleToLongBits(other.nominal))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		Formatter aFormat = new Formatter();
		//TBill(double nominal, double price, int billsAmount, LocalDate maturityDate)
		String result = aFormat.format("|   Nominal: %1$8.2f     |   Price: %2$13.2f   |     Maturity date: %3$10tD     |   Income: %4$9.2f   |   Amount: %5$8d   |\n", 
				getNominal(), getPrice(), getMaturityDate(), getIncome(), getBillsAmount()).toString();
		aFormat.close();
		return result;
	}
	 


	
	


}
