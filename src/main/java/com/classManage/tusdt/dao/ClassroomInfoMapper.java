package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.ClassroomInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassroomInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ClassroomInfo record);

    int insertSelective(ClassroomInfo record);

    ClassroomInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassroomInfo record);

    int updateByPrimaryKey(ClassroomInfo record);
}