package com.classManage.tusdt.controller;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.LoginRegisterService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-13
 * Time: 18:04
 */
@Api(protocols = "http,https", tags = {"Login"}, value = "/login",description = "登录和注册")
@RestController
public class LoginRegisterController {

    @Resource
    private LoginRegisterService loginRegisterService;

    @ApiOperation(value = "登录接口", notes = "登录成功")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "登陆成功"),})
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String removeUser(HttpServletRequest request,
                                           @RequestParam(value = "email",required = true) String email,
                                           @RequestParam(value = "password",required = true) String password
                                            ) {

        String token = loginRegisterService.login(email,password);

        return token;
    }

    @ApiOperation(value = "注册", notes = "注册")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "注册成功"),})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> register(@RequestBody User user) {
        ResponseData<String> responseData = loginRegisterService.register(user);
        return responseData;
    }

}
