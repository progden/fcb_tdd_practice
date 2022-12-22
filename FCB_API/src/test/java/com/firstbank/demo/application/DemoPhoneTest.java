package com.firstbank.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.firstbank.demo.application.controller.DemoPhoneController;
import com.firstbank.demo.application.controller.PhoneInput;
import com.firstbank.demo.application.controller.PhoneService;
import com.firstbank.demo.application.exception.PhoneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class DemoPhoneTest {

	PhoneService phoneService;
	DemoPhoneController demoPhoneController;
	PhoneInput phoneInput;
	PhoneRespostory phoneRespostory;

	private boolean res;

	@BeforeEach
	public void setUp() {
		phoneService = mock(PhoneService.class);
		phoneRespostory = mock(PhoneRespostory.class);
		demoPhoneController = new DemoPhoneController(phoneService, phoneRespostory);
		phoneInput = new PhoneInput();
		phoneInput.setPhoneNum("0980888888");
		phoneInput.setAge(18);
		phoneInput.setIdNum("A123456789");
		phoneInput.setSex("M");
		when(phoneService.checkIdNum(phoneInput.getIdNum())).thenReturn(true);
	}

	@Test
	public void 當門號已被申請過_進行申辦_應呼叫電話驗證AND手機不可申辦() {
		// arrange
		when(phoneService.checkPhoneNum(anyString())).thenReturn(true);

		// act
		doApply();

		// assert
		isCheckPhoneServicehasCall(verify(phoneService), anyString());
		shouldNotBeApply();
	}

	private void shouldNotBeApply() {
		assertFalse(res);
	}

	private void doApply() {
		res = demoPhoneController.createPhoneNum(phoneInput);
	}

	private void isCheckPhoneServicehasCall(PhoneService phoneService, String phoneNum) {
		phoneService.checkPhoneNum(phoneNum);
	}

	@Test
	public void 當年齡小於18_進行申辦_不可申辦手機() {
		phoneInput.setAge(17);

		doApply();

		shouldNotBeApply();
	}

	@Test
	public void 當門號未申請過_進行申辦_手機可以申辦() {
		// arrange
		when(phoneService.checkPhoneNum(anyString())).thenReturn(false);

		// act
		doApply();

		// assert
		isCheckPhoneServicehasCall(verify(phoneService), anyString());
		assertTrue(res);
	}

	@Test
	public void 當年齡為18可以申辦手機() {
		boolean res = demoPhoneController.createPhoneNum(phoneInput);
		assertTrue(res);
	}

	@Test
	public void 當年齡大於18進行申辦手機格應該可以申請手機() {
		phoneInput.setAge(19);
		boolean res = demoPhoneController.createPhoneNum(phoneInput);
		assertTrue(res);
	}

	@Test
	public void 當查詢門號發生連線異常_進行申辦_應該不可以申辦() {
		//doThrow(RuntimeException.class).when(phoneService).checkPhoneNum(any());
		when(phoneService.checkPhoneNum(any())).thenThrow(RuntimeException.class);
		boolean res = demoPhoneController.createPhoneNum(phoneInput);
		verify(phoneService).checkPhoneNum(phoneInput.getPhoneNum());
		assertFalse(res);
	}

	@Test
	public void 年滿18且門號未被申請_進行申辦_應儲存成功() {
		boolean res = demoPhoneController.createPhoneNum(phoneInput);
		verify(phoneRespostory).save(any());
		assertTrue(res);
	}

	@Test
	public void 年滿18且門號未被申請且儲存連線異常_進行申辦_拋出例外_error_019() {
		doThrow(RuntimeException.class).when(phoneRespostory).save(any());

		RuntimeException err = assertThrows(RuntimeException.class,
			() -> demoPhoneController.createPhoneNum(phoneInput));

		assertEquals("error-019", err.getMessage());
	}

	@Test
	public void 年滿18歲且驗證身份證號_申請門號_預期申請成功(){
		// arrange
		when(phoneService.checkIdNum(phoneInput.getIdNum())).thenReturn(true);
		//act
		doApply();
		//assert
		verify(phoneService).checkIdNum(any());
		assertTrue(res);

	}

	@Test
	public void 年滿18歲且身份證號格式錯誤_申請門號_預期申請失敗(){
		// arrange
		when(phoneService.checkIdNum(phoneInput.getIdNum())).thenReturn(false);
		//act
		doApply();
		//assert
		verify(phoneService).checkIdNum(any());
		shouldNotBeApply();
	}

	@Test
	public void 年滿18歲且身份證號連線失敗_申請門號_預期申請失敗(){
		// arrange
		when(phoneService.checkIdNum(phoneInput.getIdNum())).thenThrow(PhoneException.class);
		//act
		doApply();
		//assert
		verify(phoneService, times(1)).checkIdNum(any());
		verify(phoneService, times(1)).checkPhoneNum(any());
		shouldNotBeApply();
	}

	@Test
	public void 年滿18歲儲存連線異常_申請門號_預期回傳例外(){
		// arrange
		//when(phoneRespostory.save(any())).thenThrow(SQLException.class);
		doThrow(RuntimeException.class).when(phoneRespostory).save(any());

		//act
		RuntimeException err = assertThrows(PhoneException.class,
			() -> doApply());
		//assert
		verify(phoneService, times(1)).checkIdNum(any());


		assertEquals("error-019",err.getMessage());
	}

	@Test
	public void 一個成年的使用者_進行申辦手機_應該是個一般L0用戶(){
		// arrange
		when(phoneRespostory.save(any())).thenReturn(true);

		// act
		doApply();
		// assert
		shouldBeUserLevel("L0");
	}

	private void
	shouldBeUserLevel(String L0) {
		ArgumentCaptor<PhoneInput> captor = ArgumentCaptor.forClass(PhoneInput.class);
		verify(phoneRespostory).save(captor.capture());
		assertEquals(L0,captor.getValue().getLevel());
	}

	@Test
	public void 一個30歲以上的使用者_進行申辦手機_應該是個一般LMagician用戶(){
		// arrange
		phoneInput.setAge(31);
		when(phoneRespostory.save(any())).thenReturn(true);

		// act
		doApply();

		// assert
		shouldBeUserLevel("LMagician");
	}

	@Test
	public void 一個50歲以上的使用者_進行申辦手機_應該是個一般LMaster用戶(){
		// arrange
		phoneInput.setAge(50);
		when(phoneRespostory.save(any())).thenReturn(true);

		// act
		doApply();

		// assert
		shouldBeUserLevel("LMaster");
	}
}
