package com.example.android.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Marks extends AppCompatActivity {

    private TextView ct1,ct2,pue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        findViewById(R.id.tvCT1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Marks.this,Marks_All_Subject.class);
                startActivity(intent);
            }
        });
    }
}
