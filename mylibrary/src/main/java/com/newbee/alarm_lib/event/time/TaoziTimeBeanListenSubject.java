package com.newbee.alarm_lib.event.time;


import com.newbee.alarm_lib.bean.TaoziTimeBean;

public interface TaoziTimeBeanListenSubject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    public void attach(TaoziTimeBeanListenObserver observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    public void detach(TaoziTimeBeanListenObserver observer);

    public int observerNumb();
    /**
     * 添加时间改变事件
     *
     * @param taoziTimeBean
     */
    public void listenTime(TaoziTimeBean taoziTimeBean);


}
