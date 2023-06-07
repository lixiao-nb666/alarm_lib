package com.newbee.alarm_lib.listen;


import com.newbee.alarm_lib.bean.TaoziTimeBean;

public interface ListenNeedTimeMustDo {

    /**
     * @param taoziTimeBean   当前的时间对象
     * @param doObj           //要做的事情
     */
    public void listenNeedTimeMustDoStart(TaoziTimeBean taoziTimeBean,String doObj);


    /**
     * @param taoziTimeBean   当前的时间对象
     * @param doObj           //要做的事情
     */
    public void listenNeedTimeMustDoOver(TaoziTimeBean taoziTimeBean, String doObj);
}
