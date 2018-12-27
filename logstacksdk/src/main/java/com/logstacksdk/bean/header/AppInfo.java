package com.logstacksdk.bean.header;


/**
 * app信息
 *
 * @author Zuber
 */
public class AppInfo extends BaseBean{

    private String appId;

    private String appScret;

    private String appVersion;

    private String appChannel;

    public AppInfo() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppScret() {
        return appScret;
    }

    public void setAppScret(String appScret) {
        this.appScret = appScret;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }
}
