package com.firstbank.api.controller;

import static org.mockito.Mockito.mock;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.model.ClaimInputModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = SpringAppBootstrapper.class)
public class InwardRemittanceClaimTest {


	InwardRemittanceClaim claimService;
	private InwardRemittance irService;

	@BeforeEach
	public void init(){
		irService = mock(InwardRemittance.class);
		claimService = new InwardRemittanceClaim(irService);
	}

	@Test
	@DisplayName(value = "測試seqno 不為空")
	public void GivenEmptySeqNOShouldError() {
		// AAA
		// Arrange
		ClaimInputModel model = new ClaimInputModel();
		model.setSeqNo(0);

		// Act
		var rs = claimService.claim(model);

		// Assert
		Assertions.assertEquals("error-001", rs.getErrorCode());
	}
}
