package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.controller.InwardRemittance;
import com.firstbank.api.controller.InwardRemittanceClaim;
import com.firstbank.api.model.ClaimInputModel;
import com.firstbank.api.model.ClaimOutputModel;
import com.firstbank.api.model.QueryIrInputModel;
import com.firstbank.api.model.QueryIrOutputModel;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Data
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class IRClaimCompleteTest {

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

    private void GivenProcessMode(String X) {
        model.setProcessMode(X);
    }

    @Test
    public void GivenQueryIRWithCorrectModelShouldNoDoAnything() {
        // AAA
        // Arrange
        GivenQueryIRWithDataReturnErrorCode(null);
        // Act
        var rs = claimService.claim(model);

        // Assert
        assertNull(rs.getErrorCode());
    }

    @Test
    public void GivenQueryIRWithDataNotExistsShouldReturnError021() {
        // AAA
        // Arrange
        GivenQueryIRWithDataReturnErrorCode("error-001");

        // Act
        var rs = claimService.claim(model);

        // Assert
        shouldCallIRQuery();
        shouldReturnError(rs, "error-021");
    }

    private QueryIrOutputModel shouldCallIRQuery() {
        return verify(irService).queryIr(any(QueryIrInputModel.class));
    }

    private void GivenQueryIRWithDataReturnErrorCode(String errorCode) {
        QueryIrOutputModel irDataNotExistsModel = new QueryIrOutputModel();
        irDataNotExistsModel.setErrorCode(errorCode);
        when(irService.queryIr(any(QueryIrInputModel.class)))
                .thenReturn(irDataNotExistsModel);
    }

    private void shouldReturnError(ClaimOutputModel rs, String expected) {
        assertEquals(expected, rs.getErrorCode());
    }


}