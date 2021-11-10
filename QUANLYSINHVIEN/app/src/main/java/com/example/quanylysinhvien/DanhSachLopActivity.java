package com.example.quanylysinhvien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanylysinhvien.adapter.LopAdapter;
import com.example.quanylysinhvien.dao.LopDao;
import com.example.quanylysinhvien.dao.SinhVienDao;
import com.example.quanylysinhvien.loginandregisteractivity.LoginActivity;
import com.example.quanylysinhvien.model.Lop;
import com.example.quanylysinhvien.model.SinhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachLopActivity extends AppCompatActivity {

    /**
     * FloatingActionButton
     */
    FloatingActionButton fbadd;

    /**
     * FloatingActionButton
     */
    FloatingActionButton fab;

    /**
     * FloatingActionButton
     */
    FloatingActionButton fbHome;

    /**
     * FloatingActionButton
     */
    FloatingActionButton fabDangXuat;

    /**
     * TextView
     */
    TextView tvanhien;

    /**
     * EditText
     */
    EditText edtSearch;

    /**
     * ArrayList<Lop>
     */
    ArrayList<Lop> dsLop = new ArrayList<>();
    ArrayList<Lop> timKiem = new ArrayList<>();

    /**
     * ArrayList<SinhVien>
     */
    ArrayList<SinhVien> svlist;

    /**
     * static ArrayList<SinhVien>
     */
    static ArrayList<SinhVien> svlistDuocLoc;

    /**
     * static boolean xetList
     */
    public static boolean xetList = true;

    /**
     * ListView, LopAdapter
     */
    ListView listView;
    LopAdapter lopAdapter;

    /**
     * DAO
     */
    LopDao lopDao;
    SinhVienDao sinhVienDao;

    Boolean isOpen = false;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lop);
        relativeLayout = findViewById(R.id.relativel_layout);
        listView = findViewById(R.id.listviewLop);
        fbadd = findViewById(R.id.fbThemLop);
        tvanhien = findViewById(R.id.tvAnHien);
        fbHome = findViewById(R.id.fbHomeLop);
        fab = findViewById(R.id.fab1);
        fabDangXuat = findViewById(R.id.fbDangXuatLop);
        edtSearch = findViewById(R.id.edtserchLop);

        // check theme
        if (ManagerActivity.isDark == true) {
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }

        fbAction();
        lopDao = new LopDao(DanhSachLopActivity.this);

        dsLop = lopDao.getAll();
        timKiem = lopDao.getAll();

        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachLopActivity.this, ThemLopActivity.class));
            }
        });

        lopAdapter = new LopAdapter(DanhSachLopActivity.this, R.layout.dong_lop, dsLop);
        listView.setAdapter(lopAdapter);

        if (dsLop.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            tvanhien.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            tvanhien.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maLop = dsLop.get(position).toString();
                sinhVienDao = new SinhVienDao(DanhSachLopActivity.this);
                svlist = sinhVienDao.getALL();
                int dem = 0;
                svlistDuocLoc = new ArrayList<>();
                for (int i = 0; i < svlist.size(); i++) {

                    SinhVien sv = svlist.get(i);
                    if (maLop.matches(sv.getMaLop())) {
                        svlistDuocLoc.add(svlist.get(i));
                        dem++;
                    }
                }
                if (dem > 0) {
                    Intent i = new Intent(DanhSachLopActivity.this, MainActivity.class);
                    xetList = true;
                    startActivity(i);
                } else {
                    Toast.makeText(DanhSachLopActivity.this, "Không có sinh viên nào thuộc mã lớp " + dsLop.get(position), Toast.LENGTH_LONG).show();
                }
            }
        });


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Search or Filter

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    lopAdapter.resetData();

                } else {
                    lopAdapter.getFilter().filter(s);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    /**
     * fbAction
     */
    private void fbAction() {
        fabDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachLopActivity.this, LoginActivity.class));

            }
        });
        fbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachLopActivity.this, ManagerActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }
        });
    }

    /**
     * openMenu
     */
    private void openMenu() {
        isOpen = true;
        fbHome.animate().translationY(-getResources().getDimension(R.dimen.stan_60));
        fbadd.animate().translationY(-getResources().getDimension(R.dimen.stan_105));
        fabDangXuat.animate().translationY(-getResources().getDimension(R.dimen.stan_155));
    }

    /**
     * closeMenu
     */
    private void closeMenu() {
        isOpen = false;
        fbHome.animate().translationY(0);
        fbadd.animate().translationY(0);
        fabDangXuat.animate().translationY(0);
    }

}
