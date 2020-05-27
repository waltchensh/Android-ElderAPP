package com.example.harden;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class m_productsDetailsActivity extends AppCompatActivity {

    private TextView mPrice;
    private TextView mProduct;
    private TextView mContent;
    private Spinner mBook_categories_spiinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private  Button mJoin_btn;
    private Button mBack_btn;

    private String key;
    private String price;
    private String pro;
    private String category;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_productsdetails);

        key=getIntent().getStringExtra("key");
        pro=getIntent().getStringExtra("product");
        price=getIntent().getStringExtra("price");
        category=getIntent().getStringExtra("category");
        content=getIntent().getStringExtra("content");

        mPrice=(TextView) findViewById(R.id.author_editTxt);
        mPrice.setText(price);
        mProduct = (TextView)findViewById(R.id.title_editTxt);
        mProduct.setText(pro);
        mContent = (TextView)findViewById(R.id.isbn_editTxt);
        mContent.setText(content);
        mBook_categories_spiinner=(Spinner)findViewById(R.id.book_categories_spinner);
        mBook_categories_spiinner.setSelection(getIndex_SpinnerItem(mBook_categories_spiinner,category));


        mUpdate_btn = (Button)findViewById(R.id.update_btn);
        mDelete_btn=(Button)findViewById(R.id.delete_btn);
        mJoin_btn = (Button)findViewById(R.id.joint_btn);
        mBack_btn=(Button)findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.setProduct(mProduct.getText().toString());
                product.setPrice(mPrice.getText().toString());
                product.setContent(mContent.getText().toString());
                product.setCategory_name(mBook_categories_spiinner.getSelectedItem().toString());

                new FirebaseDatabaseHelperP().updateProduct(key, product, new FirebaseDatabaseHelperP.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Product> products, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(m_productsDetailsActivity.this,"更新成功!",Toast.LENGTH_LONG).show();

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
                new FirebaseDatabaseHelperP().deleteProduct(key, new FirebaseDatabaseHelperP.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Product> products, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(m_productsDetailsActivity.this,"刪除成功!",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });
        mJoin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
