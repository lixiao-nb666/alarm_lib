package com.newbee.alarm_lib.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultNeedTimeMustDoBean implements Serializable {
    private Map<String,NeedTimeMustDoBean> list;

    public ResultNeedTimeMustDoBean() {
        this.list=new HashMap<>();
    }

    public Map<String, NeedTimeMustDoBean> getList() {
        return list;
    }

    public void setList(Map<String, NeedTimeMustDoBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ResultNeedTimeMustDoBean{" +
                "list=" + list +
                '}';
    }

    public void add(NeedTimeMustDoBean needTimeMustDoBean){
        list.put(needTimeMustDoBean.getBs(),needTimeMustDoBean);
    }

    public static boolean checkCanUse(NeedTimeMustDoBean needTimeMustDoBean){
        if(null==needTimeMustDoBean|| TextUtils.isEmpty(needTimeMustDoBean.getBs())||
                TextUtils.isEmpty(needTimeMustDoBean.getDoThingKey())||
                0==needTimeMustDoBean.getStartTime()||0==needTimeMustDoBean.getEndTime()||
        needTimeMustDoBean.getEndTime()< needTimeMustDoBean.getStartTime()){
            return false;
        }
        return true;
    }
}
