package com.somethingnifty.diablo3armory.domain;

public class ActiveSkill extends Skill {
    private Skill skill;
    private Rune rune;

    public Skill getSkill()
    {
        return skill;
    }

    public void setSkill(Skill skill)
    {
        this.skill = skill;
    }

    public Rune getRune()
    {
        return rune;
    }

    public void setRune(Rune rune)
    {
        this.rune = rune;
    }
}
