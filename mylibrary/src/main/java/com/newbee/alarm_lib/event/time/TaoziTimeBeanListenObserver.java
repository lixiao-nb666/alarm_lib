package com.newbee.alarm_lib.event.time;


import com.newbee.alarm_lib.bean.TaoziTimeBean;

/**
 * Created by xiefuning on 2017/5/12.
 * about:
 */

public interface TaoziTimeBeanListenObserver {
    /**
     * 添加时间改变事件
     *
     * @param taoziTimeBean
     */
    public void listenTime(TaoziTimeBean taoziTimeBean);


}
