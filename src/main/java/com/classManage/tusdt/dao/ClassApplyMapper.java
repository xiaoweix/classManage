package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.ClassApply;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassApplyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ClassApply record);

    int insertSelective(ClassApply record);

    ClassApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassApply record);

    int updateByPrimaryKey(ClassApply record);
}