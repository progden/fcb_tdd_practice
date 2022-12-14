package com.firstbank.api.model;

import lombok.Data;

public class ClaimOutputModel {
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
