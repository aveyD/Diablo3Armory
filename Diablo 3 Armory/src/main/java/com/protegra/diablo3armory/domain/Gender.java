package com.protegra.diablo3armory.domain;

public enum Gender {
    MALE("male", 0),
    FEMALE("female", 1);

    private String toString;
    private int value;

    private Gender (String toString, int value){
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
