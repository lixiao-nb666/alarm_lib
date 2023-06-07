package com.newbee.alarm_lib.manager;

import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.util.listen_time.ListenAlarmEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenBetweenTimeEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenNeedTimeMustDoEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenTimeClockEventUtil;

public class TaoziTimeClockManager {


    private static TaoziTimeClockManager manager;
    private ListenTimeClockEventUtil listenTimeClockEventUtil=new ListenTimeClockEventUtil();
    private ListenAlarmEventUtil listenAlarmEventUtil=new ListenAlarmEventUtil();
    private ListenBetweenTimeEventUtil listenBetweenTimeEventUtil=new ListenBetweenTimeEventUtil();
    private ListenNeedTimeMustDoEventUtil listenNeedTimeMustDoEventUtil=new ListenNeedTimeMustDoEventUtil();

    private TaoziTimeClockManager(){}

    public static TaoziTimeClockManager getInstance(){
        if(null==manager){
            synchronized (TaoziTimeClockManager.class){
                if(null==manager){
                    manager=new TaoziTimeClockManager();
                }
            }
        }
        return manager;
    }

    public void getSystemTime(TaoziTimeBean taoziTimeBean){
        listenTimeClockEventUtil.getTimeSendListen(taoziTimeBean);
        listenAlarmEventUtil.getTimeSendListen(taoziTimeBean);
        listenBetweenTimeEventUtil.getTimeSendListen(taoziTimeBean);
        listenNeedTimeMustDoEventUtil.getTimeSendListen(taoziTimeBean);
    }


    public void close(){
        listenAlarmEventUtil.close();
        listenBetweenTimeEventUtil.close();
        listenNeedTimeMustDoEventUtil.close();
        manager=null;
    }


}
