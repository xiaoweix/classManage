package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.SchoolCatalogBO;
import com.classManage.tusdt.model.BO.SchoolInfoListBO;
import com.classManage.tusdt.model.ClassInfo;
import com.classManage.tusdt.model.SchoolInfo;
import com.classManage.tusdt.service.SchoolInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 20:04
 */
@Api(protocols = "http,https", tags = {"SchoolManage"}, value = "/class_manage/school",description = "学校信息管理")
@RestController
@RequestMapping(value = "/class_manage/school")
public class SchoolInfoController {


    @Resource
    SchoolInfoService schoolInfoService;


    @ApiOperation(value = "新增一个学校信息", notes = "增加学校")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addSchool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addSchool(HttpServletRequest request, @RequestBody SchoolInfo schoolInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_STU.equals(level)) {
            responseData = schoolInfoService.addSchool(schoolInfo);
        } else {
            responseData.setError("权限不足，仅系统管理员可删除课程信息");
        }
        return responseData;
    }

    @ApiOperation(value = "删除一所学校", notes = "删除学校")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeSchool", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeSchool(HttpServletRequest request,
                                                @RequestParam(value = "schoolID",required = true) Integer schoolID) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_SUPER.equals(level)) {
            responseData = schoolInfoService.removeSchool(schoolID);
        } else {
            responseData.setError("权限不足，仅系统管理员可删除高校信息");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑学校资料", notes = "编辑学校资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifySchoolInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifySchoolInfo(HttpServletRequest request, @RequestBody SchoolInfo schoolInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_STU.equals(level)) {
            responseData = schoolInfoService.modifySchool(schoolInfo);
        } else {
            responseData.setError("权限不足，仅系统管理员可修改高校信息");
        }
        return responseData;
    }

    @ApiOperation(value = "获取学校资料列表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getSchoolList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<SchoolInfoListBO>> getSchoolList(HttpServletRequest request,
                                                               @RequestParam(value = "schoolName",required = false) String schoolName) {

        ResponseData<List<SchoolInfoListBO>> responseData = new ResponseData<>();
        List<SchoolInfoListBO> schoolInfoListBOList = schoolInfoService.getSchoolInfo(schoolName);
        if(schoolInfoListBOList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",schoolInfoListBOList);
        return responseData;
    }
    @ApiOperation(value = "获取学校资料目录", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getSchoolCatalog", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<SchoolCatalogBO>> getSchoolCatalog(HttpServletRequest request) {

        ResponseData<List<SchoolCatalogBO>> responseData = new ResponseData<>();
        List<SchoolCatalogBO> schoolInfoListBOList = schoolInfoService.getSchoolCatalog();
        if(schoolInfoListBOList == null) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",schoolInfoListBOList);
        return responseData;
    }

}
