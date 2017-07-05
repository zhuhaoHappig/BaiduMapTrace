package com.baidu.track.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baidu.track.R;
import com.baidu.track.TrackApplication;
import com.baidu.track.utils.CommonUtil;
import com.baidu.track.utils.Constants;
import com.baidu.track.utils.DateDialog;

import java.text.SimpleDateFormat;

/**
 * 轨迹查询选项
 */
public class TrackQueryOptionsActivity extends BaseActivity
        implements CompoundButton.OnCheckedChangeListener {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private TrackApplication trackApp = null;
    private Intent result = null;
    private DateDialog dateDialog = null;
    private Button startTimeBtn = null;
    private Button endTimeBtn = null;
    private CheckBox processedCBx = null;
    private CheckBox denoiseCBx = null;
    private CheckBox vacuateCBx = null;
    private CheckBox mapmatchCBx = null;
    private TextView radiusText = null;
    private DateDialog.Callback startTimeCallback = null;
    private DateDialog.Callback endTimeCallback = null;
    private long startTime = CommonUtil.getCurrentTime();
    private long endTime = CommonUtil.getCurrentTime();
    private boolean isProcessed = true;
    private boolean isDenoise = false;
    private boolean isVacuate = false;
    private boolean isMapmatch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.track_query_options_title);
        setOptionsButtonInVisible();
        init();
        trackApp = (TrackApplication) getApplication();
    }

    private void init() {
        result = new Intent();
        startTimeBtn = (Button) findViewById(R.id.start_time);
        endTimeBtn = (Button) findViewById(R.id.end_time);
        processedCBx = (CheckBox) findViewById(R.id.processed);
        denoiseCBx = (CheckBox) findViewById(R.id.denoise);
        vacuateCBx = (CheckBox) findViewById(R.id.vacuate);
        mapmatchCBx = (CheckBox) findViewById(R.id.mapmatch);
        radiusText = (TextView) findViewById(R.id.radius_threshold);

        StringBuilder startTimeBuilder = new StringBuilder();
        startTimeBuilder.append(getResources().getString(R.string.start_time));
        startTimeBuilder.append(simpleDateFormat.format(System.currentTimeMillis()));
        startTimeBtn.setText(startTimeBuilder.toString());

        StringBuilder endTimeBuilder = new StringBuilder();
        endTimeBuilder.append(getResources().getString(R.string.end_time));
        endTimeBuilder.append(simpleDateFormat.format(System.currentTimeMillis()));
        endTimeBtn.setText(endTimeBuilder.toString());

        processedCBx.setOnCheckedChangeListener(this);
        denoiseCBx.setOnCheckedChangeListener(this);
        vacuateCBx.setOnCheckedChangeListener(this);
        mapmatchCBx.setOnCheckedChangeListener(this);

    }

    public void onStartTime(View v) {
        if (null == startTimeCallback) {
            startTimeCallback = new DateDialog.Callback() {
                @Override
                public void onDateCallback(long timeStamp) {
                    TrackQueryOptionsActivity.this.startTime = timeStamp;
                    StringBuilder startTimeBuilder = new StringBuilder();
                    startTimeBuilder.append(getResources().getString(R.string.start_time));
                    startTimeBuilder.append(simpleDateFormat.format(timeStamp * 1000));
                    startTimeBtn.setText(startTimeBuilder.toString());
                }
            };
        }
        if (null == dateDialog) {
            dateDialog = new DateDialog(this, startTimeCallback);
        } else {
            dateDialog.setCallback(startTimeCallback);
        }
        dateDialog.show();
    }

    public void onEndTime(View v) {
        if (null == endTimeCallback) {
            endTimeCallback = new DateDialog.Callback() {
                @Override
                public void onDateCallback(long timeStamp) {
                    TrackQueryOptionsActivity.this.endTime = timeStamp;
                    StringBuilder endTimeBuilder = new StringBuilder();
                    endTimeBuilder.append(getResources().getString(R.string.end_time));
                    endTimeBuilder.append(simpleDateFormat.format(timeStamp * 1000));
                    endTimeBtn.setText(endTimeBuilder.toString());
                }
            };
        }
        if (null == dateDialog) {
            dateDialog = new DateDialog(this, endTimeCallback);
        } else {
            dateDialog.setCallback(endTimeCallback);
        }
        dateDialog.show();
    }

    public void onCancel(View v) {
        super.onBackPressed();
    }

    public void onFinish(View v) {
        result.putExtra("startTime", startTime);
        result.putExtra("endTime", endTime);
        result.putExtra("processed", isProcessed);
        result.putExtra("denoise", isDenoise);
        result.putExtra("vacuate", isVacuate);
        result.putExtra("mapmatch", isMapmatch);

        String radiusStr = radiusText.getText().toString();
        if (!TextUtils.isEmpty(radiusStr)) {
            try {
                result.putExtra("radius", Integer.parseInt(radiusStr));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        setResult(Constants.RESULT_CODE, result);
        super.onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.processed:
                isProcessed = isChecked;
                break;

            case R.id.denoise:
                isDenoise = isChecked;
                break;

            case R.id.vacuate:
                isVacuate = isChecked;
                break;

            case R.id.mapmatch:
                isMapmatch = isChecked;
                break;

            default:
                break;
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_trackquery_options;
    }

}
