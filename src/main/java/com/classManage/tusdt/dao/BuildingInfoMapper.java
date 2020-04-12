package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.BuildingBaseInfoBO;
import com.classManage.tusdt.model.BuildingInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BuildingInfo record);

    int insertSelective(BuildingInfo record);

    BuildingInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuildingInfo record);

    int updateByPrimaryKey(BuildingInfo record);

    List<BuildingBaseInfoBO> getBuildingInfoList(@Param("buildingName") String buildingName,@Param("schoolId") Integer schoolId);

    List<BuildingInfo> checkBuildingName(Integer schoolId, String buildingName);
}