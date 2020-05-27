package com.example.harden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import android.content.DialogInterface;
import android.app.AlertDialog;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import android.widget.RadioGroup;
import android.widget.RadioButton;


public class SignUpActivity extends AppCompatActivity {
    private Button btnregister;
    private EditText edtemail,edtpassword;
    private FirebaseAuth mAuth;
    private RadioGroup who_group;
    TextView jump;
    private RadioButton who_member;
    private RadioButton who_company;
    private String str_who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        setTitle("註冊");
        findviewById();

        who_group=(RadioGroup)findViewById(R.id.radio_group);
        who_member = (RadioButton) findViewById(R.id.radio_m);
        who_company= (RadioButton) findViewById(R.id.radio_c);

        jump = (TextView) findViewById (R.id.jump);
        jump.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);


            }
        });


        who_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (who_member.getId() == checkedId) {
                    str_who = who_member.getText().toString();
                    show_who();
                } else if (who_company.getId() == checkedId) {
                    str_who = who_company.getText().toString();
                    show_who();
                }
            }
        });

    }

    private void show_who() { //顯示辦會員或是廠商的字
        TextView show_test = (TextView) findViewById(R.id.textView2);
        show_test.setText(str_who);
    }


    private void findviewById() {
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        btnregister = (Button)findViewById(R.id.btnregister);
        btnregister.setOnClickListener(registerListener);
    }

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnregister){
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                if(email.matches("")||password.matches("")) {
                    new AlertDialog.Builder(SignUpActivity.this)
                            .setMessage("請輸入正確的資料")
                            .setPositiveButton("確定", null)
                            .show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        EditText et_name=(EditText)findViewById(R.id.editText);//取得使用者輸入的名稱
                                        String name=et_name.getText().toString();//取得使用者輸入的名稱
                                        String email = edtemail.getText().toString();//取得使用者輸入的Email
                                        String password = edtpassword.getText().toString();//取得使用者輸入的密碼
                                        String money = "999";//給使用者樂幣
                                        String uid=mAuth.getCurrentUser().getUid();//取得使用者UID
                                        String who=str_who;

                                        if(str_who.matches("一般會員")) {

                                            //連接資料庫
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            //目錄 /ex1
                                            DatabaseReference ContactsRef = database.getReference("member");
                                            //將資料放入member
                                            member contact1 = new member(name,email,password,money,who);
                                            //將contact1放人子目錄
                                            ContactsRef.child(uid).setValue(contact1);

                                            new AlertDialog.Builder(SignUpActivity.this)
                                                    .setMessage("註冊會員成功")
                                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                        }

                                                    })
                                                    .show();
                                        }
                                        else if(str_who.matches("廠商")){

                                            //連接資料庫
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            //目錄 /ex1
                                            DatabaseReference ContactsRef = database.getReference("member");
                                            //將資料放入member
                                            member contact1 = new member(name,email,password,money,who);
                                            //將contact1放人子目錄
                                            ContactsRef.child(uid).setValue(contact1);

                                            new AlertDialog.Builder(SignUpActivity.this)
                                                    .setMessage("註冊廠商成功")
                                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                            Intent intent = new Intent();
                                                            intent.setClass(SignUpActivity.this,m_home.class);
                                                            startActivity(intent);
                                                        }

                                                    })
                                                    .show();
                                        }
                                        else{
                                            new AlertDialog.Builder(SignUpActivity.this)
                                                    .setMessage("請選擇註冊類別")
                                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                        }

                                                    })
                                                    .show();
                                        }
                                    } else {
                                        new AlertDialog.Builder(SignUpActivity.this)
                                                .setMessage("註冊失敗")
                                                .setPositiveButton("確定", null)
                                                .show();
                                    }
                                }
                            });
                }

            }
        }
    };

    public class member {

        private  String name;
        private  String email;
        private  String password;
        private  String money;
        private String who;

        public member() {
        }

        public member(String name, String email, String password,String money,String who){
            this.name = name;
            this.email = email;
            this.password = password;
            this.money=money;
            this.who=who;
        }

        public String getName(){
            return this.name=name;
        }
        public String getEmail(){
            return this.email=email;
        }
        public String getPassword(){
            return this.password=password;
        }
        public String getMoney(){
            return this.money=money;
        }
        public String getWho(){
            return this.who=who;
        }
    }
}
