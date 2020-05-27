package com.example.harden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class m_shop_pages extends AppCompatActivity {
    Button QRcode_button;
    Button record_button;
    Button a_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_shop);
        a_button=(Button)findViewById(R.id.a_button);
        QRcode_button = (Button)findViewById(R.id.QRcode_button);
        record_button =(Button)findViewById(R.id.record_button);

        a_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(m_shop_pages.this ,m_ProductsActivity.class);
                startActivity(intent);


            }
        });

        QRcode_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(m_shop_pages.this , m_NewProductsActivity.class);
                startActivity(intent);


            }
        });

    }
}
