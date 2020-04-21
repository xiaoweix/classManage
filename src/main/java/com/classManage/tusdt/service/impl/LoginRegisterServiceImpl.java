package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.UserMapper;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.LoginRegisterService;
import com.classManage.tusdt.utils.HashUtils;
import com.classManage.tusdt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-14
 * Time: 15:18
 */
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String email, String password) {
        String hashPassword = HashUtils.hashEncrypt(password, CommonConstant.PASSWORD_HASH);
        User user = userMapper.loginByEmail(email);
        String token = null;
        if(hashPassword.equals(user.getPassword())) {
            token = JwtUtils.geneJsonWebToken(user);
        }
        return token;
    }

    @Override
    public ResponseData<String> register(User user) {
        ResponseData<String> responseData = new ResponseData<>();

        User checkUserEmail = userMapper.loginByEmail(user.getEmail());

        if(checkUserEmail != null) {
            responseData.setError("该用户邮箱已存在！");
            return responseData;
        }
        user.setIsDelete(CommonConstant.DELETED_NO);
        user.setStatus(CommonConstant.USER_STATUS_UNAUDITED);
        user.setPassword(HashUtils.hashEncrypt(user.getPassword(),CommonConstant.PASSWORD_HASH));
        user.setCreateTime(new Date());
        Integer result = userMapper.insert(user);
        if (result == 0) {
            responseData.setError("注册失败");
            return responseData;
        }
        responseData.setOK("注册成功");
        return responseData;
    }
}
