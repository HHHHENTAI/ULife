package com.example.hhhhentai.JsonGet;

import java.io.Serializable;

public class JsonExpressageInfo implements Serializable {
    private String datetime;
    private String remark;
    private String zone;

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getRemark() {
        return remark;
    }

    public String getZone() {
        return zone;
    }
}
