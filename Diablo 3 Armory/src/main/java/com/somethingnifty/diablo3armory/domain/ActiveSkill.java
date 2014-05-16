package com.somethingnifty.diablo3armory.domain;

public class ActiveSkill extends Skill {
    private String categorySlug;
    private Rune rune;

    public Rune getRune()
    {
        return rune;
    }

    public void setRune(Rune rune)
    {
        this.rune = rune;
    }

    public String getCategorySlug() { return categorySlug; }

    public void setCategorySlug(String categorySlug) { this.categorySlug = categorySlug; }
}
