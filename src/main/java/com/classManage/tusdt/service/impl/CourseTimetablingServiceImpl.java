package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.*;
import com.classManage.tusdt.model.*;
import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.service.CourseTimetablingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

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

    @Autowired
    private ClassroomInfoMapper classroomInfoMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private ClassUsingMapper classUsingMapper;

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public ResponseData<String> addCoursePlan(CourseTimetabling courseTimetabling) {
        ResponseData<String> responseData = new ResponseData<>();
        //设置教学计划是否删除
        courseTimetabling.setIsDelete(CommonConstant.DELETED_NO);
        //数据库插入教学计划
        courseTimetablingMapper.insert(courseTimetabling);
        responseData.setOK("添加成功！");
        return responseData;
    }

    @Override
    public List<CoursePlanListBO> getCoursePlan(Integer schoolId, String courseName) {

        return courseTimetablingMapper.getCoursePlan(schoolId, courseName);
    }

    @Override
    public ResponseData<String> dealCoursePlan(Integer courseTimetablingId) {
        ResponseData<String> responseData = new ResponseData<>();
        CourseTimetabling courseTimetabling = courseTimetablingMapper.selectByPrimaryKey(courseTimetablingId);
        CourseInfo courseInfo = courseInfoMapper.selectByPrimaryKey(courseTimetabling.getCourseId());
        // 排课默认是周一~周五 12节、34节、67节、89节。晚上的课和周末的课除了特殊要求之外，不安排
        // 排课一门课程不可能一周上完，所以一周安排3次课。
        Calendar timeCal = Calendar.getInstance();
        //课程开始时间
        timeCal.setTime(courseTimetabling.getStartTime());
        //当前是星期几
        int day0 = timeCal.get(Calendar.DAY_OF_WEEK);
        int distance = 7 - day0; //距离下周日还有distance天
        int year = timeCal.get(Calendar.YEAR);
        int month = timeCal.get(Calendar.MONTH)+1;
        int day = timeCal.get(Calendar.DATE)+distance; //算出距离最近的下一个星期一
        //将下周一的时间 转成日期字符串 yyyy-MM-dd HH:mm:ss
        String dataString = String.format("%s-%s-%s %s:%s:%s",year,month,day,"00","00","00");
        //获取整个学校的教室 根据课程的人数、学校的楼层查找
        int courseStudentNum = getStudentNum(courseTimetabling);
        List<ClassroomInfo> classroomInfoList = classroomInfoMapper.getClassroomBySchoolID(courseTimetabling.getSchoolId(),courseTimetabling.getBuildingLayer(),courseStudentNum);

        //表示三天上課星期数 随机数且不相等
        int []weekday = new int[4];
        int max=5;//星期五
        int min=1;//星期一
        for (int i=0; i<100; i++) {
            Random random = new Random();
            weekday[0] = random.nextInt(max)%(max-min+1) + min;
            weekday[1] = random.nextInt(max)%(max-min+1) + min;
            weekday[2] = random.nextInt(max)%(max-min+1) + min;
            if (weekday[0] != weekday[1] && weekday[1] != weekday[2] && weekday[0] != weekday[2]) {
                System.out.println(i);
                System.out.println(weekday[0] + " " + weekday[1] + " " + weekday[2]);
                break;
            }
        }
        //插入课程 根据课时循环
        for (int i = 0; i < courseTimetabling.getCourseNum();) {
            for (int j = 0; j < 3; j++) {
                //Integer courseId, Integer userId, Integer roomId, Integer year, Integer month, Integer day, Integer courseTime, String remark, Integer isDelete
                int timeCount = 0;
                Random random = new Random();
                max = 4;
                //随机安排到一个课程时间
                int courseTime = random.nextInt(max)%(max-min+1) + min;

                //随机安排一个教室
                max = classroomInfoList.size()-1;
                int roomIndex = random.nextInt(max)%(max-min+1) + min;
                ClassroomInfo classroomInfo = classroomInfoList.get(roomIndex);
                String remark = String.format("课程 %s 固定使用",courseInfo.getCourseName());
                if(courseTime == 1) {
                    //如果第一节课或者第二节课被用了那就开始下一轮
                    if (checkCourse(classroomInfo.getId(),year,month,day+weekday[j],1) ||
                            checkCourse(classroomInfo.getId(),year,month,day+weekday[j],2)) {
                        j--;
                        continue;
                    }
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],1,
                            remark,CommonConstant.DELETED_NO

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],2,
                            remark,CommonConstant.DELETED_NO

                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTime == 2) {
                    //如果第一节课或者第二节课被用了那就开始下一轮
                    if (checkCourse(classroomInfo.getId(),year,month,day+weekday[j],3) ||
                            checkCourse(classroomInfo.getId(),year,month,day+weekday[j],4)) {
                        i-=2;
                        continue;
                    }
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],3,
                            remark,CommonConstant.DELETED_NO

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],4,
                            remark,CommonConstant.DELETED_NO
                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTime == 3) {
                    //如果第一节课或者第二节课被用了那就开始下一轮
                    if (checkCourse(classroomInfo.getId(),year,month,day+weekday[j],6) ||
                            checkCourse(classroomInfo.getId(),year,month,day+weekday[j],7)) {
                        i-=2;
                        continue;
                    }
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],6,
                            remark,CommonConstant.DELETED_NO

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],7,
                            remark,CommonConstant.DELETED_NO

                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTime == 4) {
                    //如果第一节课或者第二节课被用了那就开始下一轮
                    if (checkCourse(classroomInfo.getId(),year,month,day+weekday[j],8) ||
                            checkCourse(classroomInfo.getId(),year,month,day+weekday[j],9)) {
                        i-=2;
                        continue;
                    }
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],8,
                            remark,CommonConstant.DELETED_NO

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,day+weekday[j],9,
                            remark,CommonConstant.DELETED_NO

                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                }
                timeCount++;
                if (timeCount > 100) {
                    responseData.setError("教室不够分配！");
                    return responseData;
                }
                i += 2;
                if(i >= courseTimetabling.getCourseNum()) {
                    break;
                }
            }

            day+=7;
        }

        responseData.setOK("处理成功");
        return responseData;
    }

    @Override
    public ResponseData<String> disagreeCoursePlan(Integer courseTimetablingId) {
        ResponseData<String> responseData = new ResponseData<>();
        CourseTimetabling courseTimetabling = courseTimetablingMapper.selectByPrimaryKey(courseTimetablingId);

        responseData.setOK("处理成功");
        return responseData;
    }

    private Integer getStudentNum(CourseTimetabling courseTimetabling) {
        ClassInfo classInfo1 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId1());
        ClassInfo classInfo2 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId2());
        ClassInfo classInfo3 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId3());
        ClassInfo classInfo4 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId4());
        return classInfo1.getClassNumber() + classInfo2.getClassNumber() + classInfo3.getClassNumber() + classInfo4.getClassNumber();
    }
    //判断教室是否被用 false表示没用 true表示用了
    private boolean checkCourse(Integer classroomId, Integer year, Integer month, Integer day, Integer courseTime){
        CourseTimetabling courseTimetabling = courseTimetablingMapper.checkCourseTime(classroomId,year,month,day,courseTime);
        if (courseTimetabling == null) {
            return false;
        } else {
            return true;
        }
    }
}
