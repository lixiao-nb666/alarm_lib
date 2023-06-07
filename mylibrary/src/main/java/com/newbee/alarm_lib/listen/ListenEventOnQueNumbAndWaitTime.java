package com.newbee.alarm_lib.listen;

/**
 * Created by Administrator on 2018/7/15 0015.
 * 监听某个事件，必须多少秒之内，多少次，这个事件，触发一次，这个时间会重新去计算
 */

public interface ListenEventOnQueNumbAndWaitTime {

    public void listenEventOnQueNumbAndWaitTime();
}
