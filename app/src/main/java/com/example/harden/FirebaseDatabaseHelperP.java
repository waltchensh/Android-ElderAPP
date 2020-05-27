package com.example.harden;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperP {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceProducts;
    private List<Product> products = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Product> products, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelperP() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceProducts = mDatabase.getReference("products");
    }
    public void readProducts(final DataStatus dataStatus){
        mReferenceProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Product product = keyNode.getValue(Product.class);
                    products.add(product);
                }
                dataStatus.DataIsLoaded(products,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addProduct(Product product,final DataStatus dataStatus){
        String key = mReferenceProducts.push().getKey();
        mReferenceProducts.child(key).setValue(product)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
    public void updateProduct(String key,Product product,final DataStatus dataStatus){
        mReferenceProducts.child(key).setValue(product)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }
    public void deleteProduct(String key,final DataStatus dataStatus){
        mReferenceProducts.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
