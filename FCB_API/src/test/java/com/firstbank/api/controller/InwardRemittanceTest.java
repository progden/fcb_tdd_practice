package com.firstbank.api.controller;


import static org.mockito.Mockito.mock;

import com.firstbank.api.SpringAppBootstrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class InwardRemittanceTest {


	InwardRemittanceClaim claimService;
	private InwardRemittance irService;
	private CurrencyService currencyService;

	@BeforeEach
	public void init(){
		irService = mock(InwardRemittance.class);
		currencyService = mock(CurrencyService.class);
		claimService = new InwardRemittanceClaim(irService, currencyService);
	}



}
