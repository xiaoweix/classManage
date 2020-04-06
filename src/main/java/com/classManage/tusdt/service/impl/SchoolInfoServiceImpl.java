package com.classManage.tusdt.service.impl;

import com.classManage.tusdt.base.common.ResponseData;
import com.classManage.tusdt.constants.CommonConstant;
import com.classManage.tusdt.dao.SchoolInfoMapper;
import com.classManage.tusdt.dao.UserMapper;
import com.classManage.tusdt.model.BO.SchoolCatalogBO;
import com.classManage.tusdt.model.BO.SchoolInfoListBO;
import com.classManage.tusdt.model.SchoolInfo;
import com.classManage.tusdt.model.User;
import com.classManage.tusdt.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-05
 * Time: 16:03
 */
@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseData<String> addSchool(SchoolInfo schoolInfo) {
        ResponseData<String> responseData = new ResponseData<>();

        if(checkSchoolName(schoolInfo.getSchoolName())) {
            responseData.setError("校名重复");
            return responseData;
        }
        if (checkSchoolCode(schoolInfo.getSchoolCode())) {
            responseData.setError("学校码重复");
            return responseData;
        }
        schoolInfo.setCreateTime(new Date());
        schoolInfo.setIsDelete(CommonConstant.DELETED_NO);

        Integer result = schoolInfoMapper.insert(schoolInfo);
        if (result == 0) {
            responseData.setError("添加失败");
            return responseData;
        }
        responseData.setOK("添加成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifySchool(SchoolInfo schoolInfo) {

        ResponseData<String> responseData = new ResponseData<>();

        if(checkSchoolName(schoolInfo.getSchoolName())) {
            responseData.setError("校名重复");
            return responseData;
        }
        if (checkSchoolCode(schoolInfo.getSchoolCode())) {
            responseData.setError("学校码重复");
            return responseData;
        }
        schoolInfoMapper.updateByPrimaryKeySelective(schoolInfo);
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> removeSchool(Integer schoolId) {
        //TODO 要判断有没有学生，如果学校下有用户则不能删除
        ResponseData<String> responseData = new ResponseData<>();
        SchoolInfo schoolInfo = schoolInfoMapper.selectByPrimaryKey(schoolId);

        schoolInfo.setIsDelete(CommonConstant.DELETED_YES);
        Integer result = schoolInfoMapper.updateByPrimaryKeySelective(schoolInfo);
        if (result == 0) {
            responseData.setError("修改失败");
            return responseData;
        }
        responseData.setOK("修改成功");
        return responseData;
    }

    @Override
    public List<SchoolInfoListBO> getSchoolInfo(String schoolName) {
        List<SchoolInfoListBO> schoolInfoListBOList = schoolInfoMapper.selectSchoolInfoList(schoolName);
        for (int i = 0; i < schoolInfoListBOList.size(); i++) {
           Integer schoolId = schoolInfoListBOList.get(i).getId();
           Integer userNum = userMapper.countBySchool(schoolId);
           schoolInfoListBOList.get(i).setUserNum(userNum);
        }
        return schoolInfoListBOList;
    }

    @Override
    public List<SchoolCatalogBO> getSchoolCatalog() {
        List<SchoolCatalogBO> schoolCatalogBOList = schoolInfoMapper.selectSchoolCatalog();
        return schoolCatalogBOList;
    }

    @Override
    public boolean checkSchoolName(String schoolName) {
        List<SchoolInfo> schoolInfoList = schoolInfoMapper.checkSchoolName(schoolName);
        return schoolInfoList != null && !schoolInfoList.isEmpty();
    }

    @Override
    public boolean checkSchoolCode(String schoolCode) {
        List<SchoolInfo> schoolInfoList = schoolInfoMapper.checkSchoolCode(schoolCode);
        return schoolInfoList != null && !schoolInfoList.isEmpty();
    }


}
