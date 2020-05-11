package com.classManage.tusdt.model.BO;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-05
 * Time: 15:03
 */
public class ClassroomNameBO {

    private Integer classroomId;

    private String buildingName;

    private String classroomName;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
