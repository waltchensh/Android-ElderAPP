package com.example.harden;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowRecord extends AppCompatActivity {

    private ListView listview;
    ArrayAdapter adapter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_showrecord);
        setTitle("ShowData");



        listview = (ListView)findViewById(R.id.list_record);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);

        getData();
    }

    private void getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("record");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                mAuth = FirebaseAuth.getInstance();
                String uid=mAuth.getCurrentUser().getUid();//取得目前使用者UID

                for (DataSnapshot contact : dataSnapshot.getChildren()) {
                    if (contact.child("getid").getValue() != null && contact.child("payid").getValue() != null) {
                        String data_get_id = contact.child("getid").getValue().toString();//收到錢的人ID
                        String data_pay_id = contact.child("payid").getValue().toString();//支付錢的人ID
                        //adapter.add(contact.getKey().toString()+"-"+contact.child("getname").getValue().toString());
                        if (data_get_id.equals(uid)) {
                            adapter.add(contact.child("time").getValue().toString() + "  從" + contact.child("payname").getValue().toString() + "獲得" + contact.child("money").getValue().toString() + "樂幣");
                        } else if (data_pay_id.equals(uid)) {
                            adapter.add(contact.child("time").getValue().toString() + "  支付給" + contact.child("getname").getValue().toString() + "   " + contact.child("money").getValue().toString() + "樂幣");
                        }
                    }
                }

                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

}
