package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.ClassroomInfo;
import com.classManage.tusdt.service.ClassroomInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public ResponseData<String> addClassroom(@RequestBody ClassroomInfo classroomInfo) {

        ResponseData<String> responseData;
        responseData = classroomInfoService.addClassroom(classroomInfo);
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
        responseData = classroomInfoService.removeClassRoom(roomId);
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
    public ResponseData<String> modifyRoomInfo(@RequestBody ClassroomInfo classroomInfo) {

        ResponseData<String> responseData;
        responseData = classroomInfoService.modifyClassroom(classroomInfo);
        return responseData;
    }

}
