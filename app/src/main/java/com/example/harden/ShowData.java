package com.example.harden;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.widget.*;

public class ShowData extends AppCompatActivity {
    private RelativeLayout icon2;
    private RelativeLayout icon5;
    private RelativeLayout btnlogout;
    private ListView listview;
    ArrayAdapter adapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_c_home);
        icon2 = (RelativeLayout) findViewById(R.id.icon2);
        icon5 = (RelativeLayout) findViewById(R.id.icon5);

        getData();

        setTitle("ShowData");
        icon2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShowData.this , c_activities_pages.class);
                startActivity(intent);


            }
        });

        icon5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShowData.this , c_ProductsActivity.class);
                startActivity(intent);


            }
        });
        RelativeLayout to_get_money = (RelativeLayout)findViewById(R.id.icon1);
        to_get_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShowData.this, c_GetMoney.class);
                startActivity(intent);
            }
        });

        RelativeLayout to_show_record = (RelativeLayout)findViewById(R.id.icon3);
        to_show_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShowData.this, ShowRecord.class);
                startActivity(intent);
            }
        });

        RelativeLayout pay_money = (RelativeLayout)findViewById(R.id.icon4);
        pay_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(ShowData.this,c_pay_money.class);
//                startActivity(intent);
                payCode();
                //Intent intent = new Intent(ShowData.this, c_pay_money.class);
                //startActivity(intent);
            }
        });

        btnlogout = (RelativeLayout)findViewById(R.id.icon6);
        //listview = (ListView)findViewById(R.id.listview);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);



        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowData.this);

                builder.setMessage("確定登出?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FirebaseAuth.getInstance().signOut();
                                Intent intToMain = new Intent(ShowData.this, LoginActivity.class);
                                startActivity(intToMain);
                                Toast.makeText(getApplicationContext(), "已登出",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消",null);
                AlertDialog alert=builder.create();
                alert.show();
            }
        });
    }
    public void payCode() {
        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null)
        {
            if (result.getContents()==null)
            {
                Toast.makeText(this, "取消掃描", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(this, GiveMoney.class);
                String send=result.getContents();
                intent.putExtra("result",send);
                startActivity(intent);

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
    private void getData() {

        mAuth = FirebaseAuth.getInstance();
        //   if(mAuth.getCurrentUser())
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("member");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uid=mAuth.getCurrentUser().getUid();//取得使用者UID
                String who = dataSnapshot.child(uid).child("who").getValue(String.class);
                String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                String money=dataSnapshot.child(uid).child("money").getValue(String.class);
                if (who != null) {
                    if (who.equalsIgnoreCase("一般會員")) {
                        TextView show_name = (TextView) findViewById(R.id.textView3);
                        show_name.setText("Hi," + name);
                        TextView show_money = (TextView) findViewById(R.id.textView4);
                        show_money.setText("樂幣:" + money);
                    } else if (who.equalsIgnoreCase("廠商")) {

                        TextView show_name = (TextView) findViewById(R.id.textView3);
                        show_name.setText("Hi廠商," + name);
                        TextView show_money = (TextView) findViewById(R.id.textView4);
                        show_money.setText("樂幣:" + money);

                    }
                }
                //adapter.clear();

//                for (DataSnapshot contact : dataSnapshot.getChildren()){
//                    adapter.add(contact.getKey().toString()+"-"+contact.child("phone").getValue().toString());
//                }
//
//                listview.setAdapter(adapter);
                //String value = dataSnapshot.child().getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowData.this);

        builder.setMessage("確定登出?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intToMain = new Intent(ShowData.this, LoginActivity.class);
                        startActivity(intToMain);
                        Toast.makeText(getApplicationContext(), "已登出",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null);
        AlertDialog alert=builder.create();
        alert.show();
    }
}
