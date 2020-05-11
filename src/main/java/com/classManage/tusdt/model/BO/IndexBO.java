package com.classManage.tusdt.model.BO;

public class IndexBO {
    private Integer index;

    private String date;

    private Integer userNum;

    public Integer getIndex() {
        return index;
    }

    public IndexBO() {
    }

    public IndexBO(Integer index, String date, Integer userNum) {
        this.index = index;
        this.date = date;
        this.userNum = userNum;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
}
