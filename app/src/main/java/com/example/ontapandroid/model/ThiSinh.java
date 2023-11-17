package com.example.ontapandroid.model;

public class ThiSinh {
    private String sobaodanh;
    private String TenTS;
    private double diemtoan,diemly,diemhoa;

    public ThiSinh() {
    }

    public ThiSinh(String sobaodanh, String tenTS, double diemtoan, double diemly, double diemhoa) {
        this.sobaodanh = sobaodanh;
        TenTS = tenTS;
        this.diemtoan = diemtoan;
        this.diemly = diemly;
        this.diemhoa = diemhoa;
    }

    public String getSobaodanh() {
        return sobaodanh;
    }

    public void setSobaodanh(String sobaodanh) {
        this.sobaodanh = sobaodanh;
    }

    public String getTenTS() {
        return TenTS;
    }

    public void setTenTS(String tenTS) {
        TenTS = tenTS;
    }

    public double getDiemtoan() {
        return diemtoan;
    }

    public void setDiemtoan(double diemtoan) {
        this.diemtoan = diemtoan;
    }

    public double getDiemly() {
        return diemly;
    }

    public void setDiemly(double diemly) {
        this.diemly = diemly;
    }

    public double getDiemhoa() {
        return diemhoa;
    }

    public void setDiemhoa(double diemhoa) {
        this.diemhoa = diemhoa;
    }
}
