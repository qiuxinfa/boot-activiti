package com.qxf.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Leave
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/5 22:50
 **/
public class Leave implements Serializable{
    private String id;
    private String userId;
    private String processInstanceId;
    private Date startDate;
    private Date endDate;
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
