package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.SchoolInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);
}