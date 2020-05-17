package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.*;
import com.classManage.tusdt.model.*;
import com.classManage.tusdt.model.BO.ClassSchedule;
import com.classManage.tusdt.model.BO.ClassUsingScheduleBO;
import com.classManage.tusdt.model.BO.CoursePlanListBO;
import com.classManage.tusdt.service.CourseTimetablingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        courseTimetabling.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_WAIT);
        courseTimetabling.setEndTime(new Date());
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
    public List<CoursePlanListBO> getTeacherCoursePlan(Integer userId, Integer schoolId, String courseName) {
        return courseTimetablingMapper.getTeacherCoursePlan(userId,schoolId,courseName);
    }


    @Override
    public ResponseData<String> dealCoursePlan(Integer courseTimetablingId) {
        ResponseData<String> responseData = new ResponseData<>();
        CourseTimetabling courseTimetabling = courseTimetablingMapper.selectByPrimaryKey(courseTimetablingId);
        if(courseTimetabling.getResult().equals(CommonConstant.CLASSROOM_APPLY_RESULT_NO) || courseTimetabling.getResult().equals(CommonConstant.CLASSROOM_APPLY_RESULT_YES)) {
            responseData.setError("课程已经被处理无需再处理");
            return responseData;
        }
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
        int day = timeCal.get(Calendar.DATE)+distance+1; //算出距离最近的下一个星期一
        //将下周一的时间 转成日期字符串 yyyy-MM-dd HH:mm:ss
        String dataString = String.format("%s-%s-%s %s:%s:%s",year,month,day,"00","00","00");
        //获取整个学校的教室 根据课程的人数、学校的楼层查找
        System.out.println(dataString);
        int courseStudentNum = getStudentNum(courseTimetabling);
        List<ClassroomInfo> classroomInfoList = classroomInfoMapper.getClassroomBySchoolID(courseTimetabling.getSchoolId(),courseTimetabling.getBuildingLayer(),courseStudentNum);

        int cyclingCount = 0;
        //表示三天上課星期数 随机数且不相等
        List<Integer> weekday = dealWeekday(courseTimetabling,year,month,day);

        List<Integer> courseTimeList = new ArrayList<>();
        int max=4;//第四次课
        int min=1;//第一次课
        //随机安排每周的三节课
        while (courseTimeList.size() < 3) {
            Random random = new Random();
            int tempCourseTime = random.nextInt(max)%(max-min+1) + min;
            tempCourseTime *= 2;
            //检查老师的课表里面这节课被安排了没有
            int i = courseTimeList.size();
            cyclingCount ++;
            if (cyclingCount>100) {
                Collections.shuffle(weekday);
                cyclingCount = 0;
                courseTimeList.clear();
            }
            if(checkTeacherTiming(courseTimetabling.getUserId(),year,month,weekday.get(i),tempCourseTime)) {
                continue;
            }
            tempCourseTime/=2;
            courseTimeList.add(tempCourseTime);
        }
        //表示随机三间教室 特别注意要判断教室选取合不合理，如果有教室被占用，那么不能将它排课
        Map<Integer,ClassroomInfo>  classroomInfoMap= new HashMap<>();

        //随机安排一个教室
        max = classroomInfoList.size()-1;
        Random random = new Random();
        //count是用来判断是否随机到了一个教室
        //一周三节课 分别对应三间教室
        for (int j = 0; j < 3; j++) {
            int roomIndex = random.nextInt(max)%(max-min+1) + min;
            ClassroomInfo classroomInfo = classroomInfoList.get(roomIndex);
            if (checkCourse(classroomInfo.getId(),year,month,weekday.get(j),courseTimeList.get(j))) {
                j--;
                continue;
            }
            classroomInfoMap.put(weekday.get(j),classroomInfo);
            if(classroomInfoMap.size() >= 3) {
                break;
            }
        }


        //插入课程 根据课时循环
        for (int i = 0; i < courseTimetabling.getCourseNum();) {
            for (int j = 0; j < 3; j++) {
                //Integer courseId, Integer userId, Integer roomId, Integer year, Integer month, Integer day, Integer courseTime, String remark, Integer isDelete
                int timeCount = 0;
                ClassroomInfo classroomInfo = classroomInfoMap.get(weekday.get(j));
                String remark = String.format("课程 %s 固定使用",courseInfo.getCourseName());
                int newDay = day+weekday.get(j);
                if((month == 1 || month == 3 ||month == 5 ||month == 7 ||month == 8 ||month == 10 ||month == 12) && newDay>31) {
                    month += 1;
                    newDay -= 31;
                    day -= 31;
                } else if ((month == 4 || month == 6 ||month == 9 ||month == 11 ) && newDay>30) {
                    month += 1;
                    newDay -= 30;
                    day -= 30;
                } else if (month == 2 && newDay > 28) {
                    month += 1;
                    newDay -= 28;
                    day -= 28;
                }
                if(courseTimeList.get(j) == 1) {
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),1,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),2,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTimeList.get(j) == 2) {
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),3,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),4,
                            remark,CommonConstant.DELETED_NO, courseStudentNum
                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTimeList.get(j) == 3) {
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),6,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),7,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    classUsingMapper.insert(classUsing);
                    classUsingMapper.insert(classUsing2);
                } else if (courseTimeList.get(j) == 4) {
                    ClassUsing classUsing = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),8,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

                    );
                    ClassUsing classUsing2 = new ClassUsing(
                            courseTimetabling.getCourseId(),
                            courseTimetabling.getUserId(),
                            classroomInfo.getId(),year,month,newDay,weekday.get(j),9,
                            remark,CommonConstant.DELETED_NO, courseStudentNum

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
        courseTimetabling.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_YES);
        courseTimetablingMapper.updateByPrimaryKeySelective(courseTimetabling);
        responseData.setOK("处理成功");
        return responseData;
    }

    @Override
    public ResponseData<String> disagreeCoursePlan(Integer courseTimetablingId) {
        ResponseData<String> responseData = new ResponseData<>();
        CourseTimetabling courseTimetabling = courseTimetablingMapper.selectByPrimaryKey(courseTimetablingId);
        courseTimetabling.setResult(CommonConstant.CLASSROOM_APPLY_RESULT_NO);
        courseTimetablingMapper.updateByPrimaryKeySelective(courseTimetabling);
        responseData.setOK("处理成功");
        return responseData;
    }

    @Override
    public List<ClassSchedule> getSchedule(Integer userID) {
        Calendar timeCal = Calendar.getInstance();
        //课程开始时间
        timeCal.setTime(new Date());
        int year = timeCal.get(Calendar.YEAR);
        int month = timeCal.get(Calendar.MONTH)+1;
        String tempInfo;
        List<ClassSchedule> classScheduleList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            ClassSchedule classSchedule = new ClassSchedule();
            classSchedule.setWeek(i);
            List<String> courseInfo = new ArrayList<>();
            for (int j = 1; j < 10; j++) {
                if (j == 5) {
                    courseInfo.add(" ");
                    continue;
                }
                List<ClassUsingScheduleBO> classUsingScheduleBOList = classUsingMapper.getSchedule(userID,year,month,i,j);
                if(classUsingScheduleBOList == null || classUsingScheduleBOList.isEmpty()) {
                    classUsingScheduleBOList = classUsingMapper.getSchedule(userID,year,month+1,i,j);
                    if(classUsingScheduleBOList == null || classUsingScheduleBOList.isEmpty()) {
                        courseInfo.add(" ");
                        continue;
                    }
                }
                ClassUsingScheduleBO tempSchedule = classUsingScheduleBOList.get(0);
                tempInfo = String.format("%s-%s",tempSchedule.getCourseName(),tempSchedule.getRoomName());
                courseInfo.add(tempInfo);
            }
            classSchedule.setCourseInfo(courseInfo);
            classScheduleList.add(classSchedule);
        }

        return classScheduleList;
    }

    private Integer getStudentNum(CourseTimetabling courseTimetabling) {
        int stuNum = 0;
        if (courseTimetabling.getClassId1() != null) {
            ClassInfo classInfo1 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId1());
            stuNum += classInfo1.getClassNumber();
        }
        if (courseTimetabling.getClassId2() != null) {
            ClassInfo classInfo2 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId2());
            stuNum += classInfo2.getClassNumber();
        }
        if (courseTimetabling.getClassId3() != null) {
            ClassInfo classInfo3 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId3());
            stuNum += classInfo3.getClassNumber();
        }
        if (courseTimetabling.getClassId4() != null) {
            ClassInfo classInfo4 = classInfoMapper.selectByPrimaryKey(courseTimetabling.getClassId4());
            stuNum += classInfo4.getClassNumber();
        }
        return stuNum;
    }
    //判断教室是否被用 false表示没用 true表示用了㏂
    private boolean checkCourse(Integer classroomId, Integer year, Integer month, Integer courseTime,Integer week){
        List<ClassUsing> classUsingList = classUsingMapper.checkCourseTime(classroomId,year,month,week,courseTime);
        if(classUsingList == null || classUsingList.isEmpty()) {
            classUsingList = classUsingMapper.checkCourseTime(classroomId,year,month+1,week,courseTime);
            if(classUsingList != null && !classUsingList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    //判断老师是否在这节课已经安排了课程了 false表示没有用 true表示用了
    private boolean checkTeacherTiming(Integer teacherId, Integer year, Integer month,Integer week, Integer courseTime){
        List<ClassUsing> classUsing = classUsingMapper.checkTeacherTime(teacherId,year,month,week,courseTime);
        if(classUsing == null || classUsing.isEmpty()) {
            classUsing = classUsingMapper.checkTeacherTime(teacherId,year,month+1,week,courseTime);
            if(classUsing == null || classUsing.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //检查周几是否被用慢 true被用满 false没有用满
    private boolean checkWeek(Integer teacherId, Integer year, Integer month, Integer day,Integer week){
        List<ClassUsing> classUsing = classUsingMapper.checkWeekTime(teacherId,year,month,day,week);
        if (classUsing == null) {
            return false;
        }
        if(classUsing.size()<8) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        int min,max;
        max=4;//第四次课
        min=1;//第一次课
        for (int i=0; i<3; i++) {
            Random random = new Random();
            int tempCourseTime = random.nextInt(max)%(max-min+1) + min;
            System.out.println(tempCourseTime);
        }

    }
    private List<Integer> dealWeekday(CourseTimetabling courseTimetabling,Integer year, Integer month, Integer day){
        int max=5;//星期五
        int min=1;//星期一
        int cyclingCount = 0;
        Set<Integer> tempSet = new HashSet<>();
        for (int i=0; i<100; i++) {
            cyclingCount++;
            if (cyclingCount>100) {
                if (tempSet.size()==0) {
                    tempSet.add(6);
                }
                if (tempSet.size()==1) {
                    tempSet.add(6);
                }
                if (tempSet.size()==2) {
                    tempSet.add(7);
                }
                break;
            }
            Random random = new Random();
            int tempWeek =  random.nextInt(max)%(max-min+1) + min;
            if (checkWeek(courseTimetabling.getUserId(),year,month,day,tempWeek)) {
                i--;
                continue;
            }
            tempSet.add(tempWeek);
            if (tempSet.size() >= 3) {
                break;
            }
        }
        List<Integer> result =  new ArrayList<>(tempSet);
        return result;
    }
}
