package com.newbee.alarm_lib.event.time;



import com.newbee.alarm_lib.bean.TaoziTimeBean;

import java.util.ArrayList;
import java.util.List;

public class TaoziTimeBeanListenSubscriptionSubject implements TaoziTimeBeanListenSubject {

    private List<TaoziTimeBeanListenObserver> observers;
    private static TaoziTimeBeanListenSubscriptionSubject subscriptionSubject;

    private TaoziTimeBeanListenSubscriptionSubject() {
        observers = new ArrayList<>();
    }

    public static TaoziTimeBeanListenSubscriptionSubject getInstence() {
        if (subscriptionSubject == null) {
            synchronized (TaoziTimeBeanListenSubscriptionSubject.class) {
                if (subscriptionSubject == null)
                    subscriptionSubject = new TaoziTimeBeanListenSubscriptionSubject();
            }
        }
        return subscriptionSubject;

    }

    @Override
    public void attach(TaoziTimeBeanListenObserver observer) {

        observers.add(observer);

    }

    @Override
    public void detach(TaoziTimeBeanListenObserver observer) {
        observers.remove(observer);
    }

    @Override
    public int observerNumb() {
        return observers.size();
    }

    @Override
    public void listenTime(TaoziTimeBean taoziTimeBean) {
        for (TaoziTimeBeanListenObserver observer : observers) {
            observer.listenTime(taoziTimeBean);
        }
    }







}
