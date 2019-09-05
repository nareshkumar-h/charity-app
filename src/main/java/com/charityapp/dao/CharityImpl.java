package com.charityapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.charityapp.exception.DBException;
import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;
import com.charityapp.util.ConnectionUtil;

public class CharityImpl implements CharityDAO {

	/** Donor Register **/
	public Integer donorRegister(Donor donor) throws SQLException {
		/** Get user details **/
		String name = donor.getName();
		String email = donor.getEmail();
		String password = donor.getPassword();
		String dob = donor.getDob();
		Character gender = donor.getGender();
		String role = donor.getRole();

		String sql_stmt = "INSERT INTO donor(name,email,password,dob,gender,role) VALUES(?,?,?,STR_TO_DATE(?,'%d-%m-%Y'),?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rows = 0;

		try {
			/** Get connection **/
			conn = ConnectionUtil.getConnection();

			pstmt = conn.prepareStatement(sql_stmt);

			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.setString(4, dob);
			pstmt.setString(5, String.valueOf(gender));
			pstmt.setString(6, role);

			/** Execute query **/
			rows = pstmt.executeUpdate();
			System.out.println(rows + " " + "rows affected!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}
		return rows;
	}

	/** Donor Login **/
	public Donor donorLogin(Donor donor) throws SQLException {
		/** Get donor details **/
		String email = donor.getEmail();
		String password = donor.getPassword();

		String sql_stmt = "SELECT name,email,role FROM donor WHERE email = ? AND password = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		Donor donorObj = null;
		try {
			donorObj = new Donor();
			conn = ConnectionUtil.getConnection();
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			
			if (rs.next()) {
				
				String donorName = null;
				String donorEmail = null;
				String donorRole = null;
				
				donorEmail = rs.getString("email");
				donorName = rs.getString("name");
				donorRole = rs.getString("role");

				donorObj.setEmail(donorEmail);
				donorObj.setName(donorName);
				donorObj.setRole(donorRole);
				donorObj.setIsDonorLoggedIn(true);
				
			} else {
				donorObj.setIsDonorLoggedIn(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}

		return donorObj;
	}

	/** Admin Login **/
	public Admin adminLogin(Admin admin) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		Admin adminObj = null;
		try {
			String adminEmail = admin.getEmail();
			String adminPassword = admin.getPassword();
			conn = ConnectionUtil.getConnection();
			String sql_stmt = "SELECT admin_id,name,email,role,date FROM admin WHERE email = ? AND password = ?";
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setString(1, adminEmail);
			pstmt.setString(2, adminPassword);
			ResultSet rs = pstmt.executeQuery();
			adminObj = new Admin();

			if (rs.next()) {
				/** Get value from resultset **/
				Integer id = rs.getInt("admin_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String date = rs.getString("date");

				/** Set value to adminObj **/
				adminObj.setId(id);
				adminObj.setName(name);
				adminObj.setEmail(email);
				adminObj.setRole(role);
				adminObj.setDate(date);
				adminObj.setIsAdminLoggedIn(true);
			} else {
				adminObj.setIsAdminLoggedIn(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminObj;

	}

	/** Donation request 
	 * @throws DBException **/
	public Boolean donationRequest(DonationRequest request) throws DBException {
		/** Get request details **/
		String requestType = request.getRequestType();
		String description = request.getDescription();
		Double requestAmount = request.getRequestAmount();
		Integer adminId = request.getAdminId();
		Long accountNo = request.getAccountNo();

		Connection conn = null;

		PreparedStatement pstmt = null;
		
		Boolean requestStatus = false;

		try {

			conn = ConnectionUtil.getConnection();

			String sql_stmt = "INSERT INTO donation_request(request_type,description,request_amount,admin_id,account_no) VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql_stmt);

			pstmt.setString(1, requestType);
			pstmt.setString(2, description);
			pstmt.setDouble(3, requestAmount);
			pstmt.setInt(4, adminId);
			pstmt.setLong(5, accountNo);

			if(pstmt.executeUpdate() > 0)
			{
				requestStatus = true;
			}
		}

		catch (SQLException e) {
//			e.getMessage();
			throw new DBException("Donation request is not created",e);
		}

		return requestStatus;
		
	}

	/** List donation request 
	 * @throws DBException 
	 * @throws SQLException **/
	public List<DonationRequest> listDonationRequest() throws DBException, SQLException {

		Connection conn = null;
		DonationRequest request = null;
		PreparedStatement pstmt = null;
		List<DonationRequest> list = null;

		try {

			conn = ConnectionUtil.getConnection();

			String sql_stmt = "SELECT request_type,description,request_amount,date,account_no,request_id FROM donation_request";

			pstmt = conn.prepareStatement(sql_stmt);

			ResultSet rs = pstmt.executeQuery();

			list = new ArrayList<DonationRequest>();


			while (rs.next()) {

				request = new DonationRequest();
				String requestType = rs.getString("request_type");
				String description = rs.getString("description");
				Double requestAmount = rs.getDouble("request_amount");
				String date = rs.getString("date");
				Long accountNo = rs.getLong("account_no");
				Integer requestId = rs.getInt("request_id");

				request.setRequestType(requestType);
				request.setDescription(description);
				request.setRequestAmount(requestAmount);
				request.setDate(date);
				request.setAccountNo(accountNo);
				request.setRequestId(requestId);

				/** Add data to list **/
				list.add(request);

			}
		} catch (SQLException e) {
			throw new DBException("Donation request is not loaded",e);
		} finally {
			conn.close();
			pstmt.close();
		}

		return list;
	}

	/** Get balance **/

	public Double balanceEnquiry(Long accountNo) throws SQLException {

		Double amount = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql_stmt = "SELECT amount FROM bank_account WHERE account_no = ?";

		try {

			conn = ConnectionUtil.getConnection();

			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setLong(1, accountNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				amount = rs.getDouble("amount");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
			pstmt.close();
		}

		return amount;

	}

	/** Get donation request balance **/

	public Double donationRequestBalance(Integer requestId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Double requestAount = null;
		try {
			String sql_stmt = "SELECT request_amount FROM donation_request WHERE request_id = ?";

			conn = ConnectionUtil.getConnection();

			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setInt(1, requestId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				requestAount = rs.getDouble("request_amount");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestAount;
	}

	/** Update money **/

	public Boolean updateMoney(Transaction transaction) {
		
		String transactiontype = transaction.getTransactionType();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_stmt = null;
		if (transactiontype == "debit") {
			sql_stmt = "UPDATE bank_account SET amount = amount - ? WHERE account_no = ?";
		} else {
			sql_stmt = "UPDATE bank_account SET amount = amount + ? WHERE account_no = ?";
		}
		Boolean isMoneyUpdated = false;
		try {
			Long accountNo = transaction.getAccountNo();
			Double amount = transaction.getAmount();
			conn = ConnectionUtil.getConnection();
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setDouble(1, amount);
			pstmt.setLong(2, accountNo);
//			pstmt.setInt(3, pinNo);

			if(pstmt.executeUpdate() > 0)
			{
				isMoneyUpdated = true;
			}
			else
			{
				isMoneyUpdated = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isMoneyUpdated;

	}

	/** Update donation request amount **/

	public void updateRequestAmount(DonationRequest request) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql_stmt = "UPDATE donation_request SET request_amount = request_amount - ? WHERE request_id = ?";
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setDouble(1, request.getAmount());
			pstmt.setInt(2, request.getRequestId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	/** Transaction **/
//	
//	public void transaction(Transaction donor,Transaction admin)
//	{
//
//		/** Update donor money **/
//		updateMoney(donor);
//		
//		/** Update admin money **/
//		updateMoney(admin);
//		
//	}

}
