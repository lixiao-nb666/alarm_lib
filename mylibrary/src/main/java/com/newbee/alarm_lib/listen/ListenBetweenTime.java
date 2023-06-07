package com.newbee.alarm_lib.listen;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public interface ListenBetweenTime {
    /**
     *
     * @param lastTime 上次的时间
     * @param nowTime 这次的时间
     */
    public void listenBetween(long lastTime, long nowTime);
}
