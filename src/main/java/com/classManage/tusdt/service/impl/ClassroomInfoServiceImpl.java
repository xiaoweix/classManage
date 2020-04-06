package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.ClassroomInfoMapper;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.ClassApply;
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

        return null;
    }

    @Override
    public ResponseData<String> removeClassRoom(Integer classId) {
        ResponseData<String> responseData = new ResponseData<>();

        return null;
    }

    @Override
    public List<ClassroomNameBO> getAllClassroomName() {
        return null;
    }

    @Override
    public List<ClassroomNameBO> getSingleTimeRoom(String dateFrom, String dateEnd, Integer stuNum, Integer buildId) {
        return null;
    }

    @Override
    public List<ClassroomNameBO> getMultiTimeRoom(List<DateStartAndEndBO> dateStartAndEndBOList) {
        return null;
    }

    @Override
    public ResponseData<String> applyClassroomForSingleTime(ClassApply classApply) {
        ResponseData<String> responseData = new ResponseData<>();

        return null;
    }

    @Override
    public List<ClassroomApplyListBO> getRoomApplyList() {
        return null;
    }

    @Override
    public List<ClassroomApplyListBO> getRoomApplyList(Integer userId) {
        return null;
    }
}
