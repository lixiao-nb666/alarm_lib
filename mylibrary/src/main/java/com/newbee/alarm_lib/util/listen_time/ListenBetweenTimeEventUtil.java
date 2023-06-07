package com.newbee.alarm_lib.util.listen_time;


import com.newbee.alarm_lib.bean.BetweenTimeCountBean;
import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.event.between.TaoziTimeBetweenEventObserver;
import com.newbee.alarm_lib.event.between.TaoziTimeBetweenEventSubscriptionSubject;
import com.newbee.alarm_lib.listen.ListenBetweenTime;

import java.util.HashMap;
import java.util.Map;

public class ListenBetweenTimeEventUtil {

    private TaoziTimeBetweenEventObserver betweenEventObserver=new TaoziTimeBetweenEventObserver() {
        @Override
        public void addListenBetweenTime(ListenBetweenTime listenBetweenTime, long betweenTime) {
            if (listenBetweenTime != null && betweenTime >0) {
                BetweenTimeCountBean betweenTimeCountBean=map.get(listenBetweenTime);
                if(null==betweenTimeCountBean){
                    map.put(listenBetweenTime, new BetweenTimeCountBean(betweenTime, System.currentTimeMillis()));
                }else {
                    betweenTimeCountBean.setBetweenTime(betweenTime);
                    betweenTimeCountBean.setLastTime(System.currentTimeMillis());
                }


            }
        }

        @Override
        public void removelistenBetweenTime(ListenBetweenTime listenBetweenTime) {
            if (listenBetweenTime != null) {
                map.remove(listenBetweenTime);
            }
        }
    };

    private Map<ListenBetweenTime, BetweenTimeCountBean> map = new HashMap<>();

    public ListenBetweenTimeEventUtil() {
        TaoziTimeBetweenEventSubscriptionSubject.getInstence().attach(betweenEventObserver);
    }

    public void close() {
        map.clear();
        TaoziTimeBetweenEventSubscriptionSubject.getInstence().detach(betweenEventObserver);
    }





    public void getTimeSendListen(TaoziTimeBean taoziTimeBean) {
        if(taoziTimeBean==null||map.size()==0){
            return;
        }
        for (ListenBetweenTime listenBetweenTime : map.keySet()) {
            BetweenTimeCountBean betweenTimeCountBean = map.get(listenBetweenTime);
            if (betweenTimeCountBean != null && (taoziTimeBean.getTime() >= betweenTimeCountBean.getLastTime() + betweenTimeCountBean.getBetweenTime())) {
                //发送监听
                listenBetweenTime.listenBetween(betweenTimeCountBean.getLastTime(), taoziTimeBean.getTime());
                //改变监听器里面的参数
                betweenTimeCountBean.setLastTime(taoziTimeBean.getTime());
                map.put(listenBetweenTime, betweenTimeCountBean);
            }
        }
    }


}
