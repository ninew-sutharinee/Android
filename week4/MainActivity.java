package com.example.win10v3.test_android;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.internal.Internal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference Dbr_user = firebaseDatabase.getReference("User");
    //อ่านข้อมูลในโหนด User

    private  EditText id_user , id_pass;
    private FirebaseAuth mFirebaseAuth;
    private  Button btn_l , btn_r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        id_user = (EditText)findViewById(R.id.id_user);
        id_pass = (EditText)findViewById(R.id.id_pass);
        btn_l = (Button)findViewById(R.id.btn_login);
        btn_r = (Button)findViewById(R.id.btn_resister);

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resister();
            }
        });

        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void resister(){
        String username = id_user.getText().toString();
        String password = id_pass.getText().toString();


        if(!username.isEmpty() && !password.isEmpty()){

            mFirebaseAuth.createUserWithEmailAndPassword(username , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Toast.makeText(MainActivity.this , "Resister Success !" , Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MainActivity.this , "Resister Fail !" , Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private void Login(){
        String username = id_user.getText().toString();
        String password = id_pass.getText().toString();


        if(!username.isEmpty() && !password.isEmpty()){

            mFirebaseAuth.signInWithEmailAndPassword(username , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    LoginPage();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MainActivity.this , "Login Fail !" , Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void LoginPage(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }


}
