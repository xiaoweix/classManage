package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.ClassInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
}