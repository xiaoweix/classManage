package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.UserLoginBO;
import com.classManage.tusdt.model.User;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-14
 * Time: 15:17
 */
public interface LoginRegisterService {

    ResponseData<UserLoginBO> login(String email, String password);

    ResponseData<String> register(User user);
}
