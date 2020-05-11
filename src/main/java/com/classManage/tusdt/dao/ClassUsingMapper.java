package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.ClassUsingScheduleBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.ClassUsing;
import com.classManage.tusdt.model.CourseTimetabling;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassUsingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassUsing record);

    int insertSelective(ClassUsing record);

    ClassUsing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassUsing record);

    int updateByPrimaryKey(ClassUsing record);

    List<ClassroomNameBO> selectByTime(Integer schoolId, Integer buildingId, Integer year,Integer month, Integer day,Integer startCourse,Integer endCourse,Integer stuNum);

    List<ClassUsing> checkCourseTime(@Param("classroomId") Integer classroomId, @Param("year") Integer year, @Param("month") Integer month, @Param("week") Integer week, @Param("courseTime") Integer courseTime);

    List<ClassUsing> checkTeacherTime(@Param("teacherId") Integer teacherId, @Param("year") Integer year, @Param("month") Integer month, @Param("week") Integer week, @Param("courseTime") Integer courseTime);

    List<ClassUsing> checkWeekTime(@Param("teacherId") Integer teacherId, @Param("year") Integer year, @Param("month") Integer month, @Param("day") Integer day, @Param("week") Integer week);

    List<ClassUsingScheduleBO> getSchedule(@Param("teacherId") Integer teacherId, @Param("year") Integer year, @Param("month") Integer month, @Param("week") Integer week, @Param("courseTime") Integer courseTime);
}