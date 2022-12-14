package com.firstbank.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = SpringAppBootstrapper.class)
public class InwardRemittanceClaimTest {


	InwardRemittanceClaim claimService;
	private InwardRemittance irService;

	ClaimInputModel  model = null;
	@BeforeEach
	public void init(){
		irService = mock(InwardRemittance.class);
		claimService = new InwardRemittanceClaim(irService);
		model= new ClaimInputModel();
		model.setSeqNo(1234567);
		model.setRecvBranch(123);
		model.setTxVersion(66);
		model.setClaimAmt(new BigDecimal(399.325));
		model.setClaimFxrate(new BigDecimal("1234.56789"));
	}



	@Test
	@DisplayName(value = "測試seqno 為7碼數字")
	public void GivenSeqNOShouldLength7() {
		// AAA
		// Arrange


		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnSuccess(rs);
	}

	private static void shouldReturnSuccess(ClaimOutputModel rs) {
		assertEquals("0000", rs.getErrorCode());
	}

	@Test
	@DisplayName(value = "測試seqno 為8碼數字")
	@ValueSource
	public void GivenSeqNONotLength7ShouldReturnError() {
		// AAA
		// Arrange
		model.setSeqNo(12345678);

		// Act
		var rs = claimService.claim(model);


		// Assert

		shouldReturnError(rs, "error-001");
	}

	private static void shouldReturnError(ClaimOutputModel rs, String expected) {
		Assertions.assertEquals(expected, rs.getErrorCode());
	}

	@Test
	@DisplayName(value = "測試recvbranch 為3碼數字")
	public void GivenRecvBranchShouldLength3(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnError(rs, "0000");
	}

	@Test
	@DisplayName(value = "測試txversion 為2碼數字")

	public void GivenTxVersionShouldLength2(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnError(rs, "0000");
	}


	@Test
	@DisplayName(value = "測試SeqNo 只能是數字")

	public void GivenSeqNoShouldNum(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnError(rs, "0000");
	}


	@Test
	@DisplayName(value = "測試ClaimAmount只能是數字")

	public void GivenClaimAmountShouldNum(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnError(rs, "0000");
	}
	@Test
	@DisplayName(value = "測試即期買入claimFxrate必需為前4碼後5碼格式數字e.g 1234.56789")

	public void GivenclaimFxrateFormat4point5ShouldReturnSuccess(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnSuccess(rs);

	}
	@Test
	@DisplayName(value = "測試即期買入claimFxrate必需不為前4碼後5碼格式數字e.g 1234.56789")
	public void GivenclaimFxrateErrorFormat4point5ShouldReturnError(){

		model.setClaimFxrate(new BigDecimal(12345.6789));

		// Act
		var rs = claimService.claim(model);


		// Assert
		shouldReturnError(rs, "error-014");

	}

}
