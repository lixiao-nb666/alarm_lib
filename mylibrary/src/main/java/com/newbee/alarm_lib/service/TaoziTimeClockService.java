package com.newbee.alarm_lib.service;


import android.content.Intent;

import com.newbee.alarm_lib.bean.NeedTimeMustDoBean;
import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.listen.ListenNeedTimeMustDo;
import com.newbee.alarm_lib.thread.TimeClockThread;
import com.newbee.alarm_lib.thread.TimeClockThreadImp;
import com.newbee.alarm_lib.util.listen_time.ListenAlarmEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenBetweenTimeEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenNeedTimeMustDoEventUtil;
import com.newbee.alarm_lib.util.listen_time.ListenTimeClockEventUtil;
import com.newbee.bulid_lib.mybase.service.BaseService;


public class TaoziTimeClockService extends BaseService {


    private TimeClockThread timeClockThread;
    private TimeClockThreadImp timeClockThreadImp = new TimeClockThreadImp() {
        @Override
        public void getSystemTime(TaoziTimeBean taoziTimeBean) {
            listenTimeClockEventUtil.getTimeSendListen(taoziTimeBean);
            listenAlarmEventUtil.getTimeSendListen(taoziTimeBean);
            listenBetweenTimeEventUtil.getTimeSendListen(taoziTimeBean);
            listenNeedTimeMustDoEventUtil.getTimeSendListen(taoziTimeBean);
        }
    };
    private ListenTimeClockEventUtil listenTimeClockEventUtil=new ListenTimeClockEventUtil();
    private ListenAlarmEventUtil listenAlarmEventUtil=new ListenAlarmEventUtil();
    private ListenBetweenTimeEventUtil listenBetweenTimeEventUtil=new ListenBetweenTimeEventUtil();
    private ListenNeedTimeMustDoEventUtil listenNeedTimeMustDoEventUtil=new ListenNeedTimeMustDoEventUtil();
    private TaoziTimeClockEventObserver taoziTimeClockEventObserver = new TaoziTimeClockEventObserver() {


        @Override
        public void addListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo, String key) {
            listenNeedTimeMustDoEventUtil.addListen(listenNeedTimeMustDo, key);
        }

        @Override
        public void removeListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo) {
            listenNeedTimeMustDoEventUtil.removeListen(listenNeedTimeMustDo);
        }

        @Override
        public void addNeedTimeMustDoEvent(NeedTimeMustDoBean needTimeMustDoBean) {
            listenNeedTimeMustDoEventUtil.addNeedTimeMustDoEvent(needTimeMustDoBean);
        }



        @Override
        public void removeNeedTimeMustDoEvent(String bs) {
            listenNeedTimeMustDoEventUtil.removeNeedTimeMustDoEvent(bs);
        }

        @Override
        public void removeNeedTimeMustDoAllEvent() {
            listenNeedTimeMustDoEventUtil.removeNeedTimeMustDoAllEvent();
        }
    };

    @Override
    public void init() {
        timeClockThread = new TimeClockThread(timeClockThreadImp);
        timeClockThread.start();
        TaoziTimeClockEventSubscriptionSubject.getInstence().attach(taoziTimeClockEventObserver);
    }

    @Override
    public void start(Intent intent, int flags, int startId) {

    }

    @Override
    public void close() {
        if (timeClockThread != null) {
            timeClockThread.close();
        }

        listenAlarmEventUtil.close();
        listenBetweenTimeEventUtil.close();
        listenNeedTimeMustDoEventUtil.close();
        TaoziTimeClockEventSubscriptionSubject.getInstence().detach(taoziTimeClockEventObserver);
    }









}
