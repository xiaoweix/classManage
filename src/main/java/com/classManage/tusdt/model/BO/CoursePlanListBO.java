package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-12
 * Time: 15:59
 */
public class CoursePlanListBO {
    private Integer id;//courseName,courseNum,userName,classId1,classId2,classId3,classId4,remark

    private String courseName;

    private Integer courseNum;

    private String userName;

    private Integer classId1;

    private Integer classId2;

    private Integer classId3;

    private Integer classId4;

    private String remark;

    private Date startTime;

    private Integer result;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
