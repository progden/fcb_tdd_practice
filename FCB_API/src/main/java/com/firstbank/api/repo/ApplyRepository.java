package com.firstbank.api.repo;

import com.firstbank.api.model.ApplyPhoneNumberInputModel;

public interface ApplyRepository {
     Boolean save(ApplyPhoneNumberInputModel saveInput);
}
