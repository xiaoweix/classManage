package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.CourseInfoMapper;
import com.classManage.tusdt.model.BO.CourseBaseInfoBO;
import com.classManage.tusdt.model.CourseInfo;
import com.classManage.tusdt.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 14:52
 */
@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public ResponseData<String> addCourse(CourseInfo courseInfo) {

        ResponseData<String> responseData = new ResponseData<>();

        if (checkCourseName(courseInfo.getCourseName())) {
            responseData.setError("该课程已存在");
            return responseData;
        }

        courseInfo.setCreateTime(new Date());
        courseInfo.setIsDelete(CommonConstant.DELETED_NO);
        courseInfoMapper.insert(courseInfo);
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyCourse(CourseInfo courseInfo) {
        ResponseData<String> responseData = new ResponseData<>();
        if (checkCourseName(courseInfo.getCourseName())) {
            responseData.setError("该课程已存在");
            return responseData;
        }
        courseInfoMapper.updateByPrimaryKeySelective(courseInfo);
        responseData.setOK("修改成功");

        return responseData;
    }

    @Override
    public ResponseData<String> removeCourse(Integer courseId) {
        ResponseData<String> responseData = new ResponseData<>();
        CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(courseId);
        if(courseInfo == null) {
            responseData.setError("该课程不存在");
        }
        //TODO 判断该课程是否在教授
        courseInfo.setIsDelete(CommonConstant.DELETED_YES);
        courseInfoMapper.updateByPrimaryKeySelective(courseInfo);
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public List<CourseBaseInfoBO> getCourseList(String courseName, Integer schoolId) {
        return courseInfoMapper.getCourseBaseInfo(courseName, schoolId);
    }

    @Override
    public boolean checkCourseName(String courseName) {
        List<CourseInfo> courseInfoList = courseInfoMapper.getCourseListByName(courseName);
        return courseInfoList !=null && !courseInfoList.isEmpty();
    }
}
