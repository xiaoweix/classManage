package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.UserMapper;
import com.classManage.tusdt.model.BO.IndexBO;
import com.classManage.tusdt.model.BO.UserCountBO;
import com.classManage.tusdt.model.BO.UserListBO;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.UserInfoService;
import com.classManage.tusdt.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<UserListBO> getUserList(Integer schoolId,String userName) {

        return userMapper.selectUserByName(schoolId, userName);
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

    @Override
    public ResponseData<UserCountBO> indexCount(Integer schoolId) {
        ResponseData<UserCountBO> responseData= new ResponseData<>();
        UserCountBO userCountBO = new UserCountBO();
        userCountBO.setAdmin(userMapper.countAdmin());
        userCountBO.setStudent(userMapper.countStu());
        userCountBO.setTeacher(userMapper.countTea());

        Date date = new Date();
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(date);
        int year = timeCal.get(Calendar.YEAR);
        int month = timeCal.get(Calendar.MONTH)+1;
        int day = timeCal.get(Calendar.DATE);

        String tempSting = String.format("%d-%d-%d",year,month,day-6);
        IndexBO indexBO1 = new IndexBO(0,tempSting,7);
        tempSting = String.format("%d-%d-%d",year,month,day-5);
        IndexBO indexBO2 = new IndexBO(1,tempSting,11);
        tempSting = String.format("%d-%d-%d",year,month,day-4);
        IndexBO indexBO3 = new IndexBO(2,tempSting,9);
        tempSting = String.format("%d-%d-%d",year,month,day-3);
        IndexBO indexBO4 = new IndexBO(3,tempSting,13);
        tempSting = String.format("%d-%d-%d",year,month,day-2);
        IndexBO indexBO5 = new IndexBO(4,tempSting,4);
        tempSting = String.format("%d-%d-%d",year,month,day-1);
        IndexBO indexBO6 = new IndexBO(5,tempSting,17);
        List<IndexBO> indexBOList = new ArrayList<>();
        indexBOList.add(indexBO1);
        indexBOList.add(indexBO2);
        indexBOList.add(indexBO3);
        indexBOList.add(indexBO4);
        indexBOList.add(indexBO5);
        indexBOList.add(indexBO6);
        userCountBO.setAddUserList(indexBOList);
        responseData.setOK("获取成功",userCountBO);
        return responseData;
    }
}
