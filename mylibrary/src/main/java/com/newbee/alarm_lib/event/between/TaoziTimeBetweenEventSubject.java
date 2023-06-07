package com.newbee.alarm_lib.event.between;



import com.newbee.alarm_lib.listen.ListenBetweenTime;

public interface TaoziTimeBetweenEventSubject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    public void attach(TaoziTimeBetweenEventObserver observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    public void detach(TaoziTimeBetweenEventObserver observer);


    /**
     * 添加时间间隔监听
     *
     * @param listenBetweenTime
     */
    public void addListenBetweenTime(ListenBetweenTime listenBetweenTime, long waitTime);

    /**
     * 删除时间间隔监听
     *
     * @param listenBetweenTime
     */
    public void removelistenBetweenTime(ListenBetweenTime listenBetweenTime);


}
