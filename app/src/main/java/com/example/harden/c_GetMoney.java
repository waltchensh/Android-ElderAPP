package com.example.harden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Bitmap;

import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class c_GetMoney extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_getmoney);

//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(this);
        getCode();
    }

    public void getCode() {
        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getCurrentUser().getUid();//取得使用者UID
        ImageView ivCode = (ImageView) findViewById(R.id.imageView);    //這行代表宣告ivCode即為介面中的imageView
        //TextView etContent = (TextView) findViewById(R.id.textView);    //這行代表宣告etContent即為介面中的textView

        BarcodeEncoder encoder = new BarcodeEncoder();                  //宣告他人寫好的套件
        try {
            Bitmap bit = encoder.encodeBitmap(uid             //etContent.getText().toString()
                    , BarcodeFormat.QR_CODE, 1000, 1000);         //這個就是說將etContent（也就是textView）的內容文字轉為字串
            ivCode.setImageBitmap(bit);                                         //那這個etContent.getText().toString()就是代表我前面textView裡面放什麼就生產啥的qr
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
