package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.ClassUsingMapper;
import com.classManage.tusdt.dao.ClassroomApplyMapper;
import com.classManage.tusdt.model.BO.ClassroomApplyListBO;
import com.classManage.tusdt.model.BO.ClassroomNameBO;
import com.classManage.tusdt.model.BO.DateStartAndEndBO;
import com.classManage.tusdt.model.BO.OwnClassroomApplyListBO;
import com.classManage.tusdt.model.ClassUsing;
import com.classManage.tusdt.model.ClassroomApply;
import com.classManage.tusdt.service.ClassroomApplyService;
import com.classManage.tusdt.utils.CourseTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 15:51
 */
@Service
public class ClassroomApplyServiceImpl implements ClassroomApplyService {

    @Autowired
    private ClassroomApplyMapper classroomApplyMapper;

    @Autowired
    private ClassUsingMapper classUsingMapper;

    @Override
    public ResponseData<List<ClassroomNameBO>> getSingleTimeRoom(String dateFrom, String dateEnd,Integer schoolId, Integer stuNum, Integer buildId) {
        ResponseData<List<ClassroomNameBO>> responseData  = new ResponseData<>();
        Calendar timeCal = Calendar.getInstance();
        Date startTime = null;
        Date endTime = null;
        try{
            //将时间格式化
            startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFrom);
            endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Integer,Integer> startNum = CourseTimeUtils.generateStartTimeMap();
        Map<Integer,Integer> endNum = CourseTimeUtils.generateEndTimeMap();
        timeCal.setTime(startTime);
        //获取到年份
        Integer year = timeCal.get(Calendar.YEAR);
        //获取月份
        Integer month = timeCal.get(Calendar.MONTH) + 1;
        //获取天
        Integer day = timeCal.get(Calendar.DATE);
        //获取开始的小时
        Integer startCourse = timeCal.get(Calendar.HOUR);
        int startCourseNum = startNum.get(startCourse);

        timeCal.setTime(endTime);
        //获取结束的小时
        Integer endCourse = timeCal.get(Calendar.HOUR);
        int endCourseNum = endNum.get(endCourse);

        if(endCourseNum < startCourseNum) {
            responseData.setError("开始时间大于结束时间");
            return responseData;
        }
        List<ClassroomNameBO> classroomNameBOList = classUsingMapper.selectByTime(schoolId,buildId,year,month,day,startCourse,endCourse,stuNum);
        responseData.setOK("获取成功",classroomNameBOList);
        return responseData;
    }

    @Override
    public List<ClassroomNameBO> getMultiTimeRoom(List<DateStartAndEndBO> dateStartAndEndBOList) {
        return null;
    }

    @Override
    public ResponseData<String> submitApply(ClassroomApply classroomApply) {
        ResponseData<String> responseData = new ResponseData<>();
        classroomApply.setApplyTime(new Date());
        classroomApply.setIsDelete(CommonConstant.DELETED_NO);
        //设置申请结果状态为等待审核
        classroomApply.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_WAIT);
        classroomApplyMapper.insert(classroomApply);
        responseData.setOK("提交成功！");
        return responseData;
    }

    @Override
    public List<ClassroomApplyListBO> getRoomApplyListAdmin(Integer result,Integer schoolId) {
        return classroomApplyMapper.getAdminApplyClassroom(result,schoolId);
    }

    @Override
    public List<OwnClassroomApplyListBO> getOwnApplyClassroom(Integer userId) {

        return classroomApplyMapper.getOwnApplyClassroom(userId);
    }

    @Override
    public ResponseData<String> agreeClassroomApply(Integer applyId) {
        ResponseData<String> responseData = new ResponseData<>();
        ClassroomApply classroomApply = classroomApplyMapper.selectByPrimaryKey(applyId);
        if(classroomApply == null) {
            responseData.setError("操作失败，申请不存在");
            return responseData;
        }
        classroomApply.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_YES);
        Map<Integer,Integer> startNum = CourseTimeUtils.generateStartTimeMap();
        Map<Integer,Integer> endNum = CourseTimeUtils.generateEndTimeMap();

        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(classroomApply.getStartTime());

        //将对应的时间（小时）化成课程节数。
        System.out.println(timeCal.get(Calendar.HOUR));
        timeCal.setTime(classroomApply.getEndTime());
        System.out.println(timeCal.get(Calendar.HOUR));

        int startCourse = startNum.get(timeCal.get(Calendar.HOUR));
        int endCourse = endNum.get(timeCal.get(Calendar.HOUR));
        if(startCourse > endCourse) {
            responseData.setError("时间设置出错");
            return responseData;
        }
        for (int i = startCourse; i <= endCourse ; i++) {
            ClassUsing classUsing = new ClassUsing();
            Date startTime = classroomApply.getStartTime();
            Calendar startTimeCal = Calendar.getInstance();
            startTimeCal.setTime(startTime);
            //设置教室使用的基本信息
            classUsing.setRoomId(classroomApply.getRoomId());
            classUsing.setYear(startTimeCal.get(Calendar.YEAR));
            classUsing.setMonth(startTimeCal.get(Calendar.MONTH)+1);
            classUsing.setDay(startTimeCal.get(Calendar.DATE));
            if(startTimeCal.get(Calendar.DAY_OF_WEEK)!=1) {
                classUsing.setWeek(startTimeCal.get(Calendar.DAY_OF_WEEK));
            } else {
                classUsing.setWeek(7);
            }
            classUsing.setClassNum(classroomApply.getUseNum());
            classUsing.setCourseId(CommonConstant.CLASSROOM_USING_LEND_COURSEID);
            classUsing.setUserId(classroomApply.getUserId());
            classUsing.setCourseTime(i);
            classUsing.setIsDelete(CommonConstant.DELETED_NO);
            classUsingMapper.insert(classUsing);
        }
        classroomApplyMapper.updateByPrimaryKeySelective(classroomApply);
        responseData.setOK("操作成功！");
        return responseData;
    }

    @Override
    public ResponseData<String> disagreeClassroomApply(Integer applyId) {
        ResponseData<String> responseData = new ResponseData<>();
        ClassroomApply classroomApply = classroomApplyMapper.selectByPrimaryKey(applyId);
        if(classroomApply == null) {
            responseData.setError("操作失败，申请不存在");
        }
        classroomApply.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_NO);
        classroomApplyMapper.updateByPrimaryKeySelective(classroomApply);
        responseData.setOK("操作成功！");
        return responseData;
    }

}
