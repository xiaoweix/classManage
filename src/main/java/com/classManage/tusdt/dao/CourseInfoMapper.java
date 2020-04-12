package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.CourseBaseInfoBO;
import com.classManage.tusdt.model.CourseInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);

    List<CourseInfo> getCourseListByName(@Param("courseName") String courseName);

    List<CourseBaseInfoBO> getCourseBaseInfo(@Param("courseName") String courseName, @Param("schoolId")Integer schoolId);
}