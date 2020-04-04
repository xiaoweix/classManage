package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-04
 * Time: 19:24
 */
public class UserListBO {

    private Integer id;

    private String userName;

    private String email;

    private Integer jobLevel;

    private Integer status;

    private Integer classId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
