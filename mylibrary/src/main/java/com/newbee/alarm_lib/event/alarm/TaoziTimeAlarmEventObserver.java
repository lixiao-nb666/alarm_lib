package com.newbee.alarm_lib.event.alarm;


import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;
import com.newbee.alarm_lib.listen.ListenAlarm;

/**
 * Created by xiefuning on 2017/5/12.
 * about:
 */

public interface TaoziTimeAlarmEventObserver {


    /**
     * 添加闹钟监听
     *
     * @param listenAlarm
     */
    public void addListenAlarm(ListenAlarm listenAlarm);

    /**
     * 删除闹钟监听
     *
     * @param listenAlarm
     */
    public void removeListenAlarm(ListenAlarm listenAlarm);
    /**
     * 添加闹钟事件
     */
    public void addAlarmEvent(AlarmInfoBean alarmInfoBean);

    /**
     * 删除闹钟事件根据BS
     */
    public void removeAlarmEvent(String bs);

    /**
     * 删除全部闹钟
     */
    public void removeAlarmEventAll();


}
