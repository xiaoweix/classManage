package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.BO.ClassSchedule;
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
                                                              @RequestParam(value = "courseName",required = false) String courseName) {

        ResponseData<List<CoursePlanListBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<CoursePlanListBO> courseList = courseTimetablingService.getCoursePlan(schoolId, courseName);
        if(courseList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",courseList);
        return responseData;
    }
    @ApiOperation(value = "同意排课安排", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminAgreeCourseApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> adminAgreeCourseApply(HttpServletRequest request,
                                                         @RequestParam(value = "courseTimetablingId",required = true) Integer courseTimetablingId) {

        return courseTimetablingService.dealCoursePlan(courseTimetablingId);
    }


    @ApiOperation(value = "驳回排课安排", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminDisagreeCourseApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> adminDisagreeCourseApply(HttpServletRequest request,
                                                            @RequestParam(value = "courseTimetablingId",required = true) Integer courseTimetablingId) {

        return courseTimetablingService.disagreeCoursePlan(courseTimetablingId);
    }

    @ApiOperation(value = "获取课程表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassSchedule>> getSchedule(HttpServletRequest request,@RequestParam(value = "teacherId",required = false) Integer teacherId) {
        ResponseData<List<ClassSchedule>> responseData = new ResponseData<>();
        Integer userId = (Integer) request.getAttribute("userId");
        List<ClassSchedule> classScheduleList;
        if(teacherId != null) {
            classScheduleList = courseTimetablingService.getSchedule(teacherId);
        } else {
            classScheduleList = courseTimetablingService.getSchedule(userId);
        }
        if(classScheduleList == null) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.setOK("获取成功",classScheduleList);
        return responseData;
    }
}
