package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.BuildingInfoMapper;
import com.classManage.tusdt.model.BO.BuildingBaseInfoBO;
import com.classManage.tusdt.model.BO.ClassBaseInfoListBO;
import com.classManage.tusdt.model.BuildingInfo;
import com.classManage.tusdt.model.ClassInfo;
import com.classManage.tusdt.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 16:38
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingInfoMapper buildingInfoMapper;

    @Override
    public ResponseData<String> addBuilding(BuildingInfo buildingInfo) {

        ResponseData<String> responseData = new ResponseData<>();

        if(checkBuildingName(buildingInfo.getSchoolId(),buildingInfo.getBuildingName())) {
            responseData.setError("教学楼名重复");
            return responseData;
        }

        buildingInfo.setIsDelete(CommonConstant.DELETED_NO);

        Integer result = buildingInfoMapper.insert(buildingInfo);
        if (result == 0) {
            responseData.setError("添加失败");
            return responseData;
        }
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyBuilding(BuildingInfo buildingInfo) {
        ResponseData<String> responseData = new ResponseData<>();

        if(checkBuildingName(buildingInfo.getSchoolId(),buildingInfo.getBuildingName())) {
            responseData.setError("教学楼名重复");
            return responseData;
        }

        Integer result = buildingInfoMapper.updateByPrimaryKeySelective(buildingInfo);
        if (result == 0) {
            responseData.setError("修改失败");
            return responseData;
        }
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> removeBuilding(Integer buildingId) {
        ResponseData<String> responseData = new ResponseData<>();

        //TODO 检查楼层有没有教室
        BuildingInfo buildingInfo = buildingInfoMapper.selectByPrimaryKey(buildingId);
        if(buildingInfo == null) {
            responseData.setError("楼栋不存在");
        }
        buildingInfo.setIsDelete(CommonConstant.DELETED_YES);
        Integer result = buildingInfoMapper.updateByPrimaryKeySelective(buildingInfo);
        if (result == 0) {
            responseData.setError("删除失败");
            return responseData;
        }
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public List<BuildingBaseInfoBO> getBuildingBaseInfo(String buildingName) {

        return buildingInfoMapper.getBuildingInfoList(buildingName);
    }

    @Override
    public boolean checkBuildingName(Integer schoolId, String buildingName) {
        List<BuildingInfo> buildingInfoList = buildingInfoMapper.checkBuildingName(schoolId, buildingName);
        return buildingInfoList != null && !buildingInfoList.isEmpty();
    }
}
