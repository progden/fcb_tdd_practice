package com.firstbank.api.demo.controller;

import com.firstbank.demo.application.IRservice;
import com.firstbank.demo.application.PhoneRespostory;

public class DemoPhoneController {

	private final PhoneRespostory phoneRespostory;
	PhoneService phoneService;

	public DemoPhoneController(PhoneService phoneService,
		PhoneRespostory phoneRespostory) {
		this.phoneService = phoneService;
		this.phoneRespostory = phoneRespostory;

	}

	public boolean createPhoneNum(PhoneInput phoneInput) {
		int age = phoneInput.getAge();
		if (age < 18) {

			return false;
		}



		try {
			if (phoneService.checkPhoneNum(phoneInput.getPhoneNum())) {
				return false;
			}
		} catch (RuntimeException e) {
			return false;
		}

		try {
			phoneRespostory.save(phoneInput);
		}catch(RuntimeException e){
			throw new RuntimeException("error-019");
		}

		return true;
	}
}
