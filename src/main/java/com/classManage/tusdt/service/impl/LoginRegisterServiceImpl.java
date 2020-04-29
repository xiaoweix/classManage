package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.UserMapper;
import com.classManage.tusdt.model.BO.UserLoginBO;
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
    public ResponseData<UserLoginBO> login(String email, String password) {
        ResponseData<UserLoginBO> responseData = new ResponseData<>();
        //返回给前端的对象
        UserLoginBO userLoginBO = new UserLoginBO();
        //先将密码 hash加密
        String hashPassword = HashUtils.hashEncrypt(password, CommonConstant.PASSWORD_HASH);
        //根据用户登录的email获取数据库中的user对象
        User user = userMapper.loginByEmail(email);
        //判断账号的状态是否正常
        if(user.getStatus().equals(CommonConstant.USER_STATUS_ABNORMAL) || user.getStatus().equals(CommonConstant.USER_STATUS_UNAUDITED)) {
            responseData.setError("账号冻结或未通关审核 请联系管理员");
            return responseData;
        }
        //判断密码正确性
        if(hashPassword.equals(user.getPassword())) {
            userLoginBO.setCode(user.getJobLevel().toString());
            userLoginBO.setToken(JwtUtils.geneJsonWebToken(user));
        } else {
            responseData.setError("密码错误");
            return responseData;
        }
        responseData.setOK("登陆成功",userLoginBO);
        return responseData;
    }

    @Override
    public ResponseData<String> register(User user) {
        ResponseData<String> responseData = new ResponseData<>();

        //根据邮箱去检查是否已存在该用户
        User checkUserEmail = userMapper.loginByEmail(user.getEmail());
        //判断是否存在
        if(checkUserEmail != null) {
            responseData.setError("该用户邮箱已存在！");
            return responseData;
        }
        //设置基本属性非删除状态
        user.setIsDelete(CommonConstant.DELETED_NO);
        //未审核状态
        user.setStatus(CommonConstant.USER_STATUS_UNAUDITED);
        //设置加密密码
        user.setPassword(HashUtils.hashEncrypt(user.getPassword(),CommonConstant.PASSWORD_HASH));
        //设置创建时间
        user.setCreateTime(new Date());
        //插入数据库
        Integer result = userMapper.insert(user);
        if (result == 0) {
            responseData.setError("注册失败");
            return responseData;
        }
        responseData.setOK("注册成功");
        return responseData;
    }
}
