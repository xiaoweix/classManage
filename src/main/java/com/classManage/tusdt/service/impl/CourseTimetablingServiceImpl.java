package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.CourseInfoMapper;
import com.classManage.tusdt.dao.CourseTimetablingMapper;
import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.model.CourseTimetabling;
import com.classManage.tusdt.service.CourseTimetablingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-12
 * Time: 15:46
 */
@Service
public class CourseTimetablingServiceImpl implements CourseTimetablingService {

    @Autowired
    private CourseTimetablingMapper courseTimetablingMapper;

    @Override
    public ResponseData<String> addCoursePlan(CourseTimetabling courseTimetabling) {
        ResponseData<String> responseData = new ResponseData<>();

        courseTimetabling.setIsDelete(CommonConstant.DELETED_NO);
        courseTimetablingMapper.insert(courseTimetabling);
        responseData.setOK("添加成功！");
        return responseData;
    }

    @Override
    public List<CoursePlanListBO> getCoursePlan(Integer schoolId, String courseName) {

        return courseTimetablingMapper.getCoursePlan(schoolId, courseName);
    }
}
