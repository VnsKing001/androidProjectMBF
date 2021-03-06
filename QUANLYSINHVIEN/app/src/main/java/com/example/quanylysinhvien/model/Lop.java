package com.example.quanylysinhvien.model;

import androidx.annotation.NonNull;

public class Lop {
    /**
     * maLop
     */
    private String maLop;

    /**
     * tenLop
     */
    private String tenLop;

    /**
     * Constructor
     *
     * @param maLop
     * @param tenLop
     */
    public Lop(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @NonNull
    @Override
    public String toString() {
        return getMaLop();
    }
}
