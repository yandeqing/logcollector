package com.logstacksdk.bean.body;

import java.util.List;

/**
 * Page
 * Created by Tamic on 2016-03-24.
 */
public class Page {
    private String pageId;
    private String refererPageId;
    private String pageStartTime;
    private String pageEndTime;
    private String cityId;
    private String uid ;
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

    public String getPageStartTime() {
        return pageStartTime;
    }

    public void setPageStartTime(String pageStartTime) {
        this.pageStartTime = pageStartTime;
    }

    public String getPageEndTime() {
        return pageEndTime;
    }

    public void setPageEndTime(String pageEndTime) {
        this.pageEndTime = pageEndTime;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<KeyValueBean> getParameter() {
        return parameter;
    }

    public void setParameter(List<KeyValueBean> parameter) {
        this.parameter = parameter;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageId='" + pageId + '\'' +
                ", refererPageId='" + refererPageId + '\'' +
                ", pageStartTime='" + pageStartTime + '\'' +
                ", pageEndTime='" + pageEndTime + '\'' +
                ", cityId='" + cityId + '\'' +
                ", uid='" + uid + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
