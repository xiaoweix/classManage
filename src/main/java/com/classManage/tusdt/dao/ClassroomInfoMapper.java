package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.ClassUseBO;
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

    List<ClassroomInfo> getClassroomBySchoolID(@Param("schoolId") Integer schoolId,@Param("roomLayer") Integer roomLayer,@Param("roomCapacity") Integer roomCapacity);

    List<ClassroomInfo> getBySchoolID(@Param("schoolId") Integer schoolId);

    List<ClassUseBO> getClassUseBySchoolId(@Param("schoolId") Integer schoolId);
}