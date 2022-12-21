package com.firstbank.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.firstbank.api.demo.controller.DemoPhoneController;
import com.firstbank.api.demo.controller.PhoneInput;
import com.firstbank.api.demo.controller.PhoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoPhoneTest {

	PhoneService phoneService;
	DemoPhoneController demoPhoneController;
	PhoneInput phoneInput;
	PhoneRespostory phoneRespostory;
	private boolean res;

	@BeforeEach
	public void init() {
		phoneService = mock(PhoneService.class);
		phoneRespostory = mock(PhoneRespostory.class);
		demoPhoneController = new DemoPhoneController(phoneService, phoneRespostory);
		phoneInput = new PhoneInput();
		phoneInput.setPhoneNum("0980888888");
		phoneInput.setAge(18);
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
		doThrow(RuntimeException.class).when(phoneService).checkPhoneNum(any());
		boolean res = demoPhoneController.createPhoneNum(phoneInput);
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
}
