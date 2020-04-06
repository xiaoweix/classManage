package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.SchoolCatalogBO;
import com.classManage.tusdt.model.BO.SchoolInfoListBO;
import com.classManage.tusdt.model.SchoolInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);

    List<SchoolCatalogBO> selectSchoolCatalog();

    List<SchoolInfoListBO> selectSchoolInfoList(@Param("schoolName") String schoolName);

    List<SchoolInfo> checkSchoolName(@Param("schoolName") String schoolName);

    List<SchoolInfo> checkSchoolCode(@Param("schoolCode") String schoolCode);

}