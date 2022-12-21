package com.firstbank.api.interfa;

import com.firstbank.api.model.ApplyPhoneNumberInputModel;
import com.firstbank.api.model.CheckPhoneNumberOutputModel;

public class ApplyPhoneNumberService {
    //
    private final CheckNubmerService checkNubmerService;
    private final ApplyRepository applyRepository;
    private UnitRepository unitRepository;

    public ApplyPhoneNumberService(CheckNubmerService checkNubmerService, ApplyRepository applyRepository) {
        this.checkNubmerService = checkNubmerService;
        this.applyRepository = applyRepository;
    }

    public CheckPhoneNumberOutputModel apply(ApplyPhoneNumberInputModel input) {
        CheckPhoneNumberOutputModel rs = new CheckPhoneNumberOutputModel();
        // boolean goNext => 判斷要不要進入判斷
        if (valiateAgeGreateThen18(input)) {
            rs.setReturnMsg("fail");
            return rs;
        }

        try {
            if (validatePhoneNumberCorrect(input)) {
                rs.setReturnMsg("fail");
                return rs;
            }
        } catch (RuntimeException ex) {
            rs.setReturnMsg("fail");
            return rs;
        }
        // String unitCode = null;
        // String currency = null;
        // boolean validCurrency = unitRepository.validateCurrency(unitCode, currency);

        try {
            applyRepository.save();
        } catch (RuntimeException ex) {
            throw new RuntimeException("error-019");
        }
        rs.setReturnMsg("success");
        return rs;
    }

    private boolean validatePhoneNumberCorrect(ApplyPhoneNumberInputModel input) {
        return !checkNubmerService.check(input.getPhoneNumber());
    }

    private static boolean valiateAgeGreateThen18(ApplyPhoneNumberInputModel input) {
        return input.getAge() < 18;
    }
}
