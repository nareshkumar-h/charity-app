package com.charityapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.charityapp.model.Admin;
import com.charityapp.model.DonationRequest;
import com.charityapp.model.Donor;
import com.charityapp.model.Transaction;
import com.charityapp.util.ConnectionUtil;

public class CharityDAO implements Charity {
	
	/** Donor Register 
	 * @throws SQLException **/
	public void donorRegister(Donor donor) throws SQLException
	{
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
			int rows = pstmt.executeUpdate();
			System.out.println(rows + " " + "rows affected!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
			pstmt.close();
		}
	}
	
	/** Donor Login  **/
	public Donor donorLogin(Donor donor) throws SQLException
	{
		/** Get donor details**/
		String email = donor.getEmail();
		String password = donor.getPassword();
		
		String sql_stmt = "SELECT name,email,role FROM donor WHERE email = ? AND password = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			String donorName = null;
			String donorEmail = null;
			String donorRole = null;
			
			while(rs.next())
			{
				donorEmail = rs.getString("email");
				donorName = rs.getString("name");
				donorRole = rs.getString("role");
				
				donor.setEmail(donorEmail);
				donor.setName(donorName);
				donor.setRole(donorRole);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			conn.close();
			pstmt.close();
		}
		
		return donor;
	}
	
	/** Admin Login **/
	public Admin adminLogin(Admin admin)
	{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Admin adminObj = null;
		try {
			String adminEmail = admin.getEmail();
			String adminPassword = admin.getPassword();
			conn = ConnectionUtil.getConnection();
			String sql_stmt = "SELECT name,email,role,date FROM admin WHERE email = ? AND password = ?";
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setString(1, adminEmail);
			pstmt.setString(2, adminPassword);
			ResultSet rs = pstmt.executeQuery();
			adminObj = new Admin();
			
			while(rs.next())
			{
				String name = rs.getString("name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String date = rs.getString("date");
				adminObj.setName(name);
				adminObj.setEmail(email);
				adminObj.setRole(role);
				adminObj.setDate(date);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return adminObj;
		
	}
	
	/** Donation request **/
	public void donationRequest(DonationRequest request)
	{
		/** Get request details **/
		String requestType = request.getRequestType();
		String description = request.getDescription();
		Double requestAmount = request.getRequestAmount();
		Integer adminId = request.getAdminId();
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		try {
			
			conn = ConnectionUtil.getConnection();
			
			String sql_stmt = "INSERT INTO donation_request(request_type,description,request_amount,admin_id) VALUES(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql_stmt);
			
			pstmt.setString(1, requestType);
			pstmt.setString(2, description);
			pstmt.setDouble(3, requestAmount);
			pstmt.setInt(4, adminId);
			
			int rows = pstmt.executeUpdate();
			System.out.println(rows + " " + "rows affected!");
		}
		
		catch(SQLException e)
		{
//			e.getMessage();
			e.printStackTrace();
		}
		
	}
	
	/** List donation request **/
	public List<DonationRequest> listDonationRequest() throws SQLException
	{
		
		Connection conn = null;
		DonationRequest request = null;
		PreparedStatement pstmt = null;
		List<DonationRequest> list = null;
		
		try {
			
			String requestType;
			String description;
			Double requestAmount;
			String date;
			
			conn = ConnectionUtil.getConnection();
			
			String sql_stmt = "SELECT request_type,description,request_amount,date FROM donation_request";
			
			pstmt = conn.prepareStatement(sql_stmt);
			
			ResultSet rs = pstmt.executeQuery();
			
			list = new ArrayList<DonationRequest>();
			
			request = new DonationRequest();
			
			while(rs.next())
			{
				requestType = rs.getString("request_type");
				description = rs.getString("description");
				requestAmount = rs.getDouble("request_amount");
				date = rs.getString("date");
				
				request.setRequestType(requestType);
				request.setDescription(description);
				request.setRequestAmount(requestAmount);
				request.setDate(date);
				
				/** Add data to list **/
				list.add(request);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			conn.close();
			pstmt.close();
		}
		
		return list;
	}
	
	/** Get balance **/
	
	public Double balanceEnquiry(Long accountNo) throws SQLException
	{
		
		Double amount = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql_stmt = "SELECT amount FROM bank_account WHERE account_no = ?";
		
		try {

			conn = ConnectionUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setLong(1, accountNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				amount = rs.getDouble("amount");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
			pstmt.close();
		}
		
		return amount;
		
	}
	
	/** Get donation request balance **/
	
	public Double donationRequestBalance(Integer requestId)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		Double requestAount = null;
		try {
			String  sql_stmt = "SELECT request_amount FROM donation_request WHERE request_id = ?";
			
			conn = ConnectionUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setInt(1, requestId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				requestAount = rs.getDouble("request_amount");
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return requestAount;
	}
	
	/** Update money **/
	
	public Integer updateMoney(Transaction transaction)
	{
		Long accountNo = transaction.getAccountNo();
		Double amount = transaction.getAmount();
		Integer pinNo = transaction.getPinNo();
		String transactiontype = transaction.getTransactionType();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_stmt = null;
		if(transactiontype == "debit")
		{
			sql_stmt = "UPDATE bank_account SET amount = amount - ? WHERE account_no = ? AND pin_no = ?";
		}else {
			sql_stmt = "UPDATE bank_account SET amount = amount + ? WHERE account_no = ? AND pin_no = ?";
		}
		int rows = 0;
		try {
			conn = ConnectionUtil.getConnection();
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setDouble(1, amount);
			pstmt.setLong(2, accountNo);
			pstmt.setInt(3, pinNo);
			
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows;
		
	}
	
	/** Update donation request amount **/
	
	public void updateRequestAmount(DonationRequest request)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			String sql_stmt = "UPDATE donation_request SET request_amount = request_amount - ? WHERE request_id = ?";
			pstmt = conn.prepareStatement(sql_stmt);
			pstmt.setDouble(1, request.getAmount());
			pstmt.setInt(2, request.getRequestId());
			pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
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
