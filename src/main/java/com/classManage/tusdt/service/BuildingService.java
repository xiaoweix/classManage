package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.BuildingBaseInfoBO;
import com.classManage.tusdt.model.BuildingInfo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 16:38
 */
public interface BuildingService {

    ResponseData<String> addBuilding(BuildingInfo buildingInfo);

    ResponseData<String> modifyBuilding(BuildingInfo buildingInfo);

    ResponseData<String> removeBuilding(Integer buildingId);

    List<BuildingBaseInfoBO> getBuildingBaseInfo(String buildingName);

    boolean checkBuildingName(Integer schoolId, String buildingName);
}
