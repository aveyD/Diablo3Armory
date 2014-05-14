package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;

public class WeaponType implements Serializable {
    private boolean twoHanded;
    private String id;

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
