package hometasks.DepoBase;

import java.time.LocalDate;
import hometasks.Exceptions.WrongArgumentException;

public class TBill {
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
		this.nominal = nominal;
		this.price = price;
		this.billsAmount = billsAmount;
		this.maturityDate = maturityDate;
	}

	public double getNominal() {
		return nominal;
	}

	public void setNominal(double nominal) {
		if (nominal < 0) {
			throw new WrongArgumentException("Nominal should be positive number.");
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
		  return (nominal - price)*billsAmount; 
	}


}
