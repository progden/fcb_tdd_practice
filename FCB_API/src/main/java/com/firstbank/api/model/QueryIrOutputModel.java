package com.firstbank.api.model;

import lombok.Data;

public class QueryIrOutputModel {
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public IrModel getModel() {
        return model;
    }

    public void setModel(IrModel model) {
        this.model = model;
    }

    private String errorCode;
    private String errorMessage;
    private IrModel model;
}
