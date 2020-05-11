package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.OwnClassroomApplyListBO;
import com.classManage.tusdt.model.ClassroomApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassroomApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassroomApply record);

    int insertSelective(ClassroomApply record);

    ClassroomApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassroomApply record);

    int updateByPrimaryKey(ClassroomApply record);

    List<OwnClassroomApplyListBO> getOwnApplyClassroom(@Param("userId") Integer userId);

    List<ClassroomApplyListBO> getAdminApplyClassroom(@Param("result") Integer result,@Param("schoolId") Integer schoolId);
}