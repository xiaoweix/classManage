package com.classManage.tusdt.controller;


import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.model.BO.UserCountBO;
import com.classManage.tusdt.model.BO.UserListBO;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 19:51
 */
@Api(protocols = "http,https", tags = {"UserManage"}, value = "/class_manage/user",description = "用户信息管理")
@RestController
@RequestMapping(value = "/class_manage/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "获取用户列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<UserListBO>> getUserList(HttpServletRequest request,
                                                      @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                      @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                      @RequestParam(value = "userName",required = false) String userName) {

        ResponseData<List<UserListBO>> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        List<UserListBO> userList = userInfoService.getUserList(schoolId, userName);
        if(userList == null || userList.size() == 0) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",userList);
        return responseData;
    }


    @ApiOperation(value = "新增一个用户", notes = "增加用户")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addUser(HttpServletRequest request, @RequestBody User user) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer schoolId = (Integer) request.getAttribute("schoolId");
        user.setSchoolId(schoolId);
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_TEA < level) {
            responseData = userInfoService.addUser(user);
        } else {
            responseData.setError("权限不足，仅管理员可添加用户信息");
        }
        return responseData;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeUser(HttpServletRequest request,
                                           @RequestParam(value = "userId",required = true) Integer userId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_TEA < level) {
            responseData = userInfoService.removeUser(userId);
        } else {
            responseData.setError("权限不足，仅管理员可删除用户信息");
        }
        return responseData;
    }

    @ApiOperation(value = "编辑用户资料", notes = "编辑用户资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyUser(HttpServletRequest request,@RequestBody User user) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_TEA < level) {
            responseData = userInfoService.modifyUser(user);
        } else {
            responseData.setError("权限不足，仅管理员可修改用户信息");
        }
        return responseData;
    }

    @ApiOperation(value = "同意用户的申请", notes = "同意用户的申请")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "操作成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/agreeUserApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> agreeUserApply(HttpServletRequest request,
                                           @RequestParam(value = "userId",required = true) Integer userId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer level = (Integer) request.getAttribute("level");
        if (CommonConstant.USER_LEVEL_TEA < level) {
            responseData = userInfoService.agreeUserApply(userId);
        } else {
            responseData.setError("权限不足，仅管理员可修改用户信息");
        }
        return responseData;
    }

    @ApiOperation(value = "获取首页数据", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<UserCountBO> index(HttpServletRequest request) {

        Integer schoolId = (Integer) request.getAttribute("schoolId");
        return userInfoService.indexCount(schoolId);
    }
}

