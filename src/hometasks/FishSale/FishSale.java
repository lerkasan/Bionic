package hometasks.FishSale;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;
import hometasks.Exceptions.*;

public class FishSale {
	static double CONST_EXPENSES = 0.0567;
	private String fishName;
	private double purchasePrice;
	private double salePrice;
	private LocalDate purchaseDate;
	private LocalDate saleDate;
	private double weight;
	
	public FishSale() {
		this.fishName = "";
		this.purchasePrice = 0.0;
		this.salePrice = 0.0;
		this.purchaseDate = LocalDate.now(); 
		this.saleDate = LocalDate.now(); 
		this.weight = 0.0;
	}
	
	public FishSale(String fishName, double purchasePrice, double salePrice, LocalDate purchaseDate, LocalDate saleDate,
			double weight) throws WrongArgumentException, NullArgumentException {
		if (fishName != null) {
			this.fishName = fishName;
		} else {
			throw new WrongArgumentException("FishName is null");
		}
		if ( (purchasePrice >=0 )&& (salePrice >= 0) ) {
			this.purchasePrice = purchasePrice;
			this.salePrice = salePrice;
		} else {
			throw new WrongArgumentException("Purchase and Sale price can't be a negative number.");
		}
		if ((purchaseDate == null) || (saleDate == null)) {
			throw new NullArgumentException("Purchase and Sale dates can't be null.");
		}
		if (purchaseDate.isBefore(saleDate) || purchaseDate.isEqual(saleDate)) {
			this.purchaseDate = purchaseDate;
			this.saleDate = saleDate;
		} else {
			throw new WrongArgumentException("Dates can't be null and Sale date can't be before Purchase date.");
		}
		if (weight > 0) {
			this.weight = weight;
		} else {
			throw new WrongArgumentException("Weight of sold fish should be positive number.");
		}
	}
	
	public static void setConstExpenses(double constExpenses) throws WrongArgumentException {
		if (constExpenses > 0) {
		FishSale.CONST_EXPENSES = constExpenses;
		} else {
			throw new WrongArgumentException("ConstExpenses should be positive number.");
		}
	}

	public String getFishName() {
		return this.fishName;
	}

	public void setFishName(String fishName) throws WrongArgumentException {
		if (fishName != null) {
			this.fishName = fishName;
		} else {
			throw new WrongArgumentException("Fish name can't be null.");
		}
	}

	public double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) throws WrongArgumentException {
		if (purchasePrice >=0) {
			this.purchasePrice = purchasePrice;
		} else {
			throw new WrongArgumentException("Purchase price can't be a negative number.");
		}
	}

	public double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(double salePrice) throws WrongArgumentException {
		if (salePrice >=0) {
			this.salePrice = salePrice;
		} else {
			throw new WrongArgumentException("Sale price can't be a negative number.");
		}
	}

	public LocalDate getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) throws WrongArgumentException, NullArgumentException {
		if (purchaseDate == null) {
			throw new NullArgumentException("Purchase date can't be null.");
		}
		if (purchaseDate.isBefore(this.saleDate) || purchaseDate.isEqual(this.saleDate)) {
			this.purchaseDate = purchaseDate;
		} else {
			throw new WrongArgumentException("Purchase date can't be after Sale date.");
		}
	}

	public LocalDate getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(LocalDate saleDate) throws WrongArgumentException, NullArgumentException {
		if (saleDate == null) {
			throw new NullArgumentException("Sale date can't be null.");
		}
		if (this.purchaseDate.isBefore(saleDate) || this.purchaseDate.isEqual(saleDate)) {
			this.saleDate = saleDate;
		} else {
			throw new WrongArgumentException("Sale date can't be before Purchase date.");
		}
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) throws WrongArgumentException {
		if (weight > 0) {
			this.weight = weight;
		} else {
			throw new WrongArgumentException("Weight of sold fish should be positive number.");
		}
	}
	
	public double getIncome() {
		double income = this.weight*(this.salePrice - this.purchasePrice - FishSale.CONST_EXPENSES*this.purchaseDate.until(this.saleDate, ChronoUnit.DAYS));
		int temp = (int)Math.round(income*100);
		income = (double)temp/100;
		return income;
	}
	
	public static TreeMap<String, Double> getIncomeGroupedByFish(FishSale[] fishSales) throws WrongArgumentException {
		TreeMap<String, Double> groupedIncome = new TreeMap<>();
		
		if (fishSales == null) {
			throw new WrongArgumentException("Fishsales array is null.");
		}
		
		for (FishSale i : fishSales) {
			if (i != null) {
				double storedIncome = groupedIncome.getOrDefault(i.getFishName(), 0.0);
				//groupedIncome.put(i.getFishName(), storedIncome + i.getIncome());
				storedIncome += i.getIncome();
				int temp = (int)Math.round(storedIncome*100);
				storedIncome = (double)temp/100;
				groupedIncome.put(i.getFishName(), storedIncome);
			}
		}
		return groupedIncome;
	}

	public static void main(String[] args) {
		FishSale[] fishSales = new FishSale[7];
		//String fishName, double purchasePrice, double salePrice, LocalDate purchaseDate, LocalDate saleDate, double weight
		fishSales[0] = new FishSale("Окунь", 25.85, 34.17, LocalDate.of(2015,6,5), LocalDate.of(2015,6,15), 158);
		fishSales[1] = new FishSale("Толстолобик", 156.23, 197.28, LocalDate.of(2015,5,14), LocalDate.of(2015,6,8), 250);
		fishSales[2] = new FishSale("Хек", 245.94, 314.7, LocalDate.of(2015,6,25), LocalDate.of(2015,8,15), 305);
		fishSales[3] = new FishSale("Окунь", 29.8, 41.7, LocalDate.of(2015,9,4), LocalDate.of(2015,11,25), 178);
		fishSales[4] = new FishSale("Толстолобик", 186.23, 208.28, LocalDate.of(2015,10,14), LocalDate.of(2015,11,8), 450);
		fishSales[5] = new FishSale("Скумбрия", 445.94, 514.7, LocalDate.of(2015,8,11), LocalDate.of(2015,10,15), 505);
		fishSales[6] = new FishSale("Окунь", 30.6, 42.5, LocalDate.of(2015,12,4), LocalDate.of(2015,12,25), 103);
		
		for (FishSale i : fishSales) {
			System.out.println("Fish: " + i.getFishName() + " \t\t\tIncome: " + i.getIncome());
		}
		System.out.println();
		System.out.println("Fish: \t\t\t\t  Income grouped by fish:");
		TreeMap<String, Double> groupedIncome = FishSale.getIncomeGroupedByFish(fishSales);
		for (Map.Entry<String, Double> entry : groupedIncome.entrySet()) {
			System.out.println(entry.getKey() + "\t\t\t\t" + entry.getValue());
		}
	}

}
