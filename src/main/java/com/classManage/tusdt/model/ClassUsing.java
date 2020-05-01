package com.classManage.tusdt.model;

public class ClassUsing {
    private Integer id;

    private Integer courseId;

    private Integer userId;

    private Integer roomId;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer week;

    private Integer courseTime;

    private String remark;

    private Integer isDelete;

    public ClassUsing() {
    }

    public ClassUsing(Integer courseId, Integer userId, Integer roomId, Integer year, Integer month, Integer day, Integer week, Integer courseTime, String remark, Integer isDelete) {
        this.courseId = courseId;
        this.userId = userId;
        this.roomId = roomId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
        this.courseTime = courseTime;
        this.remark = remark;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}