package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 14:50
 */
public class CourseBaseInfoBO {
    private Integer id;

    private String courseName;

    private Integer courseNum;

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
}
