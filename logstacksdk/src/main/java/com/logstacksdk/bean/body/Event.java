package com.logstacksdk.bean.body;

import java.util.List;

/**
 * 对应的event事件
 * Created by ZHANGLIANG098 on 2016-03-24.
 */
public class Event {
    private String pageId;
    private String refererPageId;
    private String uid ;
    private String cityId;
    private String eventName;
    private String actionTime;
    private List<KeyValueBean> parameter ;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getRefererPageId() {
        return refererPageId;
    }

    public void setRefererPageId(String refererPageId) {
        this.refererPageId = refererPageId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public List<KeyValueBean> getParameter() {
        return parameter;
    }

    public void setParameter(List<KeyValueBean> parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "Event{" +
                "pageId='" + pageId + '\'' +
                ", refererPageId='" + refererPageId + '\'' +
                ", uid='" + uid + '\'' +
                ", cityId='" + cityId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", actionTime='" + actionTime + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
