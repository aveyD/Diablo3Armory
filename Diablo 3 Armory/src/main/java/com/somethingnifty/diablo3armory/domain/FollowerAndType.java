package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.FollowerType;

public class FollowerAndType {
    private FollowerType followerType;
    private Follower follower;

    public FollowerAndType(FollowerType followerType, Follower follower) {
        this.followerType = followerType;
        this.follower = follower;
    }

    public FollowerType getFollowerType() { return followerType; }

    public void setFollowerType(FollowerType followerType) { this.followerType = followerType; }

    public Follower getFollower() { return follower; }

    public void setFollower(Follower follower) { this.follower = follower; }
}