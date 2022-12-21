package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import com.firstbank.api.interfa.ApplyPhoneNumberService;
import com.firstbank.api.interfa.ApplyRepository;
import com.firstbank.api.interfa.CheckNubmerService;
import com.firstbank.api.model.ApplyPhoneNumberInputModel;
import com.firstbank.api.model.CheckPhoneNumberOutputModel;
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
    private CheckNubmerService checkNubmerService;
    private ApplyRepository applyRepository;
    private ApplyPhoneNumberInputModel applyInput;

    // BeforeEach 讓程式一進來先固定跑這段
    @BeforeEach
    public void init() {
        // mock 實作interface
        checkNubmerService = mock(CheckNubmerService.class);
        applyRepository = mock(ApplyRepository.class);

        applyPhoneNumberService = new ApplyPhoneNumberService(checkNubmerService, applyRepository);
        applyInput = new ApplyPhoneNumberInputModel();
        applyInput.setAge(18);
        applyInput.setPhoneNumber(VALID_PHONE_NUMBER);

        when(checkNubmerService.check(eq(VALID_PHONE_NUMBER))).thenReturn(true);
        when(checkNubmerService.check(eq(INVALID_PHONE_NUMBER))).thenReturn(false);

    }

    @Test
    public void 未滿18歲_申辦手機_不可以申辦() {
        // Arrange
        applyInput.setAge(17);

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldIncorrect(output);
    }

    // 拉出來重構，簡短原本測試方法內的程式碼
    @Test
    public void 剛好18歲_申辦手機_可以申辦() {
        // Arrange
        applyInput.setAge(18);

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldCorrect(output);
    }

    @Test
    public void 超過18歲_申辦手機_可以申辦() {
        // Arrange
        applyInput.setAge(19);

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        applyPhoneNumberShouldCorrect(output);
    }

    @Test
    public void 門號未被申請過可以申辦手機() {
        // Arrange

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldCorrect(output);
    }

    @Test
    public void 門號已被申請過不可以申辦手機() {

        // Arrange
        applyInput.setPhoneNumber(INVALID_PHONE_NUMBER);

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldIncorrect(output);
    }

    @Test
    public void 查詢門號發生連線異常_進行申請_不可以申辦手機() {

        // Arrange
        // 當check被呼叫到時,丟出RuntimeException
        doThrow(RuntimeException.class).when(checkNubmerService).check(any());

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

        // Assert
        verifyCheckIsCalled();
        applyPhoneNumberShouldIncorrect(output);
    }

    @Test
    public void 滿18且門號未被申請_進行申請_應儲存成功() {

        // Arrange

        // Act
        CheckPhoneNumberOutputModel output = applyPhoneNumberService.apply(applyInput);

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
        return verify(checkNubmerService).check(anyString());
    }

    private static void applyPhoneNumberShouldCorrect(CheckPhoneNumberOutputModel output) {
        assertEquals("success", output.getReturnMsg());
    }

    private static void applyPhoneNumberShouldIncorrect(CheckPhoneNumberOutputModel output) {
        // 判斷回傳的output內的ReturnMsg跟期望值要一致
        assertEquals("fail", output.getReturnMsg());
    }
}
