package com.newbee.alarm_lib.event.alarm;



import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;
import com.newbee.alarm_lib.listen.ListenAlarm;

import java.util.ArrayList;
import java.util.List;

public class TaoziTimeAlarmEventSubscriptionSubject implements TaoziTimeAlarmEventSubject {

    private List<TaoziTimeAlarmEventObserver> observers;
    private static TaoziTimeAlarmEventSubscriptionSubject subscriptionSubject;

    private TaoziTimeAlarmEventSubscriptionSubject() {
        observers = new ArrayList<>();
    }

    public static TaoziTimeAlarmEventSubscriptionSubject getInstence() {
        if (subscriptionSubject == null) {
            synchronized (TaoziTimeAlarmEventSubscriptionSubject.class) {
                if (subscriptionSubject == null)
                    subscriptionSubject = new TaoziTimeAlarmEventSubscriptionSubject();
            }
        }
        return subscriptionSubject;

    }



    @Override
    public void attach(TaoziTimeAlarmEventObserver observer) {

        observers.add(observer);

    }

    @Override
    public void detach(TaoziTimeAlarmEventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void addListenAlarm(ListenAlarm listenAlarm) {
        for (TaoziTimeAlarmEventObserver observer : observers) {
            observer.addListenAlarm(listenAlarm);
        }
    }

    @Override
    public void removeListenAlarm(ListenAlarm listenAlarm) {
        for (TaoziTimeAlarmEventObserver observer : observers) {
            observer.removeListenAlarm(listenAlarm);
        }
    }


    @Override
    public void addAlarmEvent(AlarmInfoBean alarmInfoBean) {
        for (TaoziTimeAlarmEventObserver observer : observers) {
            observer.addAlarmEvent(alarmInfoBean);
        }
    }

    @Override
    public void removeAlarmEvent(String bs) {
        for (TaoziTimeAlarmEventObserver observer : observers) {
            observer.removeAlarmEvent(bs);
        }
    }

    @Override
    public void removeAlarmEventAll() {
        for (TaoziTimeAlarmEventObserver observer : observers) {
            observer.removeAlarmEventAll();
        }
    }









}
