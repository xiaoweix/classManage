package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.ClassSchedule;
import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.model.CourseTimetabling;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-12
 * Time: 15:45
 */
public interface CourseTimetablingService {

    ResponseData<String> addCoursePlan(CourseTimetabling courseTimetabling);

    List<CoursePlanListBO> getCoursePlan(Integer schoolId, String courseName );

    ResponseData<String> dealCoursePlan(Integer courseTimetablingId);

    ResponseData<String> disagreeCoursePlan(Integer courseTimetablingId);

    List<ClassSchedule> getSchedule(Integer userID);
}
