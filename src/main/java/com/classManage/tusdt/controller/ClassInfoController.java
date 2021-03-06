package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.ClassBaseInfoListBO;
import com.classManage.tusdt.model.BO.SchoolCatalogBO;
import com.classManage.tusdt.model.BO.SchoolInfoListBO;
import com.classManage.tusdt.model.ClassInfo;
import com.classManage.tusdt.model.SchoolInfo;
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
 * Time: 15:57
 */
@Api(protocols = "http,https", tags = {"ClassManage"}, value = "/class_manage/class",description = "班级信息管理")
@RestController
@RequestMapping(value = "/class_manage/class")
public class ClassInfoController {

    @Resource
    private ClassInfoService classInfoService;

    @ApiOperation(value = "新增一个班级信息", notes = "增加班级")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addSchool(HttpServletRequest request,@RequestBody ClassInfo classInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        classInfo.setSchoolId(schoolId);
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classInfoService.addClass(classInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可添加班级信息");
        }
        return responseData;
    }

    @ApiOperation(value = "删除一个班级", notes = "删除班级")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeClass", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeClass(HttpServletRequest request,
                                             @RequestParam(value = "classId",required = true) Integer classId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classInfoService.removeClass(classId);
        } else {
            responseData.setError("权限不足，仅高校管理员可删除班级信息");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑班级资料", notes = "编辑班级资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyClassInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyClassInfo(HttpServletRequest request,@RequestBody ClassInfo classInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classInfoService.modifyClass(classInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可删除班级信息");
        }
        return responseData;
    }

    @ApiOperation(value = "获取班级资料列表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getClassList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassBaseInfoListBO>> getClassList(HttpServletRequest request,
                                                                 @RequestParam(value = "className",required = false) String className) {

        ResponseData<List<ClassBaseInfoListBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<ClassBaseInfoListBO> classBaseInfoList = classInfoService.getClassBaseInfo(className, schoolId);
        if(classBaseInfoList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",classBaseInfoList);
        return responseData;
    }
}
