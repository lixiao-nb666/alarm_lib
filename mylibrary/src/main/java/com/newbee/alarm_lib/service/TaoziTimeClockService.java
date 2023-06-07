package com.newbee.alarm_lib.service;


import android.content.Intent;
import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.manager.TaoziTimeClockManager;
import com.newbee.alarm_lib.thread.TimeClockThread;
import com.newbee.alarm_lib.thread.TimeClockThreadImp;
import com.newbee.bulid_lib.mybase.service.BaseService;


public class TaoziTimeClockService extends BaseService {

    private TimeClockThread timeClockThread;
    private TimeClockThreadImp timeClockThreadImp = new TimeClockThreadImp() {
        @Override
        public void getSystemTime(TaoziTimeBean taoziTimeBean) {
            TaoziTimeClockManager.getInstance().getSystemTime(taoziTimeBean);
        }
    };

    @Override
    public void init() {
        timeClockThread = new TimeClockThread(timeClockThreadImp);
        timeClockThread.start();
    }

    @Override
    public void start(Intent intent, int flags, int startId) {
    }

    @Override
    public void close() {
        if (timeClockThread != null) {
            timeClockThread.close();
        }
        TaoziTimeClockManager.getInstance().close();
    }

}
