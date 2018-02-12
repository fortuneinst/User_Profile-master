package com.example.sony.user_profile;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MY_TAG";
    EditText et1, et2, et3, et4;
    Button bt1, bt2, bt3;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    DatabaseReference databasedetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedetails = FirebaseDatabase.getInstance().getReference("detail");

        et1 = (EditText) findViewById(R.id.etusername);
        et2 = (EditText) findViewById(R.id.etid);
        et3 = (EditText) findViewById(R.id.etmobilenumber);
        et4 = (EditText) findViewById(R.id.etemail);

        bt1 = (Button) findViewById(R.id.btlog);
        bt2 = (Button) findViewById(R.id.btreg);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(getApplicationContext(), "Loggedin As :" + user.getEmail(),
                            Toast.LENGTH_LONG).show();

                } else {
                    // User is signed out
                    Toast.makeText(getApplicationContext(), "User is Not Loggedin",
                            Toast.LENGTH_LONG).show();

                }
            }
        };
    }

    public void login(View v) {

        mAuth.signInWithEmailAndPassword(et1.getText().toString().trim(), et4.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login Successfully.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public void register(View v) {

        mAuth.createUserWithEmailAndPassword(et4.getText().toString().trim(), et1.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    }
                });

        String uname = et1.getText().toString().trim();
        String id = et2.getText().toString().trim();
        String mobile = et3.getText().toString().trim();
        String email = et4.getText().toString().trim();

        if (!TextUtils.isEmpty(uname)) {
            String id1 = databasedetails.push().getKey();
            Details details = new Details(uname, id, mobile, email);
            databasedetails.child(id1).setValue(details);

            Toast.makeText(this, "Data Successfully  added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "you should enter a name", Toast.LENGTH_LONG).show();
        }


    }


}



