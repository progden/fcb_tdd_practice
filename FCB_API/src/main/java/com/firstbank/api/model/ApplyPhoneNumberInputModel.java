package com.firstbank.api.model;

public class ApplyPhoneNumberInputModel {

    private int age;
    private String phoneNumber;
    private String usrLevel;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsrLevel() {
        return usrLevel;
    }

    public void setUsrLevel(String usrLevel) {
        this.usrLevel = usrLevel;
    }
}
