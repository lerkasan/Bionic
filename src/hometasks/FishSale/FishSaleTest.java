package hometasks.FishSale;

import static org.junit.Assert.*;
import hometasks.Exceptions.*;
import java.time.LocalDate;
import org.junit.Test;

public class FishSaleTest {
	
	@Test
	public void defaultConstructorTest() {
		FishSale fishSale1 = new FishSale();
		assertEquals(fishSale1.getFishName(),"");
		assertEquals(fishSale1.getPurchasePrice(), 0.0, 0.00001);
		assertEquals(fishSale1.getSalePrice(), 0.0, 0.00001);
		assertEquals(fishSale1.getPurchaseDate(), LocalDate.now());
		assertEquals(fishSale1.getSaleDate(), LocalDate.now());
		assertEquals(fishSale1.getWeight(), 0.0, 0.00001);
	}
	
	@Test
	public void constructorTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		assertEquals(fishSale1.getFishName(),"Тунец");
		assertEquals(fishSale1.getPurchasePrice(), 123.75, 0.00001);
		assertEquals(fishSale1.getSalePrice(), 155.80, 0.00001);
		assertEquals(fishSale1.getPurchaseDate(), LocalDate.of(2016,1,10));
		assertEquals(fishSale1.getSaleDate(), LocalDate.of(2016,1,19));
		assertEquals(fishSale1.getWeight(), 250.0, 0.00001);
	}
	
	
	@Test(expected = WrongArgumentException.class)
	public void constructorFishNameExceptionTest() {
		new FishSale(null, 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorPurchasePriceExceptionTest() {
		new FishSale("Тунец", -123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorSalePriceExceptionTest() {
		new FishSale("Тунец", 123.75, -155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorWeigthExceptionTest() {
		new FishSale("Тунец", 123.75, -155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), -250.0);
	}	
	
	@Test(expected = WrongArgumentException.class)
	public void constructorDatesExceptionTest() {
		new FishSale("Тунец", 123.75, -155.80, LocalDate.of(2016, 2, 10), LocalDate.of(2016,1, 19), 250.0);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorPurchaseDateExceptionTest() {
		new FishSale(null, 123.75, 155.80, null, LocalDate.of(2016,1, 19), 250.0);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void constructorSaleDateExceptionTest() {
		new FishSale(null, 123.75, 155.80, LocalDate.of(2016,1, 19), null, 250.0);
	}
	
	@Test
	public void setFishNameTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setFishName("Окунь");
		assertEquals("Окунь", fishSale1.getFishName());
	}
	
	@Test
	public void setPurchasePriceTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setPurchasePrice(95.50);
		assertEquals(95.50, fishSale1.getPurchasePrice(), 0.00001);
	}
	
	@Test
	public void setSalePriceTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setSalePrice(170.35);
		assertEquals(170.35, fishSale1.getSalePrice(), 0.00001);
	}
	
	@Test
	public void setWeightTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setWeight(170.5);
		assertEquals(170.5, fishSale1.getWeight(), 0.00001);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setFishNameExceptionTest() {
		FishSale fishSale1 = new FishSale("Окунь", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setFishName(null);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setPurchasePriceExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setPurchasePrice(-123.75);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setSalePriceExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setSalePrice(-158.75);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setWeightExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setWeight(-170.5);
	}
	
	@Test(expected = NullPointerException.class)
	public void setPurchaseDateExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setPurchaseDate(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void setSaleDateExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setSaleDate(null);
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setPurchaseSaleDateExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setPurchaseDate(LocalDate.of(2016, 2, 10));
	}
	
	@Test(expected = WrongArgumentException.class)
	public void setSalePurchaseDateExceptionTest() {
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSale1.setSaleDate(LocalDate.of(2016, 1, 5));
	}
	
	@Test
	public void getIncomeTest() {
		//FishSale(String fishName, double purchasePrice, double salePrice, LocalDate purchaseDate, LocalDate saleDate, double weight)
		FishSale fishSale1 = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		assertEquals(7884.93, fishSale1.getIncome(), 0.00001);
		FishSale fishSale2 = new FishSale("Окунь", 25.85, 34.17, LocalDate.of(2015,6,5), LocalDate.of(2015,6,15), 158);
		assertEquals(1224.97, fishSale2.getIncome(), 0.00001);
		FishSale fishSale3 = new FishSale("Окунь", 29.8, 41.7, LocalDate.of(2015,9,4), LocalDate.of(2015,11,25), 178);
		assertEquals(1290.61, fishSale3.getIncome(), 0.00001);
		FishSale fishSale4 = new FishSale("Тунец", 156.23, 197.28, LocalDate.of(2015,5,14), LocalDate.of(2015,6,8), 250);
		assertEquals(9908.13, fishSale4.getIncome(), 0.00001);
		FishSale fishSale5 = new FishSale("Окунь", 30.6, 42.5, LocalDate.of(2015,12,4), LocalDate.of(2015,12,25), 103);
		assertEquals(1103.06, fishSale5.getIncome(), 0.00001);
	}
	
	@Test
	public void getIncomeGroupedByFishTest() {
		FishSale[] fishSales = new FishSale[2];
		fishSales[0] = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSales[1] = new FishSale("Окунь", 25.85, 34.17, LocalDate.of(2015,6,5), LocalDate.of(2015,6,15), 158);
		assertEquals("{Окунь=1224.97, Тунец=7884.93}", FishSale.getIncomeGroupedByFish(fishSales).toString());
		fishSales = new FishSale[6];
		fishSales[0] = new FishSale("Тунец", 123.75, 155.80, LocalDate.of(2016, 1, 10), LocalDate.of(2016,1, 19), 250.0);
		fishSales[1] = new FishSale("Окунь", 25.85, 34.17, LocalDate.of(2015,6,5), LocalDate.of(2015,6,15), 158);
		fishSales[2] = new FishSale("Окунь", 29.8, 41.7, LocalDate.of(2015,9,4), LocalDate.of(2015,11,25), 178);
		fishSales[3] = new FishSale("Тунец", 156.23, 197.28, LocalDate.of(2015,5,14), LocalDate.of(2015,6,8), 250);
		fishSales[4] = new FishSale("Окунь", 30.6, 42.5, LocalDate.of(2015,12,4), LocalDate.of(2015,12,25), 103);
		fishSales[5] = new FishSale("Скумбрия", 445.94, 514.7, LocalDate.of(2015,8,11), LocalDate.of(2015,10,15), 505);
		assertEquals("{Окунь=3618.64, Скумбрия=32862.62, Тунец=17793.06}", FishSale.getIncomeGroupedByFish(fishSales).toString());
	}

}
