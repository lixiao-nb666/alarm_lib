//package com.newbee.alarm_lib.util;
//
//import android.text.TextUtils;
//
//
//import com.newbee.alarm_lib.bean.SystemDeviceTimeInfoBean;
//
//public class SystemTimeUtil {
//    private final String tag = getClass().getName() + ">>>>";
//    private static SystemTimeUtil systemTimeUtil;
//    private SystemDeviceTimeInfoBean systemDeviceTimeInfoBean;
//
//
//    private SystemTimeUtil() {
//        String str = CanNotDelectShare.getInstance().getString(tag);
//        if (TextUtils.isEmpty(str)) {
//            systemDeviceTimeInfoBean = new SystemDeviceTimeInfoBean();
//        } else {
//            systemDeviceTimeInfoBean = MyGson.getInstance().fromJson(str, SystemDeviceTimeInfoBean.class);
//            if (null == systemDeviceTimeInfoBean) {
//                systemDeviceTimeInfoBean = new SystemDeviceTimeInfoBean();
//            }
//        }
//
//    }
//
//    public static SystemTimeUtil getInstance() {
//        if (null == systemTimeUtil) {
//            synchronized (SystemTimeUtil.class) {
//                if (null == systemTimeUtil) {
//                    systemTimeUtil = new SystemTimeUtil();
//                }
//            }
//        }
//        return systemTimeUtil;
//    }
//
//    private boolean isSetDeviceStartTime=false;
//    public void writeDataIsStartDevice() {
//        isSetDeviceStartTime=true;
//        long nowTime = System.currentTimeMillis();
//        if (nowTime - systemDeviceTimeInfoBean.getNowDeviceStartTime() < 3 * 60 * 1000) {
//            return;
//        }
//        systemDeviceTimeInfoBean.setNowDeviceStartTime(nowTime);
//        systemDeviceTimeInfoBean.setStartDeviceNumb(systemDeviceTimeInfoBean.getStartDeviceNumb() + 1);
//        CanNotDelectShare.getInstance().putString(tag, MyGson.getInstance().toGsonStr(systemDeviceTimeInfoBean));
//    }
//
//    /**
//     * 写入设备运行时长
//     */
//    public void writeUseTimeData() {
//        systemDeviceTimeInfoBean.setDeviceRunTime(systemDeviceTimeInfoBean.getDeviceRunTime() + 1);
//        CanNotDelectShare.getInstance().putString(tag, MyGson.getInstance().toGsonStr(systemDeviceTimeInfoBean));
//        if (!isUpload) {
//            sendToService();
//        }
//    }
//
//    private boolean isUpload = false;
//    public void sendToService() {
//        if (NetHttpConfig.getIsNetConnect()&&isSetDeviceStartTime) {
//            NetHttpServiceEventSubscriptionSubject.getInstence().uploadDeviceRunTime(systemDeviceTimeInfoBean);
//            isUpload = true;
//        }
//
//    }
//}
