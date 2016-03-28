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
		
		MoneyTransfer.fillMoneyTransferTableinDB();
		List<MoneyTransfer> moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
		Set<Integer> sending = MoneyTransfer.sendMoneyTransfer(moneyTransList, 2500.0);
		System.out.println("\nToday we send money to merchants with ids: " + sending + "\nAfter money sending:");
		moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
		
		List<Merchant> merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);
		//String name, double charge, Periods period, double minSum, String bankName, String swift, String account
		Merchant merch1 = new Merchant("Eveline Ltd.", 4.85, Periods.TENDAYS, 200.0, "PrivatBank", "Fds53G8y42", "56282356794");
		merch1.addToDB();
		
		merchants = Merchant.loadMerchantsFromDB();
		Merchant.printMerchantList(merchants);
		
		//String name, String address, String email, String ccNo, String ccType, LocalDate maturity
		Customer cust1 = new Customer("Rikki Tikki", "Kiev, Peremogy av, 15", "rikki@ukr.net", "2646573", "Visa", LocalDate.of(2017, 8, 24));
		cust1.addToDB();
		
		List<Customer> customers = Customer.loadCustomersFromDB();
		Customer.printCustomerList(customers);
		
		//int merchantId, int customerId, String goods, double sumPayed, LocalDate paymentDate
		Payment pay = new Payment(1, 2, "Cosmetics", 75.6, LocalDate.of(2016, 3, 14));
		pay.addToDB();
		
		pay = new Payment(3, 2, "Discs", 34.6, LocalDate.of(2016, 3, 25));
		pay.addToDB();
		
		MoneyTransfer.fillMoneyTransferTableinDB();
		moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
		sending = MoneyTransfer.sendMoneyTransfer(moneyTransList, 2500.0);
		System.out.println("\nToday we send money to merchants with ids: " + sending + "\nAfter money sending:");
		moneyTransList = MoneyTransfer.loadFromDB();
		MoneyTransfer.printMoneyTransferToBeSent(moneyTransList);
	}
}
