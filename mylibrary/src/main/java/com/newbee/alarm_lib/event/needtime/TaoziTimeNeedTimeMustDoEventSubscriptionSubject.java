package com.newbee.alarm_lib.event.needtime;




import com.newbee.alarm_lib.bean.NeedTimeMustDoBean;
import com.newbee.alarm_lib.listen.ListenBetweenTime;
import com.newbee.alarm_lib.listen.ListenNeedTimeMustDo;

import java.util.ArrayList;
import java.util.List;

public class TaoziTimeNeedTimeMustDoEventSubscriptionSubject implements TaoziTimeNeedTimeMustDoEventSubject {

    private List<TaoziTimeNeedTimeMustDoEventObserver> observers;
    private static TaoziTimeNeedTimeMustDoEventSubscriptionSubject subscriptionSubject;

    private TaoziTimeNeedTimeMustDoEventSubscriptionSubject() {
        observers = new ArrayList<>();
    }

    public static TaoziTimeNeedTimeMustDoEventSubscriptionSubject getInstence() {
        if (subscriptionSubject == null) {
            synchronized (TaoziTimeNeedTimeMustDoEventSubscriptionSubject.class) {
                if (subscriptionSubject == null)
                    subscriptionSubject = new TaoziTimeNeedTimeMustDoEventSubscriptionSubject();
            }
        }
        return subscriptionSubject;

    }



    @Override
    public void attach(TaoziTimeNeedTimeMustDoEventObserver observer) {

        observers.add(observer);

    }

    @Override
    public void detach(TaoziTimeNeedTimeMustDoEventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void addListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo, String key) {
        for (TaoziTimeNeedTimeMustDoEventObserver observer:observers){
            observer.addListenNeedTimeMustDo(listenNeedTimeMustDo,key);
        }
    }

    @Override
    public void removeListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo) {
        for (TaoziTimeNeedTimeMustDoEventObserver observer:observers){
            observer.removeListenNeedTimeMustDo(listenNeedTimeMustDo);
        }
    }

    @Override
    public void addNeedTimeMustDoEvent(NeedTimeMustDoBean needTimeMustDoBean) {
        for (TaoziTimeNeedTimeMustDoEventObserver observer:observers){
            observer.addNeedTimeMustDoEvent(needTimeMustDoBean);
        }
    }

    @Override
    public void removeNeedTimeMustDoEvent(String bs) {
        for (TaoziTimeNeedTimeMustDoEventObserver observer:observers){
            observer.removeNeedTimeMustDoEvent(bs);
        }
    }

    @Override
    public void removeNeedTimeMustDoAllEvent() {
        for (TaoziTimeNeedTimeMustDoEventObserver observer:observers){
            observer.removeNeedTimeMustDoAllEvent();
        }
    }


}
