package com.classManage.tusdt.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseTimetabling {
    private Integer id;

    private Integer courseId;

    private Integer userId;

    private Integer classId1;

    private Integer classId2;

    private Integer classId3;

    private Integer classId4;

    private Integer courseNum;

    private Integer buildingLayer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer schoolId;

    private String remark;

    private Integer result;

    private Integer isDelete;

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

    public Integer getClassId1() {
        return classId1;
    }

    public void setClassId1(Integer classId1) {
        this.classId1 = classId1;
    }

    public Integer getClassId2() {
        return classId2;
    }

    public void setClassId2(Integer classId2) {
        this.classId2 = classId2;
    }

    public Integer getClassId3() {
        return classId3;
    }

    public void setClassId3(Integer classId3) {
        this.classId3 = classId3;
    }

    public Integer getClassId4() {
        return classId4;
    }

    public void setClassId4(Integer classId4) {
        this.classId4 = classId4;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Integer getBuildingLayer() {
        return buildingLayer;
    }

    public void setBuildingLayer(Integer buildingLayer) {
        this.buildingLayer = buildingLayer;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}