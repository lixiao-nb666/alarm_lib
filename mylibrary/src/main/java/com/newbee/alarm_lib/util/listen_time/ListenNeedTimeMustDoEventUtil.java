package com.newbee.alarm_lib.util.listen_time;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;


import com.newbee.alarm_lib.bean.NeedTimeMustDoBean;
import com.newbee.alarm_lib.bean.ResultNeedTimeMustDoBean;
import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.event.needtime.TaoziTimeNeedTimeMustDoEventObserver;
import com.newbee.alarm_lib.event.needtime.TaoziTimeNeedTimeMustDoEventSubscriptionSubject;
import com.newbee.alarm_lib.listen.ListenNeedTimeMustDo;
import com.newbee.alarm_lib.util.CheckNeedTimeMustDoUtil;

import java.util.Map;

public class ListenNeedTimeMustDoEventUtil {
    private TaoziTimeNeedTimeMustDoEventObserver taoziTimeNeedTimeMustDoEventObserver=new TaoziTimeNeedTimeMustDoEventObserver() {
        @Override
        public void addListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo, String eventKey) {
            if (listenNeedTimeMustDo != null && !TextUtils.isEmpty(eventKey)) {
                listenNeedTimeMustDoStringMap.put(listenNeedTimeMustDo, eventKey);
                reGetNowNeedTimeMustDo = true;
            }
        }

        @Override
        public void removeListenNeedTimeMustDo(ListenNeedTimeMustDo listenNeedTimeMustDo) {
            if (listenNeedTimeMustDo != null) {
                listenNeedTimeMustDoStringMap.remove(listenNeedTimeMustDo);
                reGetNowNeedTimeMustDo = true;
            }
        }

        @Override
        public void addNeedTimeMustDoEvent(NeedTimeMustDoBean needTimeMustDoBean) {
            if(ResultNeedTimeMustDoBean.checkCanUse(needTimeMustDoBean)){
                allNeedTimeMustDo.add(needTimeMustDoBean);
                reGetNowNeedTimeMustDo = true;
            }
        }

        @Override
        public void removeNeedTimeMustDoEvent(String bs) {
            if (!TextUtils.isEmpty(bs)) {
                NeedTimeMustDoBean needTimeMustDoBean=allNeedTimeMustDo.getList().get(bs);
                if(null!=needTimeMustDoBean){
                    allNeedTimeMustDo.getList().remove(bs);
                    starMapOver(needTimeMustDoBean.getDoThingKey());
                    reGetNowNeedTimeMustDo = true;
                }
            }
        }

