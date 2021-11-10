package com.example.quanylysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanylysinhvien.dao.LopDao;
import com.example.quanylysinhvien.dao.SinhVienDao;
import com.example.quanylysinhvien.model.Lop;
import com.example.quanylysinhvien.model.SinhVien;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThemSinhVienActivity extends AppCompatActivity {
    /**
     * EditText
     */
    EditText edtTensv, edtMasv, edtemail, edtHinh;

    /**
     * Spinner
     */
    Spinner spMaLop;

    /**
     * Button
     */
    Button btnThem, btnDanhSach, btnReview;

    /**
     * SinhVienDao
     */
    SinhVienDao daoSach;

    /**
     * LopDao
     */
    LopDao lsDao;

    /**
     * linearLayout
     */
    LinearLayout linearLayout;

    /**
     * CircleImageView
     */
    CircleImageView imgAvata;

    /**
     * ArrayList<Lop>
     */
    ArrayList<Lop> lopList = new ArrayList<>();

    /**
     * Animation
     */
    Animation animation;

    /**
     * RelativeLayout
     */
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);
        lsDao = new LopDao(ThemSinhVienActivity.this);

        linearLayout = findViewById(R.id.linearLayout);
        edtMasv = findViewById(R.id.txtMaSV);
        edtTensv = findViewById(R.id.txtTenSV);
        edtHinh = findViewById(R.id.txtHinh);
        edtemail = findViewById(R.id.txtemail);
        spMaLop = findViewById(R.id.txtMalop);
        btnThem = findViewById(R.id.btntThem);

        btnDanhSach = findViewById(R.id.btnDanhSach);
        btnReview = findViewById(R.id.btnReviewThem);
        imgAvata = findViewById(R.id.imgAvata);

        daoSach = new SinhVienDao(ThemSinhVienActivity.this);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        linearLayout.setAnimation(animation);

        relativeLayout = findViewById(R.id.relativel_layout);

        // checking dark mode
        if (ManagerActivity.isDark == true) {
            // dark theme is on
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            // light theme is on
            relativeLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.backdound_app));
        }

        // SinhVien List redirect Event Listener
        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemSinhVienActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });

        // get LopList and configure Adapter
        lopList = lsDao.getAll();
        ArrayAdapter adapter = new ArrayAdapter(ThemSinhVienActivity.this, android.R.layout.simple_spinner_item, lopList);
        spMaLop.setAdapter(adapter);

        // get Image Resource Event Listener
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtHinh.getText().toString().equalsIgnoreCase("")) {
                    imgAvata.setImageResource(R.drawable.avatamacdinh);
                } else if (edtHinh.getText().toString() != "") {
                    int id_hinh = ((Activity) ThemSinhVienActivity.this).getResources().getIdentifier(edtHinh.getText().toString(), "drawable", ((Activity) ThemSinhVienActivity.this).getPackageName());
                    imgAvata.setImageResource(id_hinh);
                }
            }
        });

        // form format validation Event Listener
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    String ma = edtMasv.getText().toString();
                    String ten = edtTensv.getText().toString();
                    String email = edtemail.getText().toString();
                    String hinh = edtHinh.getText().toString();
                    Lop ls = (Lop) spMaLop.getSelectedItem();
                    String maLop = ls.getMaLop();
                    if (ma.equals("")) {// check empty student Id
                        Toast.makeText(ThemSinhVienActivity.this, "Student ID is required", Toast.LENGTH_LONG).show();
                    } else if (ten.equals("")) {// check empty student Name
                        Toast.makeText(ThemSinhVienActivity.this, "Student Name is required", Toast.LENGTH_LONG).show();
                    } else if (ten.matches((".*[0-9].*"))) {// check valid Student Name
                        Toast.makeText(ThemSinhVienActivity.this, "Name is not valid", Toast.LENGTH_LONG).show();
                    } else if (email.equals("")) {// check empty Email
                        Toast.makeText(ThemSinhVienActivity.this, "Email is required", Toast.LENGTH_LONG).show();
                    } else if (email.matches(pattern) == false) {// check valid Email
                        Toast.makeText(ThemSinhVienActivity.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    } else if (hinh.equals("")) {// check image is not selected -> set default image
                        edtHinh.setText("avatamacdinh");
                    } else {
                        SinhVien s = new SinhVien(ma, ten, email, hinh, maLop);
                        if (daoSach.insert(s)) {// success
                            Toast.makeText(ThemSinhVienActivity.this, "Add Student is Success!", Toast.LENGTH_LONG).show();
                        } else {// failed
                            Toast.makeText(ThemSinhVienActivity.this, "Student Id is contained, try another!", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ThemSinhVienActivity.this, "An Error has Accours, try again!" + e, Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
