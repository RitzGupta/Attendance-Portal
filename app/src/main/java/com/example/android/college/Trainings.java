package com.example.android.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Trainings extends AppCompatActivity {

    CardView pythonTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);

        pythonTraining = (CardView)findViewById(R.id.cvPython);

       pythonTraining
               .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent python = new Intent(Trainings.this,PythonTraining.class);
                startActivity(python);
            }
        });
    }
}
