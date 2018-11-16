package com.zhuoyue.researchManement.bean;

import java.util.Date;

public class SubjectOpenTime {

    private String desc;
    private Date startTime;
    private Date endTime;
    private Boolean open;

    public SubjectOpenTime() {
    }

    public SubjectOpenTime(String desc, Date startTime, Date endTime, Boolean open) {
        this.desc = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.open = open;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