        @Override
        public void removeNeedTimeMustDoAllEvent() {
            allNeedTimeMustDo.getList().clear();
            reGetNowNeedTimeMustDo = true;
            starMapAllOver();
        }
    };


    private String tag = getClass().getName() + ">>>>";
    private Map<ListenNeedTimeMustDo, String> listenNeedTimeMustDoStringMap = new ArrayMap<>();
    private ResultNeedTimeMustDoBean allNeedTimeMustDo = new ResultNeedTimeMustDoBean();
    private ResultNeedTimeMustDoBean nowNeedTimeMustDo = new ResultNeedTimeMustDoBean();
    private boolean reGetNowNeedTimeMustDo;

    public ListenNeedTimeMustDoEventUtil() {
        reGetNowNeedTimeMustDo = true;
        TaoziTimeNeedTimeMustDoEventSubscriptionSubject.getInstence().attach(taoziTimeNeedTimeMustDoEventObserver);
    }

    public void close() {
        listenNeedTimeMustDoStringMap.clear();
        allNeedTimeMustDo.getList().clear();
        nowNeedTimeMustDo.getList().clear();
        TaoziTimeNeedTimeMustDoEventSubscriptionSubject.getInstence().detach(taoziTimeNeedTimeMustDoEventObserver);
    }

    private Map<String, NeedTimeMustDoBean> startMap = new ArrayMap<>();
    private Map<String, NeedTimeMustDoBean> endMap = new ArrayMap<>();

    public void getTimeSendListen(TaoziTimeBean taoziTimeBean) {
        if (taoziTimeBean == null || listenNeedTimeMustDoStringMap.size() == 0) {
            return;
        }
        if (taoziTimeBean.getMinute() % 30 == 0 && taoziTimeBean.getSecond() == 0) {
            reGetNowNeedTimeMustDo = true;
        }
        if (reGetNowNeedTimeMustDo) {
            getNowNeedTimeMustDo(taoziTimeBean);
        }
        startMap.clear();
        endMap.clear();
        if (nowNeedTimeMustDo != null && nowNeedTimeMustDo.getList() != null && nowNeedTimeMustDo.getList().size() != 0) {
            for (int i = 0; i < nowNeedTimeMustDo.getList().size(); i++) {
                NeedTimeMustDoBean needTimeMustDoBean = nowNeedTimeMustDo.getList().get(i);
                if (needTimeMustDoBean == null || TextUtils.isEmpty(needTimeMustDoBean.getDoThingKey()) || TextUtils.isEmpty(needTimeMustDoBean.getDoThingVue())) {
                    continue;
                }
                int statu = CheckNeedTimeMustDoUtil.checkNeedTimeMustDo(needTimeMustDoBean, taoziTimeBean.getHour(), taoziTimeBean.getMinute(), taoziTimeBean.getSecond());
                switch (statu){
                    case 0:
                        continue;
                    case 1:
                        NeedTimeMustDoBean checkExistNeedTimeMustDoBean = startMap.get(needTimeMustDoBean.getDoThingKey());
                        if (checkExistNeedTimeMustDoBean != null) {
                            if (needTimeMustDoBean.getLever() <= checkExistNeedTimeMustDoBean.getLever()) {
                                startMap.put(needTimeMustDoBean.getDoThingKey(), needTimeMustDoBean);
                            }
                        } else {
                            startMap.put(needTimeMustDoBean.getDoThingKey(), needTimeMustDoBean);
                        }
                        continue;
                    case 2:
                        endMap.put(needTimeMustDoBean.getDoThingKey(), needTimeMustDoBean);
                        continue;
                }
            }
        }
        for (String key : startMap.keySet()) {
            NeedTimeMustDoBean startNeedTimeMustDo = startMap.get(key);
            if (startNeedTimeMustDo == null || TextUtils.isEmpty(startNeedTimeMustDo.getDoThingVue())) {
                continue;
            }
            for (ListenNeedTimeMustDo listenNeedTimeMustDo : listenNeedTimeMustDoStringMap.keySet()) {
                String listenStr = listenNeedTimeMustDoStringMap.get(listenNeedTimeMustDo);
                if (key.equals(listenStr)) {
                    listenNeedTimeMustDo.listenNeedTimeMustDoStart(taoziTimeBean, startNeedTimeMustDo.getDoThingVue());
                }
            }
        }
        for (String key : endMap.keySet()) {
            NeedTimeMustDoBean endNeedTimeMustDo = endMap.get(key);
            if (endNeedTimeMustDo == null || TextUtils.isEmpty(endNeedTimeMustDo.getDoThingVue())) {
                continue;
            }
            for (ListenNeedTimeMustDo listenNeedTimeMustDo : listenNeedTimeMustDoStringMap.keySet()) {
                String listenStr = listenNeedTimeMustDoStringMap.get(listenNeedTimeMustDo);
                if (key.equals(listenStr)) {
                    listenNeedTimeMustDo.listenNeedTimeMustDoOver(taoziTimeBean, endNeedTimeMustDo.getDoThingVue());
                }
            }
        }
    }

    private void starMapAllOver() {
        for (String key : startMap.keySet()) {
            NeedTimeMustDoBean startNeedTimeMustDo = startMap.get(key);
            if (startNeedTimeMustDo == null || TextUtils.isEmpty(startNeedTimeMustDo.getDoThingVue())) {
                continue;
            }
            for (ListenNeedTimeMustDo listenNeedTimeMustDo : listenNeedTimeMustDoStringMap.keySet()) {
                String listenStr = listenNeedTimeMustDoStringMap.get(listenNeedTimeMustDo);
                if (key.equals(listenStr)) {
                    listenNeedTimeMustDo.listenNeedTimeMustDoOver(null, startNeedTimeMustDo.getDoThingVue());
                }
            }
        }
    }

    private void starMapOver(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        NeedTimeMustDoBean endNeedTimeMustDo = startMap.get(key);
        if (endNeedTimeMustDo == null || TextUtils.isEmpty(endNeedTimeMustDo.getDoThingVue())) {
            return;
        }
        for (ListenNeedTimeMustDo listenNeedTimeMustDo : listenNeedTimeMustDoStringMap.keySet()) {
            String listenStr = listenNeedTimeMustDoStringMap.get(listenNeedTimeMustDo);
            if (key.equals(listenStr)) {
                listenNeedTimeMustDo.listenNeedTimeMustDoOver(null, endNeedTimeMustDo.getDoThingVue());
            }
        }
    }


    private void getNowNeedTimeMustDo(TaoziTimeBean taoziTimeBean) {
        if (!reGetNowNeedTimeMustDo || allNeedTimeMustDo == null || allNeedTimeMustDo.getList() == null)
            return;
        if (nowNeedTimeMustDo == null || nowNeedTimeMustDo.getList() == null) {
            nowNeedTimeMustDo = new ResultNeedTimeMustDoBean();
        } else {
            nowNeedTimeMustDo.getList().clear();

        }
        reGetNowNeedTimeMustDo = false;
        for (NeedTimeMustDoBean needTimeMustDoBean : allNeedTimeMustDo.getList().values()) {
            if (needTimeMustDoBean == null)
                continue;
            if (CheckNeedTimeMustDoUtil.checkYearAndMonthAndDayIsOk(needTimeMustDoBean, taoziTimeBean.getYear(), taoziTimeBean.getMonth(), taoziTimeBean.getDay())
                    || CheckNeedTimeMustDoUtil.checkWeekIsOk(needTimeMustDoBean, taoziTimeBean.getWeek())) {
                if (CheckNeedTimeMustDoUtil.nowHourCanStartTime(needTimeMustDoBean, taoziTimeBean.getHour(), taoziTimeBean.getMinute(), taoziTimeBean.getSecond())) {
                    nowNeedTimeMustDo.add(needTimeMustDoBean);
                    continue;
                }
            }
        }
    }

}
