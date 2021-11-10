package com.example.quanylysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanylysinhvien.dao.LopDao;
import com.example.quanylysinhvien.model.Lop;

import java.util.ArrayList;

public class ThemLopActivity extends AppCompatActivity {
    /**
     * LinearLayout
     */
    LinearLayout linearLayout, linearLayout2;

    /**
     * Animation
     */
    Animation animation;

    /**
     * EditText
     */
    EditText edtMalop, edtTenLop;

    /**
     * Button
     */
    Button btnLuu, btnXemLop;

    /**
     * LopDao
     */
    LopDao lopDao;

    /**
     * ArrayList<Lop>
     */
    ArrayList<Lop> dsLop = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        linearLayout = findViewById(R.id.linearLayoutThemLop);
        linearLayout2 = findViewById(R.id.linearLayout);
        btnLuu = findViewById(R.id.btnThêmLop);
        edtMalop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        btnXemLop = findViewById(R.id.btnXemlop);

        // checking theme
        if (ManagerActivity.isDark == true) {
            // dark theme is on
            linearLayout2.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            // light theme is on
            linearLayout2.setBackgroundDrawable(getResources().getDrawable(R.drawable.backdound_app));
        }

        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        linearLayout.setAnimation(animation);
        lopDao = new LopDao(ThemLopActivity.this);

        // Class List redirect Event Listener
        btnXemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemLopActivity.this, DanhSachLopActivity.class));
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });

        // save Listener
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String malop = edtMalop.getText().toString();
                String tenlop = edtTenLop.getText().toString();

                // validate form
                if (malop.equals("")) {
                    Toast.makeText(ThemLopActivity.this, "Class ID is required", Toast.LENGTH_SHORT).show();
                } else if (tenlop.equals("")) {
                    Toast.makeText(ThemLopActivity.this, "Class Name is required", Toast.LENGTH_SHORT).show();
                } else {
                    Lop lop = new Lop(malop, tenlop);
                    if (lopDao.insert(lop)) {// add success
                        Toast.makeText(ThemLopActivity.this, "ADD SUCCESS!", Toast.LENGTH_SHORT).show();
                    } else {// add failed
                        Toast.makeText(ThemLopActivity.this, "ADD FAILED! TRY AGAIN!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
