package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.UserCountBO;
import com.classManage.tusdt.model.BO.UserListBO;
import com.classManage.tusdt.model.User;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 19:23
 */
public interface UserInfoService {

    ResponseData<String> addUser(User user);

    List<UserListBO> getUserList(Integer schoolId,String userName);

    ResponseData<String> removeUser(Integer userId);

    ResponseData<String> modifyUser(User user);

    ResponseData<String> agreeUserApply(Integer userId);

    ResponseData<UserCountBO> indexCount(Integer schoolId);
}
