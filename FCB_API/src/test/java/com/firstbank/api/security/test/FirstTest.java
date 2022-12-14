package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.controller.InwardRemittance;
import com.firstbank.api.controller.InwardRemittanceClaim;
import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class FirstTest {

    InwardRemittanceClaim claimService;
    private InwardRemittance irService;
    private ClaimInputModel model;

    @BeforeEach
    public void init() {
        irService = mock(InwardRemittance.class);
        claimService = new InwardRemittanceClaim(irService);
        model = new ClaimInputModel();
        model.setSeqno(1234567);
        model.setRecvBranch(121);
        model.setDealingDate(new Date());
        GivenProcessMode("C");
    }

    @Test
    public void GivenCorrectClaimInputShouldNoDoAnything() {
        // AAA
        // Arrange
        // Act
        var rs = claimService.claim(model);

        // Assert
        assertNull(rs.getErrorCode());
    }

    @Test
    public void GivenEmptySeqNOShouldError() {
        // AAA
        // Arrange
        model.setSeqno(0);

        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-001", rs);
    }

    @Test
    public void GivenLessSevenSeqNo() {
        // AAA
        // Arrange
        model.setSeqno(123456);

        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-001", rs);
    }

    private static void shouldReturnError(String expected, ClaimOutputModel rs) {
        assertEquals(expected, rs.getErrorCode());
    }

    @Test
    public void GivenLessThreeRecvBranchShouldError() {
        // AAA
        // Arrange
        model.setRecvBranch(12);
        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-006", rs);
    }

    @Test
    public void GivenOverThreeRecvBranchShouldError() {
        // AAA
        // Arrange
        model.setRecvBranch(1212);
        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-006", rs);
    }

    @Test
    public void GivenProcessModeIsXShouldReturnError004() {
        // AAA
        // Arrange
        GivenProcessMode("X");
        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-004", rs);
    }
    @Test
    public void GivenProcessModeIsNullShouldReturnError004() {
        // AAA
        // Arrange
        GivenProcessMode(null);
        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-004", rs);
    }

    private void GivenProcessMode(String X) {
        model.setProcessMode(X);
    }
    @Test
    public void GivenDealingDateIsWrongFormatShouldReturnError007(){
        // AAA
        // Arrange
        model.setDealingDate(null);
        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldReturnError("error-007", rs);
    }
}