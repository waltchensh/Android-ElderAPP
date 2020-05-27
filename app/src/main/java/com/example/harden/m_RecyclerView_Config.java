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

public class m_RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Book> books,List<String> keys){
        mContext = context;
        mBooksAdapter = new BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mDate;
        private TextView mActivity;
        private TextView mContent;
        private TextView mCategory;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.book_list_item,parent,false));

            mDate = (TextView)itemView.findViewById(R.id.title_txtView);
            mActivity = (TextView)itemView.findViewById(R.id.author_txtView);
            mCategory = (TextView) itemView.findViewById(R.id.catefory_txtView);
            mContent = (TextView) itemView.findViewById(R.id.isbn_txtView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, m_activitiesDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("activity" ,mActivity.getText().toString());
                    intent.putExtra("date",mDate.getText().toString());
                    intent.putExtra("category",mCategory.getText().toString());
                    intent.putExtra("content",mContent.getText().toString());

                    mContext.startActivity(intent);

                }
            });
        }
        public void bind(Book book,String key){
            mDate.setText(book.getDate());
            mActivity.setText(book.getActivity());
            mCategory.setText(book.getCategory_name());
            mContent.setText(book.getContent());
            this.key = key;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKey;

        public BooksAdapter(List<Book> mBookList, List<String> mKey) {
            this.mBookList = mBookList;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKey.get(position));

        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
