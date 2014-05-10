package com.protegra.diablo3armory.domain.enums;

public enum Gender {
    MALE(0, "male"),
    FEMALE(1, "female");

    private String toString;
    private int value;

    private Gender(int value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getToString()
    {
        return toString;
    }

    public int getValue(){
        return value;
    }

    public static Gender getGender(int genderInt){
        Gender gender = null;

        if (MALE.getValue() == genderInt) {
            gender = MALE;
        }
        else if (FEMALE.getValue() == genderInt) {
            gender = FEMALE;
        }

        return gender;
    }

    public static Gender getGender(String genderString){
        Gender gender = null;

        if (MALE.getToString().equals(genderString)) {
            gender = MALE;
        }
        else if (FEMALE.getToString().equals(genderString)) {
            gender = FEMALE;
        }

        return gender;
    }
}
