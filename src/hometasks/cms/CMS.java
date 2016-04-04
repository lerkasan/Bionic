package hometasks.cms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CMS {
	
	private CMS() {
	}
	
	public static Connection getConnection() {
		Connection conn = null;
	    Properties props = new Properties();
		try (InputStreamReader in = new InputStreamReader(new FileInputStream("appProperties.txt"), "UTF-8")) {
			props.load(in);  
			String connString = props.getProperty("DBConnectionString");
			conn = DriverManager.getConnection(connString);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
			e.getSQLState();
			e.getErrorCode();
			e.getMessage();
			e.getCause();
		}
		return conn;
	}

	public static void main(String[] args) {
		int index = 0;
		Merchant m1 = new Merchant();
		if (args.length > 0) {
			try {
				index = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("First parameter isn't a integer number");
				e.printStackTrace();
			}
			if (index > 0) {
				m1.loadFromDB(index);
				System.out.println("Total sum payed for merchant #" + index + " is " + m1.getTotalSumPayed());
				System.out.println(m1);
				List<Merchant> merchants = Merchant.loadMerchantsFromDB();
				Merchant.printMerchantList(merchants);
				Merchant.printGivenIncomeByMerchants();
			} else {
				System.out.println("First parameter isn't a positive integer.");
			}
		} else {
				System.out.println("No argument given.");
		}
		
	/*	Payment pay = new Payment(2508, 2406, "new soapkit", 255.6, LocalDate.of(2016, 3, 28));
		//pay.addToDB();
		
		pay = new Payment(2611, 2507, "Best Powder", 357.2, LocalDate.of(2016, 3, 22));
		pay.addToDB();
		
		pay = new Payment(2711, 2407, "cleaning tools", 234.6, LocalDate.of(2016, 3, 23));
		pay.addToDB();
		
		*/
	
		
		
		List<Merchant> merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);
		//String name, double charge, Periods period, double minSum, String bankName, String swift, String account
		//Merchant merch1 = new Merchant("Eveline Ltd.", 4.85, Periods.TENDAYS, 200.0, "PrivatBank", "Fds53G8y42", "56282356794");
		//merch1.addToDB();
		Merchant merch1 = new Merchant("Volia Ltd.", 6.5, Periods.TENDAYS, 400.0, "PrivatBank", "ewejp225h", "25437658");
		merch1.addToDB();
		
		System.out.println("\nMerchants after adding merchant:");
		merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);
		
		//String name, String address, String email, String ccNo, String ccType, LocalDate maturity
		//Customer cust1 = new Customer("Rikki Tikki", "Kiev, Peremogy av, 15", "rikki@ukr.net", "2646573", "Visa", LocalDate.of(2017, 8, 24));
		//cust1.addToDB();
		Customer cust1 = new Customer("Dr. House", "Kiev, Pobedy av, 25", "house@ukr.net", "634757", "MasterCard", LocalDate.of(2016, 8, 18));
		cust1.addToDB();
		
		List<Customer> customers = Customer.loadCustomersFromDB();
		Customer.printCustomerList(customers);
		
		//int merchantId, int customerId, String goods, double sumPayed, LocalDate paymentDate
		Payment pay = new Payment(2508, 2406, "parfume soap", 75.6, LocalDate.of(2016, 3, 28));
		//pay.addToDB();
		
		pay = new Payment(2611, 2507, "kitty Powder", 157.2, LocalDate.of(2016, 3, 22));
		pay.addToDB();
		
		pay = new Payment(2711, 2407, "all for wash", 134.6, LocalDate.of(2016, 3, 23));
		pay.addToDB();
		
		System.out.println("\nMerchants after adding payment:");
		merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);
		
		MoneyTransfer.fillMoneyTransferTableinDB();
		List<MoneyTransfer> moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
		Set<Integer> sending = MoneyTransfer.sendMoneyTransfer(moneyTransList, 1500.0);
		System.out.println("\nToday we send money to merchants with ids: " + sending + "\nMoney transfer list after money sending:");
		moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
		
		System.out.println("\nMerchants after tranfering money:");
		merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);

	}
}
