package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;
import java.util.Date;

public class Death implements Serializable {
    private long killer = 0;
    private int location = 0;
    private Date time = new Date();

    public long getKiller() {
        return killer;
    }

    public void setKiller(long killer) {
        this.killer = killer;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
