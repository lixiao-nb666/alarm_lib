package com.newbee.alarm_lib.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.newbee.bulid_lib.mybase.service.BaseService;
import com.newbee.bulid_lib.mybase.service.BaseServiceDao;

/**
 * Created by xiefuning on 2017/5/11.
 * about:
 */

public class TaoziTimeClockServiceDao extends BaseServiceDao {


    public TaoziTimeClockServiceDao(Context context) {
        super(context);

    }

    private BaseService.StatuListen statuListen;
    public TaoziTimeClockServiceDao(Context context, BaseService.StatuListen statuListen) {
        super(context);
        this.statuListen=statuListen;
    }

    @Override
    public BaseService.StatuListen getStatuListen() {
        return statuListen;
    }

    @Override
    protected Class getSsCls() {
        return TaoziTimeClockService.class;
    }
}
