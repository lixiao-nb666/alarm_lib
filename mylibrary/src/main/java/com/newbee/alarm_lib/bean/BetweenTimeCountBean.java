package com.newbee.alarm_lib.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class BetweenTimeCountBean implements Serializable{
    private long betweenTime;//间隔时间
    private long lastTime;//上次时间


    public BetweenTimeCountBean(long betweenTime, long lastTime) {
        this.betweenTime = betweenTime;
        this.lastTime = lastTime;
    }

    public long getBetweenTime() {
        return betweenTime;
    }

    public void setBetweenTime(long betweenTime) {
        this.betweenTime = betweenTime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "BetweenTimeCountBean{" +
                "betweenTime=" + betweenTime +
                ", lastTime=" + lastTime +
                '}';
    }
}
