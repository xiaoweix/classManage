package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.ClassUsingMapper;
import com.classManage.tusdt.dao.ClassroomInfoMapper;
import com.classManage.tusdt.model.BO.ClassUseBO;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.ClassroomApply;
import com.classManage.tusdt.model.ClassroomInfo;
import com.classManage.tusdt.service.ClassroomInfoService;
import com.classManage.tusdt.utils.CourseTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private ClassUsingMapper classUsingMapper;

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

    @Override
    public List<ClassUseBO> getClassUse(String startTime, String endTime, String startHour, String endHour, Integer status, Integer schoolId) {
        //获取所有的教室信息
        List<ClassUseBO> classUseBOList = classroomInfoMapper.getClassUseBySchoolId(schoolId);
        Map<Integer,Integer> startNum = CourseTimeUtils.generateStartTimeMap();
        Map<Integer,Integer> endNum = CourseTimeUtils.generateEndTimeMap();
        if (startTime != null) {
            String[] startYear = startTime.split("-");
            String[] endYear = endTime.split("-");
            String[] startHort = startHour.split(":");
            String[] endHort = endHour.split(":");
            Integer startMonth = Integer.valueOf(startYear[1]);
            Integer endMonth = Integer.valueOf(endYear[1]);
            Integer startDay = Integer.valueOf(startYear[2]);
            Integer endDay = Integer.valueOf(endYear[2]);
            Integer startCourse = startNum.get(Integer.valueOf(startHort[0]));
            Integer endCourse = endNum.get(Integer.valueOf(endHort[0]));
            if (endMonth > startMonth) {
                endDay+=30;
            }
            for (int j = 0; j < classUseBOList.size(); j++) {
                List<ClassUseBO> classUseBO = new ArrayList<>();
                for (int i = startDay; i < endDay; i++) {
                    Integer year = Integer.valueOf(startYear[0]);
                    Integer classroomId = classUseBOList.get(j).getClassroomId();
                    classUseBO = classUsingMapper.getByClassUse(year,startMonth, i, startCourse,endCourse,classroomId);
                    if (classUseBO.size() > 0) {
                        classUseBOList.get(j).setClassNum(classUseBO.get(0).getClassNum());
                        classUseBOList.get(j).setRemarks(classUseBO.get(0).getRemarks());
                        break;
                    } else {
                        classUseBOList.get(j).setClassNum(0);
                        classUseBOList.get(j).setRemarks("教室空闲");
                    }
                }

            }

        } else {
            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(new Date());
            int year = timeCal.get(Calendar.YEAR);
            int month = timeCal.get(Calendar.MONTH)+1;
            int day = timeCal.get(Calendar.DATE);
            for (int j = 0; j < classUseBOList.size(); j++) {
                List<ClassUseBO> classUseBO ;
                classUseBO = classUsingMapper.getByClassUse(year,month, day, 1,10,classUseBOList.get(j).getClassroomId());
                if (classUseBO.size() > 0) {
                    classUseBOList.get(j).setClassNum(classUseBO.get(0).getClassNum());
                    classUseBOList.get(j).setRemarks(classUseBO.get(0).getRemarks());
                    continue;
                }

                classUseBOList.get(j).setClassNum(0);
                classUseBOList.get(j).setRemarks("教室空闲");
            }
        }

        if (status != null) {
            for (int i = 0; i < classUseBOList.size(); i++) {
                if (status.equals(0) && !classUseBOList.get(i).getClassNum().equals(0)) {
                    classUseBOList.remove(i--);
                } else if (status.equals(1) && classUseBOList.get(i).getClassNum().equals(0)) {
                    classUseBOList.remove(i--);
                }
            }
        }
        return classUseBOList;
    }

    public static void main(String[] args) {
        String time = "2020-05-12";
        String[] times = time.split("-");
        System.out.println(Integer.valueOf(times[0]));
        for (int i = 0; i < times.length; i++) {
            System.out.println(times[i]);
        }
    }
}
