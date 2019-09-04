package com.charityapp.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.charityapp.model.Donor;
import com.charityapp.service.DonorService;

public class DonorTestCase {

	/** Donor register **/
	@Test
	public void donorRegister() {
		Donor donor = new Donor();
		donor.setName("ajithkumar12");
		donor.setEmail("ajithkumar12@gmail.com");
		donor.setPassword("mypass");
		donor.setDob("05-06-1997");
		donor.setGender('m');
		donor.setRole("role_donor");
		int rows = DonorService.donorRegisterService(donor);
		
		/** Check Donor Register **/
		assertEquals(1, rows);
	}

	/** Donor login **/
	@Test
	public void donorLogin() {
		Donor donor = new Donor();
		donor.setEmail("krishna@gmail.com");
		donor.setPassword("mypass");
		donor = DonorService.donorLoginService(donor);
		assertNotNull(donor);
		assertEquals("role_donor", donor.getRole());
	}

}
