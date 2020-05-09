package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.BuildingBaseInfoBO;
import com.classManage.tusdt.model.BO.ClassBaseInfoListBO;
import com.classManage.tusdt.model.BuildingInfo;
import com.classManage.tusdt.model.ClassInfo;
import com.classManage.tusdt.service.BuildingService;
import com.classManage.tusdt.service.ClassInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 16:34
 */
@Api(protocols = "http,https", tags = {"BuildingManage"}, value = "/class_manage/building",description = "教学楼信息管理")
@RestController
@RequestMapping(value = "/class_manage/building")
public class BuildingController {

    @Resource
    private BuildingService buildingService;

    @ApiOperation(value = "新增一个教学楼信息", notes = "增加教学楼")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addBuilding(HttpServletRequest request,@RequestBody BuildingInfo buildingInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        buildingInfo.setSchoolId(schoolId);
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = buildingService.addBuilding(buildingInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可添加教学楼");
        }
        return responseData;
    }

    @ApiOperation(value = "删除一个教学楼", notes = "删除教学楼")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeBuilding", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeBuilding(HttpServletRequest request,
                                            @RequestParam(value = "buildingId",required = true) Integer buildingId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = buildingService.removeBuilding(buildingId);;
        } else {
            responseData.setError("权限不足，仅高校管理员可删除教学楼");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑教学楼资料", notes = "编辑教学楼资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyBuilding", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyBuilding(HttpServletRequest request, @RequestBody BuildingInfo buildingInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = buildingService.modifyBuilding(buildingInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可修改教学楼信息");
        }
        return responseData;
    }

    @ApiOperation(value = "获取教学楼资料列表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getBuildingList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<BuildingBaseInfoBO>> getBuildingList(HttpServletRequest request,
                                                                  @RequestParam(value = "buildingName",required = false) String buildingName) {

        ResponseData<List<BuildingBaseInfoBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<BuildingBaseInfoBO> buildingInfoList = buildingService.getBuildingBaseInfo(buildingName, schoolId);
        if(buildingInfoList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",buildingInfoList);
        return responseData;
    }

}
