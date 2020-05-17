package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.ClassUseBO;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.ClassroomApply;
import com.classManage.tusdt.model.ClassroomInfo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 20:05
 */
public interface ClassroomInfoService {

    ResponseData<String> addClassroom(ClassroomInfo classroomInfo);

    ResponseData<String> modifyClassroom(ClassroomInfo classroomInfo);

    ResponseData<String> removeClassRoom(Integer classId);

    List<ClassroomInfo> getAllClassroomName(Integer schoolId);

    List<ClassUseBO> getClassUse(String startTime, String endTime, String startHour, String endHour, Integer status,Integer schoolId);

}
