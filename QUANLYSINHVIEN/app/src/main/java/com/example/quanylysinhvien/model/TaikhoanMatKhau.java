package com.example.quanylysinhvien.model;

import java.io.Serializable;

public class TaikhoanMatKhau implements Serializable {
    /**
     * tenTaiKhoan, matKhau
     */
    private String tenTaiKhoan, matKhau;

    /**
     * Constructor
     */
    public TaikhoanMatKhau() {
    }

    /**
     * Constructor
     *
     * @param tenTaiKhoan
     * @param matKhau
     */
    public TaikhoanMatKhau(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


}
