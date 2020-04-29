package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.UserMapper;
import com.classManage.tusdt.model.BO.UserListBO;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.UserInfoService;
import com.classManage.tusdt.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 19:29
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseData<String> addUser(User user) {
        ResponseData<String> responseData = new ResponseData<>();
        User checkUserEmail = userMapper.loginByEmail(user.getEmail());

        if(checkUserEmail != null) {
            responseData.setError("该用户邮箱已存在！");
            return responseData;
        }
        user.setIsDelete(CommonConstant.DELETED_NO);
        user.setCreateTime(new Date());
        String password = user.getPassword();
        user.setPassword(HashUtils.hashEncrypt(password,"SHA-256"));
        userMapper.insert(user);
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public List<UserListBO> getUserList(String userName) {

        return userMapper.selectUserByName(userName);
    }

    @Override
    public ResponseData<String> removeUser(Integer userId) {
        ResponseData<String> responseData = new ResponseData<>();
        //todo 删除用户的时候需要判断是都有担任的课程和借用教室未使用的情况。
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null) {
            responseData.setError("删除失败，未查找到该用户");
        }
        user.setIsDelete(CommonConstant.DELETED_YES);
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyUser(User user) {
        ResponseData<String> responseData = new ResponseData<>();
        userMapper.updateByPrimaryKeySelective(user);
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> agreeUserApply(Integer userId) {
        ResponseData<String> responseData = new ResponseData<>();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(CommonConstant.USER_STATUS_NORMAL);
        userMapper.updateByPrimaryKeySelective(user);
        responseData.setOK("操作成功！");
        return responseData;
    }
}
