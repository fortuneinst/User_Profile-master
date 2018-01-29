package com.example.sony.user_profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button bt1,bt2;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.etuname);
        et2 = (EditText)findViewById(R.id.etid);
        et3 = (EditText)findViewById(R.id.etmnumber);
        et4 = (EditText)findViewById(R.id.etemail);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out
                    Toast.makeText(getApplicationContext(),"User is Not Loggedin",
                            Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public void login(View v){



    }

    public void register(View v){

    }
}
