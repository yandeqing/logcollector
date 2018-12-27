package com.logstacksdk.statsdk;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.logstacksdk.bean.body.ViewPath;
import com.logstacksdk.utils.ViewScratchUtil;
import com.logstacksdk.core.TcStatInterface;
import com.logstacksdk.utils.LogUtil;


/**
 * @date 2018/9/20
 * @author  yandeqing
 * @version v1.0.0
 * 备注:
 * 
 */
public  class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private static final boolean debug = true;



    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewScratchUtil.getInstance().attachActivity(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        TcStatInterface.recordPageStart(BaseActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TcStatInterface.recordPageEnd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            ViewPath path = ViewScratchUtil.getInstance().findClickView(ev);
            if (path != null) {
                LogUtil.i(debug, TAG, "【BaseActivity.dispatchTouchEvent()】【path=" + path + "】");
                TcStatInterface.initEvent(path);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}
