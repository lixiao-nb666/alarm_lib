package com.newbee.alarm_lib.bean.alarm;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultAlarmInfoBean implements Serializable {
    private Map<String,AlarmInfoBean> list;

    public ResultAlarmInfoBean() {
        this.list = new HashMap<>();
    }

    public Map<String,AlarmInfoBean> getList() {
        return list;
    }



    @Override
    public String toString() {
        return "ResultAlarmInfoBean{" +
                "list=" + list +
                '}';
    }

    public boolean add(AlarmInfoBean alarmInfoBean) {
        if(null==alarmInfoBean|| TextUtils.isEmpty(alarmInfoBean.getBs())){
            return false;
        }
        list.put(alarmInfoBean.getBs(),alarmInfoBean);
        return true;
    }

    public void remove(AlarmInfoBean alarmInfoBean){
        list.remove(alarmInfoBean);
    }
}
