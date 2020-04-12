package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.CourseBaseInfoBO;
import com.classManage.tusdt.model.CourseInfo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 20:05
 */
public interface CourseInfoService {

    ResponseData<String> addCourse(CourseInfo courseInfo);

    ResponseData<String> modifyCourse(CourseInfo courseInfo);

    ResponseData<String> removeCourse(Integer courseId);

    List<CourseBaseInfoBO> getCourseList(String courseName, Integer schoolId);

    boolean checkCourseName(String courseName);
}
