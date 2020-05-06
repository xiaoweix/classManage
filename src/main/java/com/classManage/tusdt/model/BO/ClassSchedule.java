package com.classManage.tusdt.model.BO;

import java.util.List;

public class ClassSchedule {

    Integer week;

    List<String> courseInfo;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<String> getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(List<String> courseInfo) {
        this.courseInfo = courseInfo;
    }
}
