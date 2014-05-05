package com.protegra.diablo3armory.helpers;

public final class BattletagUtil {

    private static final String VALID_BATTLETAG_PATTERN_POUND = "(\\w+)#(\\d{4})";
    private static final String VALID_BATTLETAG_PATTERN_DASH = "(\\w+)-(\\d{4})";

    private BattletagUtil() {

    }

    public static String formatBattletagForWebService(String battletag){
        if(battletag.matches(VALID_BATTLETAG_PATTERN_POUND))
        {
            battletag = battletag.replaceFirst("#", "-");
        }

        return battletag;
    }

    public static boolean isValidWebserviceBattletagFormat(String battletag)
    {
        return battletag.matches(VALID_BATTLETAG_PATTERN_DASH);
    }
}
