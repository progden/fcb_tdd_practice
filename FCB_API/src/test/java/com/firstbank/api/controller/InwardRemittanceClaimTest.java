package com.firstbank.api.controller;

import static org.mockito.Mockito.mock;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.model.ClaimInputModel;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	}



	@Test
	@DisplayName(value = "測試seqno 為7碼數字")
	@Order(1)
	public void GivenSeqNOShouldLength7() {
		// AAA
		// Arrange


		// Act
		var rs = claimService.claim(model);


		// Assert
		Assertions.assertEquals("0000", rs.getErrorCode());
	}
	@Test
	@DisplayName(value = "測試recvbranch 為3碼數字")
	@Order(2)
	public void GivenRecvBranchShouldLength3(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		Assertions.assertEquals("0000", rs.getErrorCode());
	}

	@Test
	@DisplayName(value = "測試txversion 為2碼數字")
	@Order(3)
	public void GivenTxVersionShouldLength2(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		Assertions.assertEquals("0000", rs.getErrorCode());
	}


	@Test
	@DisplayName(value = "測試SeqNo 只能是數字")
	@Order(4)
	public void GivenSeqNoShouldNum(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		Assertions.assertEquals("0000", rs.getErrorCode());
	}


	@Test
	@DisplayName(value = "測試ClaimAmount只能是數字")
	@Order(5)
	public void GivenClaimAmountShouldNum(){



		// Act
		var rs = claimService.claim(model);


		// Assert
		Assertions.assertEquals("0000", rs.getErrorCode());
	}
}
