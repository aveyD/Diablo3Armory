package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;

public class Rune implements Serializable {
    private String slug;
    private String type;
    private String name;
    private int level;
    private String description;
    private String simpleDescription;
    private String tooltipParams;
    private String skillCalcId;
    private int order;

    public String getSlug() { return slug; }

    public void setSlug(String slug) { this.slug = slug; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getSimpleDescription() { return simpleDescription; }

    public void setSimpleDescription(String simpleDescription) { this.simpleDescription = simpleDescription; }

    public String getTooltipParams() { return tooltipParams; }

    public void setTooltipParams(String tooltipParams) { this.tooltipParams = tooltipParams; }

    public String getSkillCalcId() { return skillCalcId; }

    public void setSkillCalcId(String skillCalcId) { this.skillCalcId = skillCalcId; }

    public int getOrder() { return order; }

    public void setOrder(int order) { this.order = order; }
}
