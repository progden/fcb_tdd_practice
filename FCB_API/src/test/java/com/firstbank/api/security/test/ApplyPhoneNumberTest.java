package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.interfa.ApplyPhoneNumberService;
import com.firstbank.api.interfa.ApplyRepository;
import com.firstbank.api.interfa.CheckPhoneNumberService;
import com.firstbank.api.model.ApplyPhoneNumberInputModel;
import com.firstbank.api.model.ApplyPhoneNumberOutputModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class ApplyPhoneNumberTest {

    public static final String INVALID_PHONE_NUMBER = "0987654322";
    public static final String VALID_PHONE_NUMBER = "0987654321";
    ApplyPhoneNumberService applyPhoneNumberService;
    private CheckPhoneNumberService checkNubmerService;
    private ApplyRepository applyRepository;
    private ApplyPhoneNumberInputModel applyInput;

    // BeforeEach 讓程式一進來先固定跑這段
    @BeforeEach
    public void init() {
        // mock 實作interface
        checkNubmerService = mock(CheckPhoneNumberService.class);
        applyRepository = mock(ApplyRepository.class);

        applyPhoneNumberService = new ApplyPhoneNumberService(checkNubmerService, applyRepository);
        applyInput = new ApplyPhoneNumberInputModel();
        applyInput.setAge(18);
        applyInput.setPhoneNumber(VALID_PHONE_NUMBER);

        when(checkNubmerService.checkPhoneNumber(eq(VALID_PHONE_NUMBER))).thenReturn(true);
        when(checkNubmerService.checkPhoneNumber(eq(INVALID_PHONE_NUMBER))).thenReturn(false);

    }

    @Test
    public void 當年齡17歲的人_進行申辦手機_預期申辦失敗() {
        // Arrange
        applyInput.setAge(17);

        // Act
        ApplyPhoneNumberOutputModel rs = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldIncorrect(rs);
    }

    // 拉出來重構，簡短原本測試方法內的程式碼
    @Test
    public void 當年齡剛好18歲的人_進行申辦手機_預期申辦成功() {
        // Arrange
        applyInput.setAge(18);

        // Act
        ApplyPhoneNumberOutputModel rs = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldCorrect(rs);
    }

    @Test
    public void 當年齡19歲的人_進行申辦手機_預期申辦失敗() {
        // Arrange
        applyInput.setAge(19);

        // Act
        ApplyPhoneNumberOutputModel rs = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldCorrect(rs);
    }

    @Test
    public void 驗證門號未被申請過_進行手機申辦_預期申辦成功() {
        // Arrange

        // Act
        ApplyPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldCorrect(output);
    }

    @Test
    public void 驗證門號已被申請過_進行手機申辦_預期申辦失敗() {

        // Arrange
        applyInput.setPhoneNumber(INVALID_PHONE_NUMBER);

        // Act
        ApplyPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldIncorrect(output);
    }

    @Test
    public void 查詢門號發生連線異常_進行申請_不可以申辦手機() {

        // Arrange
        // 當check被呼叫到時,丟出RuntimeException
        doThrow(RuntimeException.class).when(checkNubmerService).checkPhoneNumber(any());

        // Act
        ApplyPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldIncorrect(output);
    }

    @Test
    public void 滿18且門號未被申請_進行申請_應儲存成功() {

        // Arrange

        // Act
        ApplyPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verify(applyRepository).save();
        applyPhoneNumberShouldCorrect(output);
    }

    @Test
    public void 滿18且門號未被申請且儲存連線發生異常_進行申請_拋出例外error_019() {

        // Arrange
        doThrow(RuntimeException.class).when(applyRepository).save();

        // Act
        //當apply丟出例外,而且例外是RuntimeException，把錯誤指給ex
        var ex = assertThrows(RuntimeException.class,
                () -> applyPhoneNumberService.apply(applyInput));

        // Assert
        verify(applyRepository).save();
        assertEquals("error-019", ex.getMessage());
    }

    private boolean verifyCheckIsCalled() {
        return verify(checkNubmerService).checkPhoneNumber(anyString());
    }

    private static void applyPhoneNumberShouldCorrect(ApplyPhoneNumberOutputModel output) {
        assertEquals("success", output.getReturnMsg());
    }

    private static void applyPhoneNumberShouldIncorrect(ApplyPhoneNumberOutputModel output) {
        // 判斷回傳的output內的ReturnMsg跟期望值要一致
        assertEquals("fail", output.getReturnMsg());
    }
}
