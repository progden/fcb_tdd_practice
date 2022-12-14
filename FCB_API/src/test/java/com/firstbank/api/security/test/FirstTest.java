package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.controller.CurrencyService;
import com.firstbank.api.controller.InwardRemittance;
import com.firstbank.api.controller.InwardRemittanceClaim;
import com.firstbank.api.model.ClaimInputModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class FirstTest {

    InwardRemittanceClaim claimService;
    private InwardRemittance irService;
    private CurrencyService currencyService;

    @BeforeEach
    public void init(){
        irService = mock(InwardRemittance.class);
        currencyService = mock(CurrencyService.class);
        claimService = new InwardRemittanceClaim(irService, currencyService);
    }

    @Test
    public void GivenEmptySeqNOShouldError() {
        // AAA
        // Arrange
        ClaimInputModel model = new ClaimInputModel();
        //model.setSeqNo("");

        // Act
        var rs = claimService.claim(model);

        // Assert
        //Assertions.assertEquals("error-001", rs.getErrorCode());
    }

}
