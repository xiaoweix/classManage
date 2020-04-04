package com.classManage.tusdt.model;

import java.util.Date;

public class SchoolInfo {
    private Integer id;

    private String schoolName;

    private String schoolAddress;

    private String schoolCode;

    private String schoolPresident;

    private String schoolPresidentIdCard;

    private String schoolTelephone;

    private String schoolMailCode;

    private Date createTime;

    private Integer isDelete;

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
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress == null ? null : schoolAddress.trim();
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolPresident() {
        return schoolPresident;
    }

    public void setSchoolPresident(String schoolPresident) {
        this.schoolPresident = schoolPresident == null ? null : schoolPresident.trim();
    }

    public String getSchoolPresidentIdCard() {
        return schoolPresidentIdCard;
    }

    public void setSchoolPresidentIdCard(String schoolPresidentIdCard) {
        this.schoolPresidentIdCard = schoolPresidentIdCard == null ? null : schoolPresidentIdCard.trim();
    }

    public String getSchoolTelephone() {
        return schoolTelephone;
    }

    public void setSchoolTelephone(String schoolTelephone) {
        this.schoolTelephone = schoolTelephone == null ? null : schoolTelephone.trim();
    }

    public String getSchoolMailCode() {
        return schoolMailCode;
    }

    public void setSchoolMailCode(String schoolMailCode) {
        this.schoolMailCode = schoolMailCode == null ? null : schoolMailCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}