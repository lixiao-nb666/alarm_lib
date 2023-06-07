package com.newbee.alarm_lib.event.needtime;



import com.newbee.alarm_lib.bean.NeedTimeMustDoBean;
import com.newbee.alarm_lib.listen.ListenBetweenTime;
import com.newbee.alarm_lib.listen.ListenNeedTimeMustDo;

public interface TaoziTimeNeedTimeMustDoEventSubject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    public void attach(TaoziTimeNeedTimeMustDoEventObserver observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    public void detach(TaoziTimeNeedTimeMustDoEventObserver observer);

    /**
     * 添加在规定的时间必须开始做的事情的监听器
     *
     * @param listenNeedTimeMustDo
     */
    public void addListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo, String key);

    /**
     * 删除在规定的时间必须开始做的事情的监听器
     *
     * @param listenNeedTimeMustDo
     */
    public void removeListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo);

    /**
     * 添加对应的强制执行
     * @param needTimeMustDoBean
     */
    public void addNeedTimeMustDoEvent(NeedTimeMustDoBean needTimeMustDoBean);



    /**
     * 删除对应的强制执行
     * @param bs
     */
    public void removeNeedTimeMustDoEvent(String bs);

    /**
     * 删除所有的强制执行
     */
    public void removeNeedTimeMustDoAllEvent();


}
