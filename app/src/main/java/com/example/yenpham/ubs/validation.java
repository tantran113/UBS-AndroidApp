package com.example.yenpham.ubs;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;



import android.content.Context;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class validation extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.OnConnectionFailedListener{


    public EditText verificationcode;
    public Button submit;
    public Button cancel;


    private EditText codeField;
    private Button Submit;
    private int our_code;
    boolean success = false;
    int user_code;
    String codeStr;
    int count = 0;

    //user infor
    // user infor;
    private String utemail;
    private int code;
    private String fN;
    private String lN;
    private String mav;
    private String phoneNum;
    private String dob;
    private String maj;
    private String gmail;
    private UBSdb db;


    private GoogleApiClient client;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .addApi(AppIndex.API).build();



        db = new UBSdb(this);
        Bundle intent = getIntent().getExtras();
        our_code = intent.getInt("Ver_Code");
        lN=intent.getString("Lname");
        fN= intent.getString("Fname");
        code= intent.getInt("Ver_Code");
        phoneNum= intent.getString("Phone");
        utemail= intent.getString("utemail");
        mav= intent.getString("mavId");
        dob= intent.getString("dob");
        maj= intent.getString("major");
        SharePref sharePref = SharePref.getInstance();
        if(sharePref.getISLogged_IN(this)){
            gmail = intent.getString("Gmail");
        }
        codeField = (EditText) findViewById(R.id.veriCode);

        count = 0;


        Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(this);


        cancelation();
    }


    public void cancelation() {
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {


                                          Intent move = new Intent(validation.this, welcom_class.class);
                                          startActivity(move);
                                          finish();


                                      }
                                  }
        );
    }



        @Override
        public void onClick(View view) {
            codeStr = codeField.getText().toString();
            count ++;
           // try {
                SharePref sharePref = SharePref.getInstance();

                user_code= Integer.parseInt(codeStr);
                if (our_code == user_code) {
                    if(!sharePref.getISLogged_IN(this)) {
                        success = true;
                        Intent vad = new Intent(validation.this, login_gg.class);
                        vad.putExtra("Lname", lN);
                        vad.putExtra("Fname", fN);
                        vad.putExtra("Phone", phoneNum);
                        vad.putExtra("Ver_Code", code);
                        vad.putExtra("utemail", utemail);
                        vad.putExtra("mavId", mav);
                        vad.putExtra("dob", dob);
                        vad.putExtra("major", maj);
                        vad.putExtra("IS_First_Time", true);
                        startActivity(vad);
                        finish();
                    }else if (sharePref.getISLogged_IN(this)){
                        success = true;
                        int ID=-1;
                            User user = new User(db.getLastUserID()+1,fN, lN, dob,mav, phoneNum,
                                utemail, gmail, maj, String.valueOf(code));

                            db.addUser(user);
                            ID = db.getUserID(gmail);


                     //   go to menu activity
                        sharePref.saveUserID(this,ID);
                        Intent mainMenu = new Intent(validation.this, menu.class);
                        //mainMenu.putExtras( );
                        startActivity(mainMenu);
                        finish();

                    }
                } else {
                    codeField.setError("Invalid Code");
                    success = false;
                }

//            } catch (Exception e) {
//                codeField.setError("Invalid Code");
//                success = false;
//            }

            if (!success && count >=3){
                String message = String.format("Failed to be a UTA student.");
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
                if(sharePref.getISLogged_IN(this)){
                    signOut();
                }
                Intent vad = new Intent(validation.this, welcom_class.class);
                startActivity(vad);
                finish();
            }



        }


    public void signOut() {
        Auth.GoogleSignInApi.signOut(client).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        SharePref sharePref;

                        sharePref = SharePref.getInstance();
                        SharedPreferences.Editor editor = sharePref.sharepreferences.edit();
////                        SharedPreferences sharedPreferences = getSharedPreferences(SyncStateContract.Constants.ACCOUNT_NAME, MODE_PRIVATE);
////                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        if (sharePref.getISLogged_IN(validation.this)) {
                            client.disconnect();
                            sharePref.saveISLogged_IN(validation.this,false);
                            Intent wel = new Intent(validation.this, welcom_class.class);
                            startActivity(wel);
                            finish();

                        } else {

                            Intent intent = new Intent(validation.this, welcom_class.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                });
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("menu Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

    }


}