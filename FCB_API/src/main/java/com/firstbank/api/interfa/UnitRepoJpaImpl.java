package com.firstbank.api.interfa;

public class UnitRepoJpaImpl implements UnitRepository{
    @Override
    public boolean validateCurrency(String unitCode, String currency) {
        return false;
    }
}
