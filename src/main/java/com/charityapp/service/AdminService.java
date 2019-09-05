package com.charityapp.service;

import com.charityapp.dao.Charity;
import com.charityapp.dao.CharityDAO;
import com.charityapp.model.Admin;

public class AdminService {
	
	public static Admin adminLoginService(Admin admin)
	{
		Charity charity = new CharityDAO();
		Admin adminObj = new Admin();
		adminObj = charity.adminLogin(admin);
		return adminObj;
	}

}
