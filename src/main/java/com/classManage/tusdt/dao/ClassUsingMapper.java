package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.ClassUsing;

import java.util.List;

public interface ClassUsingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassUsing record);

    int insertSelective(ClassUsing record);

    ClassUsing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassUsing record);

    int updateByPrimaryKey(ClassUsing record);

    List<ClassroomNameBO> selectByTime(Integer schoolId, Integer buildingId, Integer year,Integer month, Integer day,Integer startCourse,Integer endCourse);
}