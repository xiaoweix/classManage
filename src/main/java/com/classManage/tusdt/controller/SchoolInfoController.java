package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
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
    public ResponseData<String> addSchool(@RequestBody SchoolInfo schoolInfo) {

        ResponseData<String> responseData;
        responseData = schoolInfoService.addSchool(schoolInfo);
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

        ResponseData<String> responseData = schoolInfoService.removeSchool(schoolID);
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
    public ResponseData<String> modifySchoolInfo(@RequestBody SchoolInfo schoolInfo) {

        ResponseData<String> responseData;
        responseData = schoolInfoService.modifySchool(schoolInfo);
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
