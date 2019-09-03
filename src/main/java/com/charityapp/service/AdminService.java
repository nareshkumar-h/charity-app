package com.charityapp.service;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.model.Admin;

public class AdminService {
	
	public static void adminLoginService(Admin admin)
	{
		Charity charity = new CharityDAO();
		
		charity.adminLogin(admin);
	}

}
