package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.model.CourseTimetabling;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTimetablingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimetabling record);

    int insertSelective(CourseTimetabling record);

    CourseTimetabling selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTimetabling record);

    int updateByPrimaryKey(CourseTimetabling record);

    List<CoursePlanListBO> getCoursePlan(Integer schoolId, String courseName);

    CourseTimetabling checkCourseTime(@Param("classroomId") Integer classroomId,@Param("year") Integer year,@Param("month") Integer month,@Param("day") Integer day,@Param("courseTime") Integer courseTime);
}