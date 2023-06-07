package com.newbee.alarm_lib.listen;


import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;

/**
 * Created by Administrator on 2018/7/15 0015.
 */

public interface ListenAlarm {


    public void listenAlarm(TaoziTimeBean taoziTimeBean, AlarmInfoBean alarmInfoBean);
}
