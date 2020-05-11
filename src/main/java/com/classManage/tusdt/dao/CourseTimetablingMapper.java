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

    List<CoursePlanListBO> getCoursePlan(@Param("schoolId") Integer schoolId, @Param("courseName") String courseName);

    List<CoursePlanListBO> getTeacherCoursePlan(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId,@Param("courseName") String courseName);
}