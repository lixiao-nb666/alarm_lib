package com.newbee.alarm_lib.util.listen_time;


import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.event.time.TaoziTimeBeanListenSubscriptionSubject;

public class ListenTimeClockEventUtil {


    public void getTimeSendListen(TaoziTimeBean taoziTimeBean) {
        if(TaoziTimeBeanListenSubscriptionSubject.getInstence().observerNumb()==0){
            return;
        }

        TaoziTimeBeanListenSubscriptionSubject.getInstence().listenTime(taoziTimeBean);
    }
}
