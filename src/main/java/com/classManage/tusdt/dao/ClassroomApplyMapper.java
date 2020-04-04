package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.ClassroomApply;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassroomApplyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ClassroomApply record);

    int insertSelective(ClassroomApply record);

    ClassroomApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassroomApply record);

    int updateByPrimaryKey(ClassroomApply record);
}