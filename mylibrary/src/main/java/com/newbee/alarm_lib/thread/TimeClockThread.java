package com.newbee.alarm_lib.thread;


import com.newbee.alarm_lib.bean.TaoziTimeBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class TimeClockThread extends Thread {
    private boolean isStart;
    private TimeClockThreadImp timeClockThreadImp;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
    public TaoziTimeBean taoziTimeBean = new TaoziTimeBean();
    
    
    public TimeClockThread(TimeClockThreadImp timeClockThreadImp) {
        isStart = true;
        this.timeClockThreadImp = timeClockThreadImp;
    }

    public void close() {
        isStart = false;
    }
    public boolean isStart() {
        return isStart;
    }

    @Override
    public void run() {
        super.run();
        Calendar cal = Calendar.getInstance();
        int w;
        long nowTime;
        String timeStr;
        String[] strs;
        int last_m=-1;//上次的分钟为
        while (isStart) {
            try {
                if (timeClockThreadImp != null) {
                    Date date = new Date();
                    cal.setTime(date);
                    w = cal.get(Calendar.DAY_OF_WEEK) - 1;
                    if (w < 0)
                        w = 0;
                    taoziTimeBean.setWeek(w);
                    nowTime = date.getTime();
                    timeStr = sdf.format(date);
                    strs = timeStr.split(":");
                    taoziTimeBean.setYear(Integer.valueOf(strs[0]));
                    taoziTimeBean.setMonth(Integer.valueOf(strs[1]));
                    taoziTimeBean.setDay(Integer.valueOf(strs[2]));
                    taoziTimeBean.setHour(Integer.valueOf(strs[3]));
                    int now_m=Integer.valueOf(strs[4]);
                    taoziTimeBean.setMinute(now_m);
                    if(last_m!=now_m){
                        last_m=now_m;
//                        SystemTimeUtil.getInstance().writeUseTimeData();
                    }
                    taoziTimeBean.setSecond(Integer.valueOf(strs[5]));
                    taoziTimeBean.setTime(nowTime);
                    timeClockThreadImp.getSystemTime(taoziTimeBean);
                }
                new Thread().sleep(1 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
