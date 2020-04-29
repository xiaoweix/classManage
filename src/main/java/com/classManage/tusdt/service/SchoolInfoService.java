package com.classManage.tusdt.service;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.model.BO.SchoolCatalogBO;
import com.classManage.tusdt.model.BO.SchoolInfoListBO;
import com.classManage.tusdt.model.SchoolInfo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 20:06
 */
public interface SchoolInfoService {

    ResponseData<String> addSchool(SchoolInfo schoolInfo);

    ResponseData<String> modifySchool(SchoolInfo schoolInfo);

    ResponseData<String> removeSchool(Integer schoolid);

    List<SchoolInfoListBO> getSchoolInfo(String schoolName);

    List<SchoolCatalogBO> getSchoolCatalog();

    boolean checkSchoolName(String schoolName);

    boolean checkSchoolCode(String schoolCode);

    boolean checkSchoolStudent(Integer schoolId);
}
