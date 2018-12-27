package com.logstacksdk.bean.body;

/**

 * Created by Tamic 2016-03-24.
 */
public class AppAction {
    private String actionTime;
    private int appActionType;
    private String appActionDesc;

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public int getAppActionType() {
        return appActionType;
    }

    public void setAppActionType(int appActionType) {
        this.appActionType = appActionType;
    }

    public String getAppActionDesc() {
        return appActionDesc;
    }

    public void setAppActionDesc(String appActionDesc) {
        this.appActionDesc = appActionDesc;
    }

    @Override
    public String toString() {
        return "AppAction{" +
                "actionTime='" + actionTime + '\'' +
                ", appActionType='" + appActionType + '\'' +
                '}';
    }
}
