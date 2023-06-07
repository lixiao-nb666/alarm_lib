package com.newbee.alarm_lib.manager;

public class TaoziTimeClockManager {


    private static TaoziTimeClockManager manager;
    private TaoziTimeClockManager(){}

    public static TaoziTimeClockManager getInstance(){
        if(null==manager){
            synchronized (TaoziTimeClockManager.class){
                if(null==manager){
                    manager=new TaoziTimeClockManager();
                }
            }
        }
        return manager;
    }


}
