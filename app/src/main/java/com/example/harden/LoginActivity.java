package com.example.harden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.AlertDialog;
import android.util.Log;


public class LoginActivity extends AppCompatActivity {
    private Button btnlogin,btnregister;
    private EditText edtemail,edtpassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findviewById();

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG",user.getUid());
                    startActivity(new Intent(LoginActivity.this,transferPage.class));
                } else {
                    // User is signed out
                    Log.d("Tag","user ==null");
                }
                // ...
            }
        };
    }
    private View.OnClickListener btnListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnlogin) {
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                if (email.matches("")||password.matches("")){
                    new AlertDialog.Builder(LoginActivity.this)
                            .setMessage("請輸入帳號密碼")
                            .setPositiveButton("確定", null)
                            .show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Log.d("TAG", "登入成功");
                                        startActivity(new Intent(LoginActivity.this,transferPage.class));
                                    } else {
                                        new AlertDialog.Builder(LoginActivity.this)
                                                .setMessage("帳號密碼錯誤")
                                                .setPositiveButton("確定", null)
                                                .show();
                                    }
                                }
                            });
                }
            }
            if (view.getId()==R.id.btnregister){
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        }
    };
    private void findviewById() {
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnregister = (Button)findViewById(R.id.btnregister);
        edtemail = (EditText)findViewById(R.id.edtemail);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        btnlogin.setOnClickListener(btnListener);
        btnregister.setOnClickListener(btnListener);

    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

