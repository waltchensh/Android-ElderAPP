package com.example.harden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class m_NewActivitiesActivity extends AppCompatActivity {
    private EditText mActivity;
    EditText date;
    private EditText mContent;
    private Spinner mBook_categories_spinner;
    private Button mAdd_btn;
    private Button mBack_btn;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_newactivity);

        mActivity = (EditText) findViewById(R.id.author_editTxt);
        mContent = (EditText)findViewById(R.id.isbn_editTxt);
        mBook_categories_spinner=(Spinner)findViewById(R.id.book_categories_spinner);

        mAdd_btn = (Button)findViewById(R.id.add_btn);
        mBack_btn=(Button)findViewById(R.id.back_btn);


        date = (EditText)findViewById(R.id.editText_record);
        Button dateButton = (Button)findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(m_NewActivitiesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = ""+ setDateFormat(year,month,day);
                        date.setText(format);
                    }

                }, mYear,mMonth, mDay).show();
            }

        });

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setActivity(mActivity.getText().toString());
                book.setDate(date.getText().toString());
                book.setContent(mContent.getText().toString());
                book.setCategory_name(mBook_categories_spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(m_NewActivitiesActivity.this,"已成功發佈!",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(m_NewActivitiesActivity.this , m_activitiesListActivity.class);
                startActivity(intent);

            }
        });


    }
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }
}
