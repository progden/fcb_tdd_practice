package com.firstbank.demo.application.controller;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;

public interface PhoneService {

	boolean checkPhoneNum(String phoneNum);

	boolean checkIdNum(String idNum);
}
