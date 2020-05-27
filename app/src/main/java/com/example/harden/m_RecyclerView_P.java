package com.example.harden;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class m_RecyclerView_P {
    private Context mContext;
    private ProductsAdapter mProductsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Product> products,List<String> keys){
        mContext = context;
        mProductsAdapter = new ProductsAdapter(products,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mProductsAdapter);
    }

    class ProductItemView extends RecyclerView.ViewHolder{
        private TextView mPro;
        private TextView mPrice;
        private TextView mContent;
        private TextView mCategory;

        private String key;

        public ProductItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.p_list_item,parent,false));
            LayoutInflater.from(mContext)
                    .inflate(R.layout.p_list_item, parent, false);

            mPro = (TextView)itemView.findViewById(R.id.title_txtView);
            mPrice = (TextView)itemView.findViewById(R.id.author_txtView);
            mCategory = (TextView) itemView.findViewById(R.id.catefory_txtView);
            mContent = (TextView) itemView.findViewById(R.id.isbn_txtView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, m_productsDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("price" ,mPrice.getText().toString());
                    intent.putExtra("product",mPro.getText().toString());
                    intent.putExtra("category",mCategory.getText().toString());
                    intent.putExtra("content",mContent.getText().toString());

                    mContext.startActivity(intent);

                }
            });
        }
        public void bind(Product product,String key){
            mPro.setText(product.getProduct());
            mPrice.setText(product.getPrice());
            mCategory.setText(product.getCategory_name());
            mContent.setText(product.getContent());
            this.key = key;
        }
    }
    class ProductsAdapter extends RecyclerView.Adapter<ProductItemView>{
        private List<Product> mProductList;
        private List<String> mKey;

        public ProductsAdapter(List<Product> mProductList, List<String> mKey) {
            this.mProductList = mProductList;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public ProductItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductItemView holder, int position) {
            holder.bind(mProductList.get(position),mKey.get(position));

        }

        @Override
        public int getItemCount() {
            return mProductList.size();
        }
    }
}
