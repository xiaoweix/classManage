package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.model.CourseTimetabling;
import com.classManage.tusdt.service.CourseTimetablingService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-12
 * Time: 15:33
 */
@Api(protocols = "http,https", tags = {"courseManage"}, value = "/class_manage/courseManage",description = "教学规划信息管理")
@RestController
@RequestMapping(value = "/class_manage/courseManage")
public class CourseTimetablingController {


    @Resource
    private CourseTimetablingService courseTimetablingService;


    @ApiOperation(value = "提交教学规划", notes = "增加课程")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addCoursePlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addCoursePlan(@RequestBody CourseTimetabling courseTimetabling) {

        ResponseData<String> responseData;
        responseData = courseTimetablingService.addCoursePlan(courseTimetabling);
        return responseData;
    }

    @ApiOperation(value = "获取课程列表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminCoursePlanList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<CoursePlanListBO>> adminCoursePlanList(HttpServletRequest request,
                                                              @RequestParam(value = "courseName",required = false) String courseName,
                                                              @RequestParam(value = "schoolId",required = true) Integer schoolId) {

        ResponseData<List<CoursePlanListBO>> responseData = new ResponseData<>();
        List<CoursePlanListBO> courseList = courseTimetablingService.getCoursePlan(schoolId, courseName);
        if(courseList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",courseList);
        return responseData;
    }
}
