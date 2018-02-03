package com.example.sony.user_profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MY_TAG";
    EditText et1, et2, et3, et4;
    Button bt1, bt2;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.etusername);
        et2 = (EditText) findViewById(R.id.etid);
        et3 = (EditText) findViewById(R.id.etmobilenumber);
        et4 = (EditText) findViewById(R.id.etemail);


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
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login Successfully.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void register(View v) {

        String email = et4.getText().toString().trim();
        String uname =et1.getText().toString().trim();



        mAuth.createUserWithEmailAndPassword(email, uname)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        dodata();

    }


    public void dodata(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("myuserdata");
        DatabaseReference myRef1 = database.getReference("myemaildata");
        DatabaseReference myRef2 = database.getReference("myphonenumberdata");
        DatabaseReference myRef3 = database.getReference("myiddata");


        String email = et4.getText().toString().trim();
        String uname =et1.getText().toString().trim();
        String pname =et3.getText().toString().trim();
        String iname =et2.getText().toString().trim();

        myRef.setValue(uname);
        myRef1.setValue(email);
        myRef2.setValue(pname);
        myRef3.setValue(iname);

    }

  /*  public void docache(){


        AsyncMemcacheService asyncCache = MemcacheServiceFactory.getAsyncMemcacheService();
asyncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

String unkey = "unkey";
String emkey = "emailkey";
String phkey = "phnumkey";
String idkey = "idkey";

byte[] value;

String uname, emdata, phdata, iddata;

uname = et1.getText().toString();
emdata = et2.getText().toString();


long count = 1;
Future<Object> futureValue = asyncCache.get(key); // Read from cache.
// ... Do other work in parallel to cache retrieval.
try {
	    asyncCache.put(unkey , uname);
	    asyncCache.put(emkey, emdata);
	    asyncCache.put(phkey , phdata);
	    asyncCache.put(idkey, iddata);

  }
} catch (InterruptedException | ExecutionException e) {
  throw new ServletException("Error when waiting for future value", e);
}



/*

    asyncCache.get(unkey);
    asyncCache.get(emkey);
    asyncCache.get(phkey);
    asyncCache.get(idkey);


}
*/


}
