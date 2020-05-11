package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.OwnClassroomApplyListBO;
import com.classManage.tusdt.model.ClassroomApply;
import com.classManage.tusdt.model.ClassroomInfo;
import com.classManage.tusdt.service.ClassroomApplyService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 15:53
 */
@Api(protocols = "http,https", tags = {"classroomApplyManage"}, value = "/class_manage/classroomApply",description = "教室资源申请管理")
@RestController
@RequestMapping(value = "/class_manage/classroomApply")
public class ClassroomApplyController {

    @Resource
    private ClassroomApplyService classroomApplyService;

    @ApiOperation(value = "获取单时间教室列表", notes = "多时间通用")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/singleTimeRoom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassroomNameBO>> singleTimeRoom(HttpServletRequest request,
                                                              @RequestParam(value = "stuNum",required = true) Integer stuNum,
                                                              @RequestParam(value = "buildingId",required = false) Integer buildingId,
                                                              @RequestParam(value = "startTime",required = true) String startTime,
                                                              @RequestParam(value = "endTime",required = true) String endTime) {

        ResponseData<List<ClassroomNameBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        responseData = classroomApplyService.getSingleTimeRoom(startTime,endTime,schoolId,stuNum,buildingId);
        return responseData;
    }

    @ApiOperation(value = "提交教室申请单（单时间、多时间通用）", notes = "提交申请单")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/submitApply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> submitApply(HttpServletRequest request, @RequestBody ClassroomApply classroomApply) {

        ResponseData<String> responseData;
        Integer userId = (Integer) request.getAttribute("userId");
        classroomApply.setUserId(userId);
        responseData = classroomApplyService.submitApply(classroomApply);
        return responseData;
    }

    @ApiOperation(value = "获取自己的申请列表（老师和学生）", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getApplyClassroom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<OwnClassroomApplyListBO>> getApplyClassroom(HttpServletRequest request) {

        ResponseData<List<OwnClassroomApplyListBO>> responseData = new ResponseData<>();
        Integer userId = (Integer) request.getAttribute("userId");
        List<OwnClassroomApplyListBO> userList = classroomApplyService.getOwnApplyClassroom(userId);
        if(userList == null || userList.size() == 0) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",userList);
        return responseData;
    }



    @ApiOperation(value = "获取申请列表（管理员界面）", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminApplyClassroom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<ClassroomApplyListBO>> adminApplyClassroom(HttpServletRequest request,
                                                                        @RequestParam(value = "result",required = false) Integer result) {

        ResponseData<List<ClassroomApplyListBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<ClassroomApplyListBO> classroomApplyListBOList = classroomApplyService.getRoomApplyListAdmin(result,schoolId);
        if(classroomApplyListBOList == null || classroomApplyListBOList.size() == 0) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",classroomApplyListBOList);
        return responseData;
    }


    @ApiOperation(value = "同意教室申请", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminAgreeClassroomApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> adminAgreeClassroomApply(HttpServletRequest request,
                                                                         @RequestParam(value = "applyId",required = true) Integer applyId) {

        return classroomApplyService.agreeClassroomApply(applyId);
    }


    @ApiOperation(value = "驳回教室申请", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/adminDisagreeClassroomApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> adminDisagreeClassroomApply(HttpServletRequest request,
                                                         @RequestParam(value = "applyId",required = true) Integer applyId) {

        return classroomApplyService.disagreeClassroomApply(applyId);
    }

}
