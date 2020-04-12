package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.ClassInfoMapper;
import com.classManage.tusdt.model.BO.ClassBaseInfoListBO;
import com.classManage.tusdt.model.ClassInfo;
import com.classManage.tusdt.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 16:03
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public ResponseData<String> addClass(ClassInfo classInfo) {
        ResponseData<String> responseData = new ResponseData<>();

        if(checkClassName(classInfo.getSchoolId(),classInfo.getClassName())) {
            responseData.setError("班名重复");
            return responseData;
        }
        classInfo.setIsDelete(CommonConstant.DELETED_NO);

        Integer result = classInfoMapper.insert(classInfo);
        if (result == 0) {
            responseData.setError("添加失败");
            return responseData;
        }
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyClass(ClassInfo classInfo) {

        ResponseData<String> responseData = new ResponseData<>();

        if(checkClassName(classInfo.getSchoolId(),classInfo.getClassName())) {
            responseData.setError("班名重复");
            return responseData;
        }

        Integer result = classInfoMapper.updateByPrimaryKeySelective(classInfo);
        if (result == 0) {
            responseData.setError("修改失败");
            return responseData;
        }
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> removeClass(Integer classId) {

        ResponseData<String> responseData = new ResponseData<>();

        //TODO 检查班级有没有人
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(classId);
        if(classInfo == null) {
            responseData.setError("班级不存在");
        }
        classInfo.setIsDelete(CommonConstant.DELETED_YES);
        Integer result = classInfoMapper.updateByPrimaryKeySelective(classInfo);
        if (result == 0) {
            responseData.setError("删除失败");
            return responseData;
        }
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public List<ClassBaseInfoListBO> getClassBaseInfo(String className, Integer schoolId) {

        return classInfoMapper.getClassInfoList(className, schoolId);
    }

    @Override
    public boolean checkClassName(Integer schoolId, String className) {
        List<ClassInfo> classInfoList = classInfoMapper.checkClassName(schoolId,className);
        return classInfoList != null && !classInfoList.isEmpty();
    }
}
