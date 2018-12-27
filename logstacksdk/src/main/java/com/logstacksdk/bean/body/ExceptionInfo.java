package com.logstacksdk.bean.body;

import com.logstacksdk.utils.DateUtil;

/**
 * Created by null on 2016/9/22.
 */
public class ExceptionInfo {
    private String phoneModel;
    private String systemModel;
    private String systemVersion;
    private String exceptionString;
    private String actionTime;

    public ExceptionInfo() {

    }

    public ExceptionInfo(String phoneModel, String systemModel, String systemVersion, String exceptionString) {
        this.phoneModel = phoneModel;
        this.systemModel = systemModel;
        this.systemVersion = systemVersion;
        this.exceptionString = exceptionString;
        this.actionTime = DateUtil.getDateString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public String toString() {
        return "ExceptionInfo{" +
                "phoneModel='" + phoneModel + '\'' +
                ", systemModel='" + systemModel + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", exceptionString='" + exceptionString + '\'' +
                '}';
    }
}
