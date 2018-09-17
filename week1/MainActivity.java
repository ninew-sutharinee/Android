package com.example.win10v3.test_android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference databaseReference = firebaseDatabase.getReference();


    private DatabaseReference Dbr_user = firebaseDatabase.getReference("User");
    //อ่านข้อมูลในโหนด User


    ListView listView;
    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText editName, editno , editmajor;
    ImageButton btn , btn_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lv_text);
        adapter= new ArrayAdapter<String>(this , android.R.layout.simple_dropdown_item_1line , list) ;
        listView.setAdapter(adapter);

        editName  = (EditText) findViewById(R.id.Edit_name);
        editno = (EditText) findViewById(R.id.Edit_no);
        editmajor = (EditText) findViewById(R.id.Edit_major);
        btn = (ImageButton) findViewById(R.id.btn);
        btn_show = (ImageButton) findViewById(R.id.btn_show);

        Dbr_user.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String value = dataSnapshot.getValue(String.class);
                list.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

                });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String name = editName.getText().toString();
                String no = editno.getText().toString();
                String major = editmajor.getText().toString();
                Dbr_user.push().setValue(no + " " + name + " " + major  );
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dbr_user.removeValue();
                list.clear();
                adapter.clear();
            }
        });
    }
}
