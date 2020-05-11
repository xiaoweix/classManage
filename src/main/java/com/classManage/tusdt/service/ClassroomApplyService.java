package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.BO.OwnClassroomApplyListBO;
import com.classManage.tusdt.model.ClassroomApply;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 15:50
 */
public interface ClassroomApplyService {


    /**
     * 通过单时间段筛查教室
     * @param dateFrom
     * @param dateEnd
     * @param stuNum
     * @param buildId
     * @return
     */
    ResponseData<List<ClassroomNameBO>> getSingleTimeRoom(String dateFrom, String dateEnd, Integer schoolId, Integer stuNum, Integer buildId);

    List<ClassroomNameBO> getMultiTimeRoom(List<DateStartAndEndBO> dateStartAndEndBOList);

    /**
     * 单时间教室申请提交
     * @param classroomApply
     * @return
     */
    ResponseData<String> submitApply(ClassroomApply classroomApply);

    /**
     * 获取教室预约所有信息List
     * @return
     */
    List<ClassroomApplyListBO> getRoomApplyListAdmin(Integer result,Integer schoolId);

    /**
     * 获取自己个人 教室预约信息List
     * @return
     */
    List<OwnClassroomApplyListBO> getOwnApplyClassroom(Integer userId);


    ResponseData<String> agreeClassroomApply(Integer applyId);

    ResponseData<String> disagreeClassroomApply(Integer applyId);

}
