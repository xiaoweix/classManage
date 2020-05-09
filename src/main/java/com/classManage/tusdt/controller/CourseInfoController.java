package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.CourseBaseInfoBO;
import com.classManage.tusdt.model.CourseInfo;
import com.classManage.tusdt.service.CourseInfoService;
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
@Api(protocols = "http,https", tags = {"CourseManage"}, value = "/class_manage/course",description = "课程信息管理")
@RestController
@RequestMapping(value = "/class_manage/course")
public class CourseInfoController {

    @Resource
    private CourseInfoService courseInfoService;

    @ApiOperation(value = "新增一个课程信息", notes = "增加课程")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addCourse(HttpServletRequest request, @RequestBody CourseInfo courseInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = courseInfoService.addCourse(courseInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可添加课程信息");
        }
        return responseData;
    }

    @ApiOperation(value = "删除课程", notes = "删除课程")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeCourse", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeCourse(HttpServletRequest request,
                                               @RequestParam(value = "courseId",required = true) Integer courseId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = courseInfoService.removeCourse(courseId);
        } else {
            responseData.setError("权限不足，仅高校管理员可删除课程信息");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑课程资料", notes = "编辑课程资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyCourse(HttpServletRequest request, @RequestBody CourseInfo courseInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = courseInfoService.modifyCourse(courseInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可修改课程信息");
        }
        return responseData;
    }

    @ApiOperation(value = "获取课程列表", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<CourseBaseInfoBO>> getCourseList(HttpServletRequest request,
                                                              @RequestParam(value = "courseName",required = false) String courseName) {

        ResponseData<List<CourseBaseInfoBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<CourseBaseInfoBO> courseList = courseInfoService.getCourseList(courseName, schoolId);
        if(courseList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        responseData.set("获取成功",courseList);
        return responseData;
    }
}
