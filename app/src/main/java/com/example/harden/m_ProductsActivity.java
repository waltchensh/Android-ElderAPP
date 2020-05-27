package com.example.harden;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class m_ProductsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_products);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_p);
        new FirebaseDatabaseHelperP().readProducts(new FirebaseDatabaseHelperP.DataStatus() {
            @Override
            public void DataIsLoaded(List<Product> products, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new m_RecyclerView_P().setConfig(mRecyclerView, m_ProductsActivity.this,products,keys);

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

   /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.booklist_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_book:
                startActivity(new Intent(this,m_NewActivitiesActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
