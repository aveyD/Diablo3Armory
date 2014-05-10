package com.protegra.diablo3armory.domain;

public class ItemWearableActiveHero extends ItemWearable{
    // advanced item info
    private int requiredLevel;
    private int itemLevel;
    private int bonusAffixes;
    private int bonusAffixesMax;
    private boolean accountBound;
    private String flavorText;
    private String typeName;
    private WeaponType weaponType;
    //Armor armor;

    // TODO: more to come

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public int getBonusAffixes() {
        return bonusAffixes;
    }

    public void setBonusAffixes(int bonusAffixes) {
        this.bonusAffixes = bonusAffixes;
    }

    public int getBonusAffixesMax() {
        return bonusAffixesMax;
    }

    public void setBonusAffixesMax(int bonusAffixesMax) {
        this.bonusAffixesMax = bonusAffixesMax;
    }

    public boolean isAccountBound() {
        return accountBound;
    }

    public void setAccountBound(boolean accountBound) {
        this.accountBound = accountBound;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
}
