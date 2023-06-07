package com.newbee.alarm_lib.event.between;




import com.newbee.alarm_lib.listen.ListenBetweenTime;

import java.util.ArrayList;
import java.util.List;

public class TaoziTimeBetweenEventSubscriptionSubject implements TaoziTimeBetweenEventSubject {

    private List<TaoziTimeBetweenEventObserver> observers;
    private static TaoziTimeBetweenEventSubscriptionSubject subscriptionSubject;

    private TaoziTimeBetweenEventSubscriptionSubject() {
        observers = new ArrayList<>();
    }

    public static TaoziTimeBetweenEventSubscriptionSubject getInstence() {
        if (subscriptionSubject == null) {
            synchronized (TaoziTimeBetweenEventSubscriptionSubject.class) {
                if (subscriptionSubject == null)
                    subscriptionSubject = new TaoziTimeBetweenEventSubscriptionSubject();
            }
        }
        return subscriptionSubject;

    }



    @Override
    public void attach(TaoziTimeBetweenEventObserver observer) {

        observers.add(observer);

    }

    @Override
    public void detach(TaoziTimeBetweenEventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void addListenBetweenTime(ListenBetweenTime listenBetweenTime, long waitTime) {
        for (TaoziTimeBetweenEventObserver observer : observers) {
            observer.addListenBetweenTime(listenBetweenTime,waitTime);
        }
    }

    @Override
    public void removelistenBetweenTime(ListenBetweenTime listenBetweenTime) {
        for (TaoziTimeBetweenEventObserver observer : observers) {
            observer.removelistenBetweenTime(listenBetweenTime);
        }
    }













}
