package com.classManage.tusdt.model.BO;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-06
 * Time: 16:49
 */
public class BuildingBaseInfoBO {
    private Integer id;

    private String buildingName;

    private String address;

    private Integer buildingLayer;

    private Integer schoolId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBuildingLayer() {
        return buildingLayer;
    }

    public void setBuildingLayer(Integer buildingLayer) {
        this.buildingLayer = buildingLayer;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
