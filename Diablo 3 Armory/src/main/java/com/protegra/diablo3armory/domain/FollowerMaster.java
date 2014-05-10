package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.FollowerType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FollowerMaster implements Serializable{
    private Map<FollowerType, Follower> followersByFollowerType = new HashMap<FollowerType, Follower>();

    public Follower getFollower(FollowerType followerType){
        return followersByFollowerType.get(followerType);
    }

    public void setFollower(FollowerType followerType, Follower follower){
        followersByFollowerType.put(followerType, follower);
    }
}
