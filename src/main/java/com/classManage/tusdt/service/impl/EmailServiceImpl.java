package com.classManage.tusdt.service.impl;


import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.service.EmailService;
import com.classManage.tusdt.utils.MailUtil;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 14:53
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Integer code;

    private String checkEmail;
    @Override
    public ResponseData<String> sendEmail(String email) {
        ResponseData<String> responseData = new ResponseData<>();
        //（数据类型)(最小值+Math.random()*(最大值-最小值+1))
        code = (int)(100000+Math.random()*(999999-1+1));
        checkEmail = email;
        MailUtil.sendMail(email,code.toString());
        responseData.setOK("发送成功");
        return responseData;
    }

    @Override
    public ResponseData<String> checkEmailCode(String email, Integer emailCode) {
        ResponseData<String> responseData = new ResponseData<>();
        if(!email.equals(checkEmail)) {
            responseData.setError("邮箱更改，验证失败！");
            return responseData;
        }
        if(!emailCode.equals(code)) {
            responseData.setError("验证码错误！");
            return responseData;
        }
        responseData.setOK("验证成功");
        return responseData;
    }
}
