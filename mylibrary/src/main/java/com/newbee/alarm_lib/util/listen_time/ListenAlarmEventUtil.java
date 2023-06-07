package com.newbee.alarm_lib.util.listen_time;

import android.text.TextUtils;
import android.util.Log;
import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;
import com.newbee.alarm_lib.bean.alarm.ResultAlarmInfoBean;
import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.event.alarm.TaoziTimeAlarmEventObserver;

import com.newbee.alarm_lib.event.alarm.TaoziTimeAlarmEventSubscriptionSubject;
import com.newbee.alarm_lib.listen.ListenAlarm;
import com.newbee.alarm_lib.util.CheckAlarmUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 闹钟每次会自己遍历2个小时之内的闹钟，可以做个预警
 */
public class ListenAlarmEventUtil {
    private String tag = getClass().getName() + ">>>>";
    private TaoziTimeAlarmEventObserver alarmEventObserver=new TaoziTimeAlarmEventObserver() {
        /**
         * 添加监听
         */
        @Override
        public void addListenAlarm(ListenAlarm listenAlarm) {
            if (listenAlarm != null) {
                listenAlarmList.add(listenAlarm);
                reGetNowCanStartAlarms = true;
            }
        }

        /**
         * 删除监听
         */
        @Override
        public void removeListenAlarm(ListenAlarm listenAlarm) {
            if (listenAlarm != null) {
                listenAlarmList.remove(listenAlarm);
                reGetNowCanStartAlarms = true;
            }
        }

        /**
         * 添加闹钟事件
         */
        @Override
        public void addAlarmEvent(AlarmInfoBean alarmInfoBean) {
            if (alarmInfoBean != null) {
                resultAlarmInfoBean.add(alarmInfoBean);
                reGetNowCanStartAlarms = true;
            }
        }

        /**
         * 删除闹钟事件
         */
        @Override
        public void removeAlarmEvent(String bs) {
            if(!TextUtils.isEmpty(bs)){
                resultAlarmInfoBean.getList().remove(bs);
                reGetNowCanStartAlarms = true;
            }
        }

        @Override
        public void removeAlarmEventAll() {
            resultAlarmInfoBean.getList().clear();
            reGetNowCanStartAlarms = true;
        }
    };

    private List<ListenAlarm> listenAlarmList = new ArrayList<>();
    private ResultAlarmInfoBean resultAlarmInfoBean= new ResultAlarmInfoBean();;//系统全部的闹钟
    private ResultAlarmInfoBean nowCanStartAlarms= new ResultAlarmInfoBean();;//现在可以启动的闹钟
    private boolean reGetNowCanStartAlarms;


    public ListenAlarmEventUtil() {
        TaoziTimeAlarmEventSubscriptionSubject.getInstence().attach(alarmEventObserver);
        reGetNowCanStartAlarms = true;
    }

    public void close() {
        reGetNowCanStartAlarms = false;
        listenAlarmList.clear();
        resultAlarmInfoBean=null;
        nowCanStartAlarms=null;
        TaoziTimeAlarmEventSubscriptionSubject.getInstence().detach(alarmEventObserver);
    }








    private void getNowNeedStartAlarms(TaoziTimeBean taoziTimeBean) {
        if (!reGetNowCanStartAlarms || resultAlarmInfoBean == null || resultAlarmInfoBean.getList() == null)
            return;
        if (nowCanStartAlarms == null||nowCanStartAlarms.getList()==null) {
            nowCanStartAlarms = new ResultAlarmInfoBean();
        } else {
            nowCanStartAlarms.getList().clear();
        }
        for (AlarmInfoBean alarmInfoBean : resultAlarmInfoBean.getList().values()) {
            if (alarmInfoBean == null)
                continue;

            //如果当前的星期几是对的
            if(CheckAlarmUtil.checkWeekIsOk(alarmInfoBean, taoziTimeBean.getWeek())||
                    CheckAlarmUtil.checkYearAndMonthAndDayIsOk(alarmInfoBean, taoziTimeBean.getYear(), taoziTimeBean.getMonth(), taoziTimeBean.getDay())){
                if (CheckAlarmUtil.nowHourCanStartTime(alarmInfoBean, taoziTimeBean.getHour(), taoziTimeBean.getMinute(), taoziTimeBean.getSecond())) {
                    nowCanStartAlarms.add(alarmInfoBean);
                    continue;
                }
            }
        }
        reGetNowCanStartAlarms = false;
        Log.i(tag, "---总共的闹钟有多少个" + resultAlarmInfoBean.getList().size() + "---当前启动的闹钟有多少个" + nowCanStartAlarms.getList().size());

    }


    public void getTimeSendListen(TaoziTimeBean taoziTimeBean) {
        if (null==taoziTimeBean||listenAlarmList.size()==0) {
            return;
        }
        if(!reGetNowCanStartAlarms){
            reGetNowCanStartAlarms=nowTimeCanResetAlarms(taoziTimeBean);
        }
        if(reGetNowCanStartAlarms){
            getNowNeedStartAlarms(taoziTimeBean);
        }
        if(null==nowCanStartAlarms||null==nowCanStartAlarms.getList()||nowCanStartAlarms.getList().size()==0){
            return;
        }

        for (AlarmInfoBean nowAlarm:nowCanStartAlarms.getList().values()){
            if (null==nowAlarm){
                continue;
            }

            if (CheckAlarmUtil.checkNowHourIsOk(nowAlarm, taoziTimeBean.getHour(), taoziTimeBean.getMinute(), taoziTimeBean.getSecond())) {
                sendListenMsg(taoziTimeBean, nowAlarm);
                reGetNowCanStartAlarms=true;
                continue;
            }
        }
    }

    private boolean nowTimeCanResetAlarms(TaoziTimeBean taoziTimeBean){
        return taoziTimeBean.getSecond() == 0&&taoziTimeBean.getMinute() % CheckAlarmUtil.ALARM_START_MINUTE_CHA== 0;
    }

    private void sendListenMsg(TaoziTimeBean taoziTimeBean, AlarmInfoBean alarmInfoBean) {
        for (ListenAlarm listenAlarm : listenAlarmList) {
            listenAlarm.listenAlarm(taoziTimeBean, alarmInfoBean);
        }
    }
}
