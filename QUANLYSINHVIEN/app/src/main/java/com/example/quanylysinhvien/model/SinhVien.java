package com.example.quanylysinhvien.model;

public class SinhVien {
    /**
     * maSv
     */
    private String maSv;

    /**
     * tenSv
     */
    private String tenSv;

    /**
     * email
     */
    private String email;

    /**
     * hinh
     */
    private String hinh;

    /**
     * maLop
     */
    private String maLop;

    /**
     * Constructor
     *
     * @param maSv
     * @param tenSv
     * @param email
     * @param hinh
     * @param maLop
     */
    public SinhVien(String maSv, String tenSv, String email, String hinh, String maLop) {
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.email = email;
        this.hinh = hinh;
        this.maLop = maLop;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
}
