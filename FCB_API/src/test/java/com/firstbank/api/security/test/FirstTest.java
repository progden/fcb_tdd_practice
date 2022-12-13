package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.controller.InwardRemittance;
import com.firstbank.api.controller.InwardRemittanceClaim;
import com.firstbank.api.model.ClaimInputModel;
import org.junit.jupiter.api.Assertions;
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

    @BeforeEach
    public void init(){
        irService = mock(InwardRemittance.class);
        claimService = new InwardRemittanceClaim(irService);
    }

    @Test
    public void GivenEmptySeqNOShouldError() {
        // AAA
        // Arrange
        ClaimInputModel model = new ClaimInputModel();
        model.setSeqno(3);

        // Act
        var rs = claimService.claim(model);

        // Assert
        Assertions.assertEquals("error-001", rs.getErrorCode());
    }

    @Test
    public void GivenSevenSeqNo() {
        // AAA
        // Arrange
        ClaimInputModel model = new ClaimInputModel();
        model.setSeqno(1234567);

        // Act
        var rs = claimService.claim(model);

        // Assert
        Assertions.assertEquals("error-001", rs.getErrorCode());
    }
}
