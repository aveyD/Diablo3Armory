package com.protegra.diablo3armory.domain;

/**
 * Created by David on 01/05/2014.
 */
public class Type {
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
