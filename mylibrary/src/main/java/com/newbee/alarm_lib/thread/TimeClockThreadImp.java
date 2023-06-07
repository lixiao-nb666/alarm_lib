package com.newbee.alarm_lib.thread;


import com.newbee.alarm_lib.bean.TaoziTimeBean;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public interface TimeClockThreadImp {

    //获得系统当前的时间
     void getSystemTime(TaoziTimeBean taoziTimeBean);
}
