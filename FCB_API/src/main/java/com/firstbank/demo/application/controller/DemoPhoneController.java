package com.firstbank.demo.application.controller;

import com.firstbank.demo.application.PhoneRespostory;
import com.firstbank.demo.application.exception.PhoneException;

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
			if(!phoneService.checkIdNum(phoneInput.getIdNum())){
				return false;
			}
		} catch (RuntimeException e) {
			return false;
		}

		////
		SetUserLevel(phoneInput);

		try {
			phoneRespostory.save(phoneInput);
		}catch(Exception e){
			throw new PhoneException("error-019");
		}

		return true;
	}

	private static void SetUserLevel(PhoneInput phoneInput) {
		if(phoneInput.getAge() > 17 ){
			phoneInput.setLevel("L0");
		}
		if(phoneInput.getAge() > 29){
			phoneInput.setLevel("LMagician");
		}
		if(phoneInput.getAge() > 49){
			phoneInput.setLevel("LMaster");
		}
	}
}
