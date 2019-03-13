
package com.example.android.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Attendance extends AppCompatActivity {

    private CardView Monday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Monday = (CardView)findViewById(R.id.cvMonday);
        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Attendance.this,Attendance_Detail.class);
                startActivity(intent);

            }
        });

    }
}
