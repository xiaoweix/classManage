package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.base.constants.Response;
import com.classManage.tusdt.model.BO.ClassBaseInfoListBO;
import com.classManage.tusdt.model.ClassInfo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 15:59
 */
public interface ClassInfoService {

    ResponseData<String> addClass(ClassInfo classInfo);

    ResponseData<String> modifyClass(ClassInfo classInfo);

    ResponseData<String> removeClass(Integer classId);

    List<ClassBaseInfoListBO> getClassBaseInfo(String className, Integer schoolId);

    boolean checkClassName(Integer schoolId, String className);
}
