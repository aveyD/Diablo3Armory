package com.protegra.diablo3armory.domain;

/**
 * Created by David on 01/05/2014.
 */
public class Item {
    private String id;
    private String name;
    private String icon;
    private String displayColor;
    private String tooltipParams;
    private RandomAffixes randomAffixes;
    private CraftedBy craftedBy;

    // advanced item info
    private int requiredLevel;
    private int itemLevel;
    private int bonusAffixes;
    private int bonusAffixesMax;
    private boolean accountBound;
    private String flavorText;
    private String typeName;
    private Type type;
    //Armor armor;

    // TODO: more to come

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public void setTooltipParams(String tooltipParams) {
        this.tooltipParams = tooltipParams;
    }

    public RandomAffixes getRandomAffixes() {
        return randomAffixes;
    }

    public void setRandomAffixes(RandomAffixes randomAffixes) {
        this.randomAffixes = randomAffixes;
    }

    public CraftedBy getCraftedBy() {
        return craftedBy;
    }

    public void setCraftedBy(CraftedBy craftedBy) {
        this.craftedBy = craftedBy;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
