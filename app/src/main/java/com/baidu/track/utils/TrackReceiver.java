package com.baidu.track.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;

import com.baidu.trace.model.StatusCodes;

/**
 * 这个广播意义在于:手机锁屏后一段时间，cpu可能会进入休眠模式，此时无法严格按照采集周期获取定位依据，导致轨迹点缺失。
 * 避免这种情况的方式是APP持有电量锁。还有doze 模式：Doze模式是Android6.0上新出的一种模式，是一种全新的、低能耗的状态，在后台只有部分任务允许运行，
 * 其他都被强制停止。当用户一段时间没有使用手机的时候，Doze模式通过延缓app后台的CPU和网络活动减少电量的消耗。
 * 若手机厂商生产的定制机型中使用到该模式，需要申请将app添加进白名单，可尽量帮助鹰眼服务在后台持续运行
 */
public class TrackReceiver extends BroadcastReceiver {

    private WakeLock wakeLock = null;

    public TrackReceiver(WakeLock wakeLock) {
        super();
        this.wakeLock = wakeLock;
    }

    @SuppressLint("Wakelock")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            if (null != wakeLock && !(wakeLock.isHeld())) {
                wakeLock.acquire();
            }
        } else if (Intent.ACTION_SCREEN_ON.equals(action) || Intent.ACTION_USER_PRESENT.equals(action)) {
            if (null != wakeLock && wakeLock.isHeld()) {
                wakeLock.release();
            }
        } else if (StatusCodes.GPS_STATUS_ACTION.equals(action)) {
            int statusCode = intent.getIntExtra("statusCode", 0);
            String statusMessage = intent.getStringExtra("statusMessage");
            System.out.println(String.format("GPS status, statusCode:%d, statusMessage:%s", statusCode,
                    statusMessage));

        }
    }

}
