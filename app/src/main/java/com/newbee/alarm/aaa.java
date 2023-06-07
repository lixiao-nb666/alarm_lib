package com.newbee.alarm;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.newbee.alarm_lib.bean.TaoziTimeBean;
import com.newbee.alarm_lib.bean.alarm.AlarmHourMinuteSecondInfo;
import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;
import com.newbee.alarm_lib.event.alarm.TaoziTimeAlarmEventSubscriptionSubject;
import com.newbee.alarm_lib.event.between.TaoziTimeBetweenEventSubscriptionSubject;
import com.newbee.alarm_lib.event.time.TaoziTimeBeanListenObserver;
import com.newbee.alarm_lib.event.time.TaoziTimeBeanListenSubscriptionSubject;
import com.newbee.alarm_lib.listen.ListenAlarm;
import com.newbee.alarm_lib.listen.ListenBetweenTime;
import com.newbee.alarm_lib.service.TaoziTimeClockServiceDao;
import com.newbee.bulid_lib.mybase.activity.BaseCompatActivity;
import com.newbee.bulid_lib.mybase.service.BaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class aaa extends BaseCompatActivity {

    TaoziTimeClockServiceDao serviceDao;

    TaoziTimeBeanListenObserver timeBeanListenObserver=new TaoziTimeBeanListenObserver() {
        @Override
        public void listenTime(TaoziTimeBean taoziTimeBean) {
            Message msg=new Message();
            msg.what=0;
            msg.obj=taoziTimeBean;
            handler.sendMessage(msg);

            if(taoziTimeBean.getSecond()% 10==0){
                AlarmInfoBean alarmInfoBean=new AlarmInfoBean();
                alarmInfoBean.setBs(taoziTimeBean.getTime()+"");
                alarmInfoBean.setAlarmStr(taoziTimeBean.toString());
                List<AlarmHourMinuteSecondInfo> alarmHourMinuteSecondInfoList=new ArrayList<>();
                for(int i=1;i<4;i++){
                    AlarmHourMinuteSecondInfo alarmHourMinuteSecondInfo=new AlarmHourMinuteSecondInfo();
                    alarmHourMinuteSecondInfo.setHour(taoziTimeBean.getHour());
                    alarmHourMinuteSecondInfo.setMinute(taoziTimeBean.getMinute());
                    alarmHourMinuteSecondInfo.setSecond(taoziTimeBean.getSecond()+(i*2));
                    alarmHourMinuteSecondInfoList.add(alarmHourMinuteSecondInfo);
                }
                alarmInfoBean.setAlarmHourMinuteSecondInfoList(alarmHourMinuteSecondInfoList);
                Set<Integer> weeks=new HashSet<>();
                weeks.add(taoziTimeBean.getWeek());
                alarmInfoBean.setWeeks(weeks);
                TaoziTimeAlarmEventSubscriptionSubject.getInstence().addAlarmEvent(alarmInfoBean);

                Log.i(tag,"kankanAlarm0:"+taoziTimeBean.toString());
            }
        }
    };



    int alarmNumb;
    private ListenAlarm listenAlarm=new ListenAlarm() {
        @Override
        public void listenAlarm(TaoziTimeBean taoziTimeBean, AlarmInfoBean alarmInfoBean) {
            Log.i(tag,"kankanAlarm1:"+taoziTimeBean.toString());
            Log.i(tag,"kankanAlarm2:"+alarmInfoBean.toString());
            alarmNumb++;
            Message msg=new Message();
            msg.what=1;
            msg.obj=alarmInfoBean;
            handler.sendMessage(msg);
        }
    };

    private int betNumb;
    private ListenBetweenTime listenBetweenTime=new ListenBetweenTime() {
        @Override
        public void listenBetween(long lastTime, long nowTime) {
            Log.i(tag,"kankanlistenBetween1:"+lastTime+"--"+nowTime);
            betNumb++;
            handler.sendEmptyMessage(2);
        }
    };


    private TextView tv,tv1,tv2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    TaoziTimeBean taoziTimeBean= (TaoziTimeBean) msg.obj;
                    tv.setText(taoziTimeBean.getSecond()+"");
                    break;
                case 1:
                    AlarmInfoBean alarmInfoBean= (AlarmInfoBean) msg.obj;
                    tv1.setText(alarmNumb+"---"+alarmInfoBean.getBs());
                    break;
                case 2:
                    tv2.setText(betNumb+"");
                    break;
            }

        }
    };


    private BaseService.StatuListen statuListen=new BaseService.StatuListen() {
        @Override
        public void isStart() {
            TaoziTimeBeanListenSubscriptionSubject.getInstence().attach(timeBeanListenObserver);
            TaoziTimeAlarmEventSubscriptionSubject.getInstence().addListenAlarm(listenAlarm);
            TaoziTimeBetweenEventSubscriptionSubject.getInstence().addListenBetweenTime(listenBetweenTime,5000);
        }
    };


    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_aa;
    }

    @Override
    public void initView() {
        tv=findViewById(R.id.tv_set);
        tv1=findViewById(R.id.tv_1);
        tv2=findViewById(R.id.tv_2);
        serviceDao=new TaoziTimeClockServiceDao(this,statuListen);
        serviceDao.startServiceIsBind();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initControl() {

    }

    @Override
    public void closeActivity() {

        TaoziTimeBeanListenSubscriptionSubject.getInstence().detach(timeBeanListenObserver);
        TaoziTimeAlarmEventSubscriptionSubject.getInstence().removeListenAlarm(listenAlarm);
        TaoziTimeBetweenEventSubscriptionSubject.getInstence().removelistenBetweenTime(listenBetweenTime);
        serviceDao.stopServiceIsBind();
    }

    @Override
    public void viewIsShow() {
        showToast("shenmewnayi");
    }

    @Override
    public void viewIsPause() {

    }

    @Override
    public void changeConfig() {

    }


}
