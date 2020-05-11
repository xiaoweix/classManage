package com.classManage.tusdt.model.BO;

import java.util.List;
import java.util.Map;

public class UserCountBO {

    private Integer student;

    private Integer teacher;

    private Integer admin;

    private List<IndexBO> addUserList;

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public List<IndexBO> getAddUserList() {
        return addUserList;
    }

    public void setAddUserList(List<IndexBO> addUserList) {
        this.addUserList = addUserList;
    }
}
