package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.ClassUseBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.ClassroomInfo;
import com.classManage.tusdt.service.ClassroomInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 20:04
 */
@Api(protocols = "http,https", tags = {"ClassroomManage"}, value = "/class_manage/classroom",description = "教室信息管理")
@RestController
@RequestMapping(value = "/class_manage/classroom")
public class ClassroomInfoController {

    @Resource
    ClassroomInfoService classroomInfoService;

    @ApiOperation(value = "新增一个教室信息", notes = "增加教室")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addClassroom", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addClassroom(HttpServletRequest request,@RequestBody ClassroomInfo classroomInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        classroomInfo.setSchoolId(schoolId);
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classroomInfoService.addClassroom(classroomInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可添加教室");
        }
        return responseData;

    }

    @ApiOperation(value = "删除一间教室", notes = "删除教室")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeClassroom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeClassroom(HttpServletRequest request,
                                           @RequestParam(value = "roomId",required = true) Integer roomId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classroomInfoService.removeClassRoom(roomId);
        } else {
            responseData.setError("权限不足，仅高校管理员可删除教室");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑教室资料", notes = "编辑教室资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyRoomInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyRoomInfo(HttpServletRequest request, @RequestBody ClassroomInfo classroomInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData = classroomInfoService.modifyClassroom(classroomInfo);
        } else {
            responseData.setError("权限不足，仅高校管理员可编辑教室");
        }
        return responseData;
    }
    @ApiOperation(value = "获取教室列表", notes = "多时间通用")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/classroomList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassroomInfo>> singleTimeRoom(HttpServletRequest request) {

        ResponseData<List<ClassroomInfo>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<ClassroomInfo> classroomInfoList = classroomInfoService.getAllClassroomName(schoolId);
        responseData.setOK("获取成功",classroomInfoList);
        return responseData;
    }

    @ApiOperation(value = "查看教室使用情况", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/classroomUseList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassUseBO>> classroomUseList(HttpServletRequest request,
                                                              @RequestParam(value = "startTime",required = false) String startTime,
                                                              @RequestParam(value = "endTime",required = false) String endTime,
                                                              @RequestParam(value = "startHour",required = false) String startHour,
                                                              @RequestParam(value = "endHour",required = false) String endHour,
                                                              @RequestParam(value = "status",required = false) Integer status) {

        ResponseData<List<ClassUseBO>> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (!CommonConstant.USER_LEVEL_UNI_ADMIN.equals(level)) {
            responseData.setError("权限不足，仅高校管理员查看教室使用列表");
            return responseData;
        }
        Integer schoolId = (Integer) request.getAttribute("schoolId"); //
        List<ClassUseBO> classroomInfoList = classroomInfoService.getClassUse(startTime,startHour,endTime,endHour,status,schoolId);
        responseData.setOK("获取成功",classroomInfoList);
        return responseData;
    }

}
