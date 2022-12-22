package com.firstbank.api.repo;

import com.firstbank.api.model.ApplyPhoneNumberInputModel;
import com.firstbank.api.model.ApplyPhoneNumberOutputModel;

public class ApplyPhoneNumberService {
    //
    private final CheckPhoneNumberRepo checkNumberService;
    private final ApplyRepository applyRepository;
    // private UnitRepository unitRepository;

    public ApplyPhoneNumberService(CheckPhoneNumberRepo checkNubmerService, ApplyRepository applyRepository) {
        this.checkNumberService = checkNubmerService;
        this.applyRepository = applyRepository;
    }

    public ApplyPhoneNumberOutputModel apply(ApplyPhoneNumberInputModel input) {
        ApplyPhoneNumberOutputModel rs = new ApplyPhoneNumberOutputModel();

        if (validateAgeGreaterThen18(input)) {
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

        try {
            applyRepository.save();
        } catch (RuntimeException ex) {
            throw new RuntimeException("error-019");
        }
        rs.setReturnMsg("success");
        return rs;
    }

    private boolean validatePhoneNumberCorrect(ApplyPhoneNumberInputModel input) {
        return !checkNumberService.checkPhoneNumber(input.getPhoneNumber());
    }

    private static boolean validateAgeGreaterThen18(ApplyPhoneNumberInputModel input) {
        return input.getAge() < 18;
    }
}
