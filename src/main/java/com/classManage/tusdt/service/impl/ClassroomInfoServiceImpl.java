package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.ClassroomInfoMapper;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.ClassroomApply;
import com.classManage.tusdt.model.ClassroomInfo;
import com.classManage.tusdt.service.ClassroomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-05
 * Time: 15:32
 */
@Service
public class ClassroomInfoServiceImpl implements ClassroomInfoService {

    @Autowired
    private ClassroomInfoMapper classroomInfoMapper;

    @Override
    public ResponseData<String> addClassroom(ClassroomInfo classroomInfo) {
        ResponseData<String> responseData = new ResponseData<>();
        classroomInfo.setIsDelete(CommonConstant.DELETED_NO);
        classroomInfoMapper.insert(classroomInfo);
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyClassroom(ClassroomInfo classroomInfo) {
        ResponseData<String> responseData = new ResponseData<>();
        classroomInfoMapper.updateByPrimaryKeySelective(classroomInfo);
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> removeClassRoom(Integer classId) {
        ResponseData<String> responseData = new ResponseData<>();
        ClassroomInfo classroomInfo = classroomInfoMapper.selectByPrimaryKey(classId);
        classroomInfo.setIsDelete(CommonConstant.DELETED_YES);
        classroomInfoMapper.updateByPrimaryKeySelective(classroomInfo);
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public List<ClassroomInfo> getAllClassroomName( Integer schoolId) {
        return classroomInfoMapper.getBySchoolID(schoolId);
    }

}
