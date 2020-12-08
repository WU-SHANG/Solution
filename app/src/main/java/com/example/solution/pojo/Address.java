package com.example.solution.pojo;

public class Address {
    /**
     * 地区名
     */
    private String district;
    /**
     * 楼盘名
     */
    private String cellname;
    /**
     * 门牌号
     */
    private String building;

    public Address(String district, String cellname, String building) {
        this.district = district;
        this.cellname = cellname;
        this.building = building;
    }

    public String getDistrict() {
        return district;
    }

    public String getCellname() {
        return cellname;
    }

    public String getBuilding() {
        return building;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
