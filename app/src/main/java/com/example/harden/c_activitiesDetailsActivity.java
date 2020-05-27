package com.example.harden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class c_activitiesDetailsActivity extends AppCompatActivity {

    private TextView mActivity;
    private TextView mDate;
    private TextView mContent;
    private Spinner mBook_categories_spiinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private  Button mJoint_btn;
    private  Button mCheckIn_btn;
    private Button mBack_btn;

    private String key;
    private String activity;
    private String date;
    private String category;
    private String content;
    DatabaseReference databaseArtists;
    DatabaseReference databasejoined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_activitydetails);

        key=getIntent().getStringExtra("key");
        date=getIntent().getStringExtra("date");
        activity=getIntent().getStringExtra("activity");
        category=getIntent().getStringExtra("category");
        content=getIntent().getStringExtra("content");

        mActivity=(TextView) findViewById(R.id.author_editTxt);
        mActivity.setText(activity);
        mDate = (TextView)findViewById(R.id.title_editTxt);
        mDate.setText(date);
        mContent = (TextView)findViewById(R.id.isbn_editTxt);
        mContent.setText(content);
        mBook_categories_spiinner=(Spinner)findViewById(R.id.book_categories_spinner);
        mBook_categories_spiinner.setSelection(getIndex_SpinnerItem(mBook_categories_spiinner,category));


        mUpdate_btn = (Button)findViewById(R.id.update_btn);
        mDelete_btn=(Button)findViewById(R.id.delete_btn);
        mJoint_btn = (Button)findViewById(R.id.joint_btn);
        mCheckIn_btn = (Button)findViewById(R.id.check_in_btn);
        mBack_btn=(Button)findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setDate(mDate.getText().toString());
                book.setActivity(mActivity.getText().toString());
                book.setContent(mContent.getText().toString());
                book.setCategory_name(mBook_categories_spiinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateBook(key, book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(c_activitiesDetailsActivity.this,"Book c_CheckIn_Record been"+
                                "update successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(c_activitiesDetailsActivity.this,"Book c_CheckIn_Record has been"+
                                "deleted successfully!",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });
        mJoint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasejoined = FirebaseDatabase.getInstance().getReference("join_activity");
                addActivity();
            }
        });
        mCheckIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseArtists = FirebaseDatabase.getInstance().getReference("record_artists");
                addArtist();
                Intent intent = new Intent();
                intent.setClass(c_activitiesDetailsActivity.this , c_GetMoney.class);
                startActivity(intent);
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }
    private int getIndex_SpinnerItem(Spinner spinner,String item){
        int index=0;
        for (int i=0; i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }
    private void addArtist(){

            String id = databaseArtists.push().getKey();
            record_Artist artist = new record_Artist(id,date,activity);
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this , "簽到完成！",Toast.LENGTH_LONG).show();


    }
    private void addActivity(){

        String id = databasejoined.push().getKey();
        join_activity activities = new join_activity(id,date,activity);
        databasejoined.child(id).setValue(activities);

        Toast.makeText(this , "報名成功！",Toast.LENGTH_LONG).show();


    }
}
