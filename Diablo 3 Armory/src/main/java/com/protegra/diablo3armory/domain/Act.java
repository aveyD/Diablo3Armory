package com.protegra.diablo3armory.domain;

import java.io.Serializable;
import java.util.List;

public class Act implements Serializable {
    private boolean completed;
    private List<Quest> completedQuests;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(List<Quest> completedQuests) {
        this.completedQuests = completedQuests;
    }
}
