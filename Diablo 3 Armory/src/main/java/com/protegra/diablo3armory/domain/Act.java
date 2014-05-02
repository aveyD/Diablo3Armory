package com.protegra.diablo3armory.domain;

import java.util.List;

/**
 * Created by David on 01/05/2014.
 */
public class Act {
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
