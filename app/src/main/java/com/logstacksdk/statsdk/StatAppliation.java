package com.logstacksdk.statsdk;

import android.app.Application;

import com.logstacksdk.config.NetConfig;
import com.logstacksdk.core.TcStatInterface;
import com.logstacksdk.storage.db.DbManager;
import com.logstacksdk.utils.LogUtil;


/**
 * Created by LIUYONGKUI726 on 2016-04-13.
 */
public class StatAppliation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(debug, TAG, "【StatAppliation.onCreate()】【start】");
        DbManager.getInstance().init(this);

        // you app id
        int appId = 21212;
        // assets
//        String fileName = "";
        String fileName = "stat_id.json";
        // attachActivity statSdk
        TcStatInterface.initialize(this, appId, "zuber", fileName);
        // set upload url
        TcStatInterface.setUrl(NetConfig.ONLINE_URL);
        // Set loadPolicy
        TcStatInterface.setUploadPolicy(TcStatInterface.UploadPolicy.UPLOAD_POLICY_REALTIME, TcStatInterface.UPLOAD_TIME_ONE);

        TcStatInterface.recordAppStart();
    }

    @Override
    public void onTerminate() {
        LogUtil.i(debug, TAG, "【StatAppliation.onTerminate()】【start】");
        DbManager.getInstance().destroy();
        TcStatInterface.recordAppEnd();
        super.onTerminate();
    }

    private static final String TAG = StatAppliation.class.getSimpleName();
    private static final boolean debug = true;

}
