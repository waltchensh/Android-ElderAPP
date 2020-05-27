package com.example.harden;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class c_activities_pages extends AppCompatActivity {
    Button QRcode_button;
    Button record_button;
    Button a_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_activities);
        a_button=(Button)findViewById(R.id.a_button);
        QRcode_button = (Button)findViewById(R.id.QRcode_button);
        record_button =(Button)findViewById(R.id.record_button);

        a_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(c_activities_pages.this ,c_activitiesListActivity.class);
                startActivity(intent);


            }
        });

        QRcode_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(c_activities_pages.this , c_Join_Record.class);
                startActivity(intent);


            }
        });
        record_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(c_activities_pages.this , c_CheckIn_Record.class);
                startActivity(intent);


            }
        });
    }
}
