package com.logstacksdk.bean.header;

import java.io.Serializable;

/**
 * 版权:上海屋聚 版权所有
 * author: yandeqing
 * version: 3.0.0
 * date:2018/9/1 11:54
 * 备注:
 *
 * @author Zuber
 */
public class BaseBean implements Serializable {
    protected Integer id;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
