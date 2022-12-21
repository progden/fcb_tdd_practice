package com.firstbank.api.controller;

import org.springframework.stereotype.Service;

@Service
public interface CurrencyService {

	boolean checkValidCurrency(String unitCode, String currCode);
}
