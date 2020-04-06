package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-05
 * Time: 15:54
 */
public class SchoolInfoListBO {

    private Integer id;

    private String schoolName;

    private String schoolAddress;

    private String schoolCode;

    private String schoolPresident;

    private String schoolTelephone;

    private String schoolMailCode;

    private Integer userNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolPresident() {
        return schoolPresident;
    }

    public void setSchoolPresident(String schoolPresident) {
        this.schoolPresident = schoolPresident;
    }

    public String getSchoolTelephone() {
        return schoolTelephone;
    }

    public void setSchoolTelephone(String schoolTelephone) {
        this.schoolTelephone = schoolTelephone;
    }

    public String getSchoolMailCode() {
        return schoolMailCode;
    }

    public void setSchoolMailCode(String schoolMailCode) {
        this.schoolMailCode = schoolMailCode;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}
