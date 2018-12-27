package com.logstacksdk.core.crash;

import android.content.Context;
import android.util.Log;

import com.logstacksdk.storage.db.helper.StaticsAgent;
import com.logstacksdk.bean.body.ExceptionInfo;
import com.logstacksdk.utils.DeviceUtil;

/**
 * Created by null on 2016/9/22.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context context;
    public static CrashHandler INSTANCE;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private CrashHandler() {
    }

    public void init(Context context) {
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex != null) {
            StackTraceElement[] stackTraceElements = ex.getStackTrace();
            Log.i("jiangTest", stackTraceElements.length + "---");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ex.getMessage()).append("\n");
            for (int i = stackTraceElements.length - 1; i >= 0; i--) {
                stringBuffer.append(stackTraceElements[i].getFileName()).append(":").append(stackTraceElements[i].getClassName()).append(stackTraceElements[i].getMethodName()).append("(").append(stackTraceElements[i].getLineNumber()).append(")").append("\n");
            }
            Log.i("jiangTest", stringBuffer.toString());
            StaticsAgent.storeObject(new ExceptionInfo(DeviceUtil.getPhoneModel(), DeviceUtil.getSystemModel(), String.valueOf(DeviceUtil.getSystemVersion()), stringBuffer.toString()));
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
}
