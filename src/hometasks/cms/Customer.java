package hometasks.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import hometasks.Exceptions.WrongArgumentException;

public class Customer {
	private int id;
	private String name;
	private String address;
	private String email;
	private String ccNo;
	private String ccType;
	private LocalDate maturity;

	public Customer() {
		this.id = -1;
	}

	public Customer(String name, String address, String email, String ccNo, String ccType, LocalDate maturity) {
		this.id = -1;
		this.name = name;
		this.address = address;
		this.email = email;
		this.ccNo = ccNo;
		this.ccType = ccType;
		this.maturity = maturity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		try {
			InternetAddress emailAddr = new InternetAddress(email); // https://java.net/projects/javamail/pages/Home#Download_JavaMail_Release
			emailAddr.validate();
		} catch (AddressException ex) {
			ex.printStackTrace();
			throw new WrongArgumentException("Customer's email isn't valid.");
		}
		this.email = email;
	}

	public String getCcNo() {
		return ccNo;
	}

	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public LocalDate getMaturity() {
		return maturity;
	}

	public void setMaturity(LocalDate maturity) {
		this.maturity = maturity;
	}
	
	@Override
	public String toString() {
		if (id == -1) {
			return "";
		}
		Formatter aFormat = new Formatter();
		String result = aFormat.format("|   %1$6d   |   %2$25s   |   %3$50s   |   %4$20s   |   %5$14s   |   %6$15s   |   %7$8tD   |%n", 
				id, name, address, email, ccNo, ccType, maturity).toString();
		aFormat.close();
		return result;
	}
	
	public static boolean existsInDB(int customerId) {
		Connection con = CMS.getConnection();
		String sql = "select count(*) from customer where id = " + customerId;
		try (Statement stm = con.createStatement()) {
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next() && (rs.getInt(1) == 1)) {
				rs.close();
				con.close();
				return true;
			}
			rs.close();
			con.close();
			//return false;
		} catch (SQLException e3) {
			e3.printStackTrace();
			e3.getSQLState();
			e3.getErrorCode();
			e3.getMessage();
			e3.getCause();
		}
		return false;
	}
	
	public void addToDB() {
		Connection con = CMS.getConnection();
		String sql = "insert into customer (name, address, email, ccNo, ccType, maturity) " +
				 	 "values (?, ?, ?, ?, ?, ?)";
		Instant instant = maturity.atStartOfDay(ZoneId.systemDefault()).toInstant();
		java.sql.Date dt = new java.sql.Date(java.util.Date.from(instant).getTime());

		try (PreparedStatement stm = con.prepareStatement(sql)) {
			stm.setString(1, name);
			stm.setString(2, address);
			stm.setString(3, email);
			stm.setString(4, ccNo);
			stm.setString(5, ccType);
			stm.setDate(6, dt);
			stm.executeUpdate();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				e.getSQLState();
				e.getErrorCode();
				e.getMessage();
				e.getCause();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			e1.getSQLState();
			e1.getErrorCode();
			e1.getMessage();
			e1.getCause();
		}
	}
	
	public void loadFromDB(int id) {
		Connection con = CMS.getConnection();
		try (Statement stm = con.createStatement()) {
			String sql = "select id, name, address, email, ccNo, ccType, maturity "+
						 "from customer where id = " + id;
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				this.id = rs.getInt("id");
				this.name = rs.getString("name");
				this.address = rs.getString("address");
				this.email = rs.getString("email");
				this.ccNo = rs.getString("ccNo");
				this.ccType = rs.getString("ccType");
				this.maturity = rs.getDate("maturity").toLocalDate();
			} else {
				throw new WrongArgumentException("Customer not found. There is no customer with id "+id+" in database.");
			}
			rs.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				e.getSQLState();
				e.getErrorCode();
				e.getMessage();
				e.getCause();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			e1.getSQLState();
			e1.getErrorCode();
			e1.getMessage();
			e1.getCause();
		}
	}
	
	public static List<Customer> loadCustomersFromDB() {
		Connection con = CMS.getConnection();
		List<Customer> customerList = new ArrayList<>();
		try (Statement stm = con.createStatement()) {
			String sql = "select id, name, address, email, ccNo, ccType, maturity from customer";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Customer cust = new Customer();
				cust.setId(rs.getInt("id"));
				cust.setName(rs.getString("name"));
				cust.setAddress(rs.getString("address"));
				cust.setEmail(rs.getString("email"));
				cust.setCcNo(rs.getString("ccNo"));
				cust.setCcType(rs.getString("ccType"));
				cust.setMaturity(rs.getDate("maturity").toLocalDate());
				customerList.add(cust);
			}
			rs.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				e.getSQLState();
				e.getErrorCode();
				e.getMessage();
				e.getCause();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			e1.getSQLState();
			e1.getErrorCode();
			e1.getMessage();
			e1.getCause();
		}
		return customerList;
	}
	
	public static void printCustomerList(List<Customer> customerList) {
		if ( (customerList != null) && (! customerList.isEmpty()) ) {
			System.out.println("\nCustomers list:");
			System.out.println("|     id     |              name             |                         address                        |" +
			"           email          |" +
							   "        ccNo        |        ccType        |   maturity   |");
			for (Customer cust : customerList) {
				System.out.print(cust);
			}
		}
	}
}
