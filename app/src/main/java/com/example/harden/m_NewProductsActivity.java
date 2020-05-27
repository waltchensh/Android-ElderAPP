package com.example.harden;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class m_NewProductsActivity extends AppCompatActivity {
    private EditText mPrice;
    EditText pro;
    private EditText mContent;
    private Spinner mBook_categories_spinner;
    private Button mAdd_btn;
    private Button mBack_btn;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_newproduct);

        mPrice = (EditText) findViewById(R.id.author_editTxt);
        mContent = (EditText)findViewById(R.id.isbn_editTxt);
        mBook_categories_spinner=(Spinner)findViewById(R.id.book_categories_spinner);

        mAdd_btn = (Button)findViewById(R.id.add_btn);
        mBack_btn=(Button)findViewById(R.id.back_btn);

        pro = (EditText)findViewById(R.id.editText_record);



        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product= new Product();
                product.setPrice(mPrice.getText().toString());
                product.setProduct(pro.getText().toString());
                product.setContent(mContent.getText().toString());
                product.setCategory_name(mBook_categories_spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelperP().addProduct(product, new FirebaseDatabaseHelperP.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Product> products, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(m_NewProductsActivity.this,"已成功發佈!",Toast.LENGTH_LONG).show();

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
                intent.setClass(m_NewProductsActivity.this , m_shop_pages.class);
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
