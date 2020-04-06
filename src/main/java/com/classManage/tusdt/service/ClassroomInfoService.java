package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.ClassApply;
import com.classManage.tusdt.model.ClassInfo;
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

    List<ClassroomNameBO> getAllClassroomName();

    /**
     * 通过单时间段筛查教室
     * @param dateFrom
     * @param dateEnd
     * @param stuNum
     * @param buildId
     * @return
     */
    List<ClassroomNameBO> getSingleTimeRoom(String dateFrom, String dateEnd, Integer stuNum, Integer buildId);

    List<ClassroomNameBO> getMultiTimeRoom(List<DateStartAndEndBO> dateStartAndEndBOList);

    /**
     * 单时间教室申请提交
     * @param classApply
     * @return
     */
    ResponseData<String> applyClassroomForSingleTime(ClassApply classApply);

    /**
     * 获取教室预约所有信息List
     * @return
     */
    List<ClassroomApplyListBO> getRoomApplyList();

    /**
     * 获取自己个人 教室预约信息List
     * @return
     */
    List<ClassroomApplyListBO> getRoomApplyList(Integer userId);

}
