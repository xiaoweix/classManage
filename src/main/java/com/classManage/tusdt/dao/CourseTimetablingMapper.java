package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.CourseTimetabling;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseTimetablingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimetabling record);

    int insertSelective(CourseTimetabling record);

    CourseTimetabling selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTimetabling record);

    int updateByPrimaryKey(CourseTimetabling record);
}