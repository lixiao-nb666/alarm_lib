package com.newbee.alarm_lib.bean;


import com.newbee.alarm_lib.listen.ListenEventAfterTime;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/15 0015.
 */

public class EventAfterTimeCountBean implements Serializable{
    private ListenEventAfterTime listenEventAfterTime;
    private String tag;//事件的标识
    private long eventTime;//事件触发的时间
    private long eventWaitTime;//事件等待的时间

    public EventAfterTimeCountBean(ListenEventAfterTime listenEventAfterTime, String tag, long eventTime, long eventWaitTime) {
        this.listenEventAfterTime = listenEventAfterTime;
        this.tag = tag;
        this.eventTime = eventTime;
        this.eventWaitTime = eventWaitTime;
    }

    public ListenEventAfterTime getListenEventAfterTime() {
        return listenEventAfterTime;
    }

    public void setListenEventAfterTime(ListenEventAfterTime listenEventAfterTime) {
        this.listenEventAfterTime = listenEventAfterTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public long getEventWaitTime() {
        return eventWaitTime;
    }

    public void setEventWaitTime(long eventWaitTime) {
        this.eventWaitTime = eventWaitTime;
    }
}
