package com.example.harden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class c_CheckIn_Record extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerGenres;

    DatabaseReference databaseArtists;
    ListView listViewArtists;

    List<record_Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_checkin_record);
        databaseArtists = FirebaseDatabase.getInstance().getReference("record_artists");

        editTextName = (EditText)findViewById(R.id.editText_record);
        buttonAdd = (Button)findViewById(R.id.button_record);
        spinnerGenres =(Spinner) findViewById(R.id.spinner_record);
        listViewArtists = (ListView)findViewById(R.id.record_listViewArtists);
        artistList = new ArrayList<>();

        Button dateButton = (Button)findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(c_CheckIn_Record.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = ""+ setDateFormat(year,month,day);
                        editTextName.setText(format);
                    }

                }, mYear,mMonth, mDay).show();
            }

        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });
    }
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    record_Artist artist = artistSnapshot.getValue(record_Artist.class);

                    artistList.add(artist);
                }
                record_ArtistList adapter = new record_ArtistList(c_CheckIn_Record.this ,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addArtist(){
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
            String id = databaseArtists.push().getKey();
            record_Artist artist = new record_Artist(id,name,genre);
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this , "Artist added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
        }
    }
}
