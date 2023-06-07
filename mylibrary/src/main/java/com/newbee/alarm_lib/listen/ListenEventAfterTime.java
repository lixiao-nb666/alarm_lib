package com.newbee.alarm_lib.listen;

/**
 * Created by Administrator on 2018/7/15 0015.
 */

public interface ListenEventAfterTime {
    /**
     *
     * @param tag  某个事件的标识
     * @param eventTime  事件触发的时间
     * @param nowTime  当前的时间
     */
    public void listenEventAfterTime(String tag, long eventTime, long nowTime);
}
