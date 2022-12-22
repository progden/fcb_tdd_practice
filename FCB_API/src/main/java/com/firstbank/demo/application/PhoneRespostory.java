package com.firstbank.demo.application;

import com.firstbank.demo.application.controller.PhoneInput;
import java.sql.SQLException;

public interface PhoneRespostory {

	boolean save(PhoneInput phoneInput);
}
