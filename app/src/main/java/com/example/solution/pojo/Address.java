package com.example.solution.pojo;

public class Address {
    private int Id;
    /**
     * 地区名
     */
    private String District;
    /**
     * 楼盘名
     */
    private String Cellname;
    /**
     * 门牌号
     */
    private String Building;

    public Address(String district, String cellname, String building) {
        District = district;
        Cellname = cellname;
        Building = building;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCellname() {
        return Cellname;
    }

    public void setCellname(String cellname) {
        Cellname = cellname;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }
}
