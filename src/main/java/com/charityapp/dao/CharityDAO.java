package com.charityapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.charityapp.model.Donor;
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
			
			while(rs.next())
			{
				donorEmail = rs.getString("email");
				donorName = rs.getString("name");
				donor.setEmail(donorEmail);
				donor.setName(donorName);
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
	
	public void donorRequest()
	{
		
		
	}

}
