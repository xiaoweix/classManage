package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-05
 * Time: 15:24
 */
public class ClassroomApplyListBO {

    private Integer id;

    private String purpose;

    private Date applyTime;

    private Date startTime;

    private Date endTime;

    private Integer useNum;

    private String roomName;

    private String userName;

    private Integer userJobLevel;

    private Integer result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserJobLevel() {
        return userJobLevel;
    }

    public void setUserJobLevel(Integer userJobLevel) {
        this.userJobLevel = userJobLevel;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
