package com.example.yenpham.ubs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class menu extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener{
    protected Button us;
    protected Button logout;
    protected Button club,classifieds;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final String TAG = MainActivity.class.getSimpleName();


    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .addApi(AppIndex.API).build();

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

            }
        });
        moveToUs();
        moveToClub();
        moveToClassified();



        MobileAds.initialize(getApplicationContext(), "@string/banner_ad_unit_id");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void moveToClassified() {
        classifieds = (Button)findViewById(R.id.classified);
        classifieds.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(menu.this, classifiedActivity.class);
                startActivity(reg);
            }

        });
    }

    private void moveToClub() {
        club = (Button)findViewById(R.id.formClub);
        club.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(menu.this, joinedClubActivity.class);
                startActivity(reg);
            }

        });
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
                        if (sharePref.getISLogged_IN(menu.this)) {
                            client.disconnect();
                            sharePref.saveISLogged_IN(menu.this,false);
                            Intent wel = new Intent(menu.this, welcom_class.class);
                            startActivity(wel);
                            finish();

                        } else {

                            Intent intent = new Intent(menu.this, welcom_class.class);
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



    public void moveToUs (){
        us = (Button)findViewById(R.id.aboutUs);
        us.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent reg= new Intent(menu.this, aboutUS.class);
                startActivity(reg);
            }

        });
    }
    @Override
    public void onBackPressed() {
       signOut();
    }



}
