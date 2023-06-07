package com.newbee.alarm_lib.bean;

import java.io.Serializable;

public class    SystemDeviceTimeInfoBean implements Serializable {
    private long startDeviceNumb;//开机的次数
    private long nowDeviceStartTime;//这次开机的时间
    private long deviceRunTime;//单位是分钟
    private String deviceId;

    public long getStartDeviceNumb() {
        return startDeviceNumb;
    }

    public void setStartDeviceNumb(long startDeviceNumb) {
        this.startDeviceNumb = startDeviceNumb;
    }

    public long getNowDeviceStartTime() {
        return nowDeviceStartTime;
    }

    public void setNowDeviceStartTime(long nowDeviceStartTime) {
        this.nowDeviceStartTime = nowDeviceStartTime;
    }

    public long getDeviceRunTime() {
        return deviceRunTime;
    }

    public void setDeviceRunTime(long deviceRunTime) {
        this.deviceRunTime = deviceRunTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "SystemDeviceTimeInfoBean{" +
                "startDeviceNumb=" + startDeviceNumb +
                ", nowDeviceStartTime=" + nowDeviceStartTime +
                ", deviceRunTime=" + deviceRunTime +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
