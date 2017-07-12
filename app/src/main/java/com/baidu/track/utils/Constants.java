package com.baidu.track.utils;

public final class Constants {

    public static final String TAG = "BaiduTraceSDK_V3";

    public static final int REQUEST_CODE = 1;

    public static final int RESULT_CODE = 1;

    public static final int DEFAULT_RADIUS_THRESHOLD = 0;

    public static final int PAGE_SIZE = 5000;

    /**
     * 默认采集周期
     */
    public static final int DEFAULT_GATHER_INTERVAL = 5;

    /**
     * 默认打包周期
     */
    public static final int DEFAULT_PACK_INTERVAL = 15;

    /**
     * 实时定位间隔(单位:秒)
     */
    public static final int LOC_INTERVAL = 5;

    /**
     * 最后一次定位信息
     */
    public static final String LAST_LOCATION = "last_location";

}
