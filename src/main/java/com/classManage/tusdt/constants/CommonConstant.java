package com.classManage.tusdt.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 21:14
 */
public class CommonConstant {
    /**
     * 已删除
     */
    public static final Integer DELETED_YES = 1;

    /**
     * 未删除
     */
    public static final Integer DELETED_NO = 0;

    /**
     * 预约教室类型 单时间预约single
     */
    public static final Integer  CLASSROOM_APPLY_TYPE_SINGLE_TIME = 0;

    /**
     * 预约教室类型 多时间预约Multi
     */
    public static final Integer CLASSROOM_APPLY_TYPE_MULTI_TIME = 1;

    /**
     * 预约教室结果 成功 1
     */
    public static final Integer CLASSROOM_APPLY_RESULT_YES = 1;

    /**
     * 预约教室结果 失败 2
     */
    public static final Integer CLASSROOM_APPLY_RESULT_NO = 2;

    /**
     * 预约教室结果 待处理 0
     */
    public static final Integer CLASSROOM_APPLY_RESULT_WAIT = 0;

    /**
     * 当教室被借的时候 课程id为0
     */
    public static final Integer CLASSROOM_USING_LEND_COURSEID = 0;
}
