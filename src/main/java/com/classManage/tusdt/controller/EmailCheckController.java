package com.classManage.tusdt.controller;


import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 14:48
 */
@Api(protocols = "http,https", tags = {"email"}, value = "/email",description = "邮箱接口")
@RestController
@RequestMapping(value = "/email")
public class EmailCheckController {

    @Resource
    private EmailService emailService;

    @ApiOperation(value = "发送邮件", notes = "验证邮件")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "发送成功"),})
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> sendEmail(@RequestParam(value = "email",required = true) String email) {


        return emailService.sendEmail(email);
    }

    @ApiOperation(value = "验证邮箱验证码", notes = "验证邮箱验证码")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "验证成功"),})
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> checkEmail(@RequestParam(value = "email",required = true) String email,
                                           @RequestParam(value = "code",required = true) Integer code) {


        return emailService.checkEmailCode(email,code);
    }

}

