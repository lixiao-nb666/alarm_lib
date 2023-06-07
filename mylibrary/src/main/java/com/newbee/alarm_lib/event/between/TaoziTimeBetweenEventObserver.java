package com.newbee.alarm_lib.event.between;


import com.newbee.alarm_lib.listen.ListenBetweenTime;

/**
 * Created by xiefuning on 2017/5/12.
 * about:
 */

public interface TaoziTimeBetweenEventObserver {


    /**
     * 添加时间间隔监听
     *
     * @param listenBetweenTime
     */
    public void addListenBetweenTime(ListenBetweenTime listenBetweenTime, long waitTime);

    /**
     * 删除时间间隔监听
     *
     * @param listenBetweenTime
     */
    public void removelistenBetweenTime(ListenBetweenTime listenBetweenTime);


}
