package com.classManage.tusdt.model.BO;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-10
 * Time: 20:42
 */
public class OwnClassroomApplyListBO {
    private Integer id;

    private Integer applyType;

    private String purpose;

    private Date applyTime;

    private Integer result;

    private String classroomName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
