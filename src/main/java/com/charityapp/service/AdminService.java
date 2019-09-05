package com.charityapp.service;

import com.charityapp.dao.CharityDAO;
import com.charityapp.dao.CharityImpl;
import com.charityapp.model.Admin;

public class AdminService {
	
	public static Admin adminLoginService(Admin admin)
	{
		CharityDAO charity = new CharityImpl();
		Admin adminObj = new Admin();
		adminObj = charity.adminLogin(admin);
		return adminObj;
	}

}
