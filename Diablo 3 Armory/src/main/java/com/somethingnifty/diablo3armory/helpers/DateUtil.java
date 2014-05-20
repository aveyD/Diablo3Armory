package com.somethingnifty.diablo3armory.helpers;

import org.json.JSONException;

import java.util.Date;

public class DateUtil
{
    private static final long TIME_MULTIPLIER = 1000;

    protected static Date getDate(Long longDate) throws JSONException
    {
        Date date = new Date();
        date.setTime(longDate * TIME_MULTIPLIER);

        return date;
    }
}
