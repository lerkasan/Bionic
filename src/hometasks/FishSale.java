package hometasks;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FishSale {
	static final double CONST = 0.0567;
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
		this.purchaseDate = LocalDate.now(); // Is it better to set it NULL?
		this.saleDate = LocalDate.now(); // Is it better to set it NULL?
		this.weight = 0.0;
	}
	
	public FishSale(String fishName, double purchasePrice, double salePrice, LocalDate purchaseDate, LocalDate saleDate,
			double weight) throws IllegalArgumentException {
		this.fishName = fishName;
		if ( (purchasePrice >=0 )&& (salePrice >= 0) ) {
			this.purchasePrice = purchasePrice;
			this.salePrice = salePrice;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Purchase and Sale price can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
		if (purchaseDate.isBefore(saleDate) || purchaseDate.isEqual(saleDate)) {
			this.purchaseDate = purchaseDate;
			this.saleDate = saleDate;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Sale date can't be before Purchase date.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
		if (weight > 0) {
			this.weight = weight;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Weight of sold fish should be positive number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public String getFishName() {
		return this.fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public double getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) throws IllegalArgumentException {
		if (purchasePrice >=0) {
			this.purchasePrice = purchasePrice;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Purchase price can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(double salePrice) throws IllegalArgumentException {
		if (salePrice >=0) {
			this.salePrice = salePrice;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Sale price can't be a negative number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public LocalDate getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) throws IllegalArgumentException {
		if (purchaseDate.isBefore(this.saleDate) || purchaseDate.isEqual(this.saleDate)) {
			this.purchaseDate = purchaseDate;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Purchase date can't be after Sale date.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public LocalDate getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(LocalDate saleDate) throws IllegalArgumentException {
		if (this.purchaseDate.isBefore(saleDate) || this.purchaseDate.isEqual(saleDate)) {
			this.saleDate = saleDate;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Sale date can't be before Purchase date.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) throws IllegalArgumentException {
		if (weight > 0) {
			this.weight = weight;
		} else {
			IllegalArgumentException  e = new IllegalArgumentException("Weight of sold fish should be positive number.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
	}
	
	public double getIncome() {
		double income = this.weight*(this.salePrice - this.purchasePrice - FishSale.CONST*this.purchaseDate.until(this.saleDate, ChronoUnit.DAYS));
		int temp = (int)Math.round(income*100);
		income = (double)temp/100;
		return income;
	}
	
	public static TreeMap<String, Double> printIncomeGroupedByFish(FishSale[] fishSales) throws IllegalArgumentException {
		TreeMap<String, Double> groupedIncome = new TreeMap<>();
		
		if (fishSales == null) {
			IllegalArgumentException  e = new IllegalArgumentException("Fishsales array is null.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}
		
		for (FishSale i : fishSales) {
			double storedIncome = groupedIncome.getOrDefault(i.getFishName(), 0.0);
			//groupedIncome.put(i.getFishName(), storedIncome + i.getIncome());
			storedIncome += i.getIncome();
			int temp = (int)Math.round(storedIncome*100);
			storedIncome = (double)temp/100;
			groupedIncome.put(i.getFishName(), storedIncome);
		}
		System.out.println("Fish: \t\t\t\t  Income grouped by fish:");
		for (Map.Entry<String, Double> entry : groupedIncome.entrySet()) {
			System.out.println(entry.getKey() + "\t\t\t\t" + entry.getValue());
		}
		return groupedIncome;
	}

	public static void main(String[] args) {
		//String fishName, double purchasePrice, double salePrice, LocalDate purchaseDate, LocalDate saleDate, double weight
		FishSale fishSale1 = new FishSale("Окунь", 25.85, 34.17, LocalDate.of(2015,6,5), LocalDate.of(2015,6,15), 158);
		FishSale fishSale2 = new FishSale("Толстолобик", 156.23, 197.28, LocalDate.of(2015,5,14), LocalDate.of(2015,6,8), 250);
		FishSale fishSale3 = new FishSale("Хек", 245.94, 314.7, LocalDate.of(2015,6,25), LocalDate.of(2015,8,15), 305);
		FishSale fishSale4 = new FishSale("Окунь", 29.8, 41.7, LocalDate.of(2015,9,4), LocalDate.of(2015,11,25), 178);
		FishSale fishSale5 = new FishSale("Толстолобик", 186.23, 208.28, LocalDate.of(2015,10,14), LocalDate.of(2015,11,8), 450);
		FishSale fishSale6 = new FishSale("Скумбрия", 445.94, 514.7, LocalDate.of(2015,8,11), LocalDate.of(2015,10,15), 505);
		FishSale fishSale7 = new FishSale("Окунь", 30.6, 42.5, LocalDate.of(2015,12,4), LocalDate.of(2015,12,25), 103);
		FishSale[] fishSales = {fishSale1, fishSale2, fishSale3, fishSale4, fishSale5, fishSale6, fishSale7};
		for (FishSale i : fishSales) {
			System.out.println("Fish: " + i.getFishName() + " \t\t\tIncome: " + i.getIncome());
		}
		System.out.println();
		FishSale.printIncomeGroupedByFish(fishSales);
	}

}
