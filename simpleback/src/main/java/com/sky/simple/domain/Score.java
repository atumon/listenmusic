package com.sky.simple.domain;

import java.io.Serializable;

public class Score implements Serializable {

    private Long userId;
    private String tag;
    private float score;

    public Score() {
    }

    public Score(Long userId, String tag, float score) {
        this.userId = userId;
        this.tag = tag;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userId=" + userId +
                ", tag='" + tag + '\'' +
                ", score=" + score +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
