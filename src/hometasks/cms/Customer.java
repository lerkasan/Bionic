package hometasks.cms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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
	
}
