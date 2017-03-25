package com.example.yenpham.ubs;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static com.example.yenpham.ubs.R.id.email;


/**
 * Created by Yenpham on 11/3/16.
 */

public class login_gg extends AppCompatActivity implements
    View.OnClickListener,
    GoogleApiClient.OnConnectionFailedListener {

        private static final String TAG = MainActivity.class.getSimpleName();
        private static final int RC_SIGN_IN = 000;

        private GoogleApiClient mGoogleApiClient;
        private ProgressDialog mProgressDialog;

        private SignInButton btnSignIn;
        private Button btnSignOut, btnRevokeAccess;
        private LinearLayout llProfileLayout;
        private ImageView imgProfilePic;
        private TextView txtName, txtEmail;
        private boolean is_First;
        private UBSdb db;
        //add
        private Context context;

        // user infor;
        private String utemail;
        private int code;
        private String fN;
        private String lN;
        private String mav;
        private String phoneNum;
        private String dob;
        private String maj;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            setTheme(R.style.AppTheme);
            super.onCreate(savedInstanceState);

                setContentView(R.layout.login);
                Bundle intent = getIntent().getExtras();

                is_First=intent.getBoolean("IS_First_Time");
                if(is_First){

                    lN=intent.getString("Lname");
                    fN= intent.getString("Fname");
                    code= intent.getInt("Ver_Code");
                    phoneNum= intent.getString("Phone");
                    utemail= intent.getString("utemail");
                    mav= intent.getString("mavId");
                    dob= intent.getString("dob");
                    maj= intent.getString("major");
                }
                db= new UBSdb(this);
                btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
                btnSignOut = (Button) findViewById(R.id.btn_sign_out);
                btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
                llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
                imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
                txtName = (TextView) findViewById(R.id.txtName);
                txtEmail = (TextView) findViewById(R.id.txtEmail);

                btnSignIn.setOnClickListener(this);
                btnSignOut.setOnClickListener(this);
                btnRevokeAccess.setOnClickListener(this);

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .enableAutoManage(this, this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .addApi(AppIndex.API).build();

                // Customizing G+ button
                btnSignIn.setSize(SignInButton.SIZE_STANDARD);
                btnSignIn.setScopes(gso.getScopeArray());


        }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();

                Uri personPhotoUrl = acct.getPhotoUrl();

            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);

            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

            case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                // Get account information
                 String mFullName = acct.getDisplayName();
                String mEmail = acct.getEmail();
               SharePref sharePref;
                sharePref = SharePref.getInstance();
                sharePref.saveISLogged_IN(this, true);


                //compare register email here
                //if this is first time look for email doesnot exist

                if ((!is_First && db.GmailExist(mEmail)) ||is_First && !db.GmailExist(mEmail)){
                   int ID=-1;
                    if (!is_First) {
                        ID = db.getUserID(mEmail);
                    }else {


                            db.addUser(new User(db.getLastUserID()+1,fN, lN, dob, mav, phoneNum,
                                    utemail, mEmail, maj, String.valueOf(code)));
                            ID = db.getUserID(mEmail);

                    }
                    //go to menu activity
                    sharePref.saveUserID(this,ID);
                    Intent mainMenu = new Intent(login_gg.this, menu.class);
                    //mainMenu.putExtras( );
                    startActivity(mainMenu);
                    finish();

                }//if this is not the first time look for email exist if exist then have to do the other
                else if(is_First && db.GmailExist(mEmail) ){

                    String message = String.format("Gmail has been registered with UBS. Please try another account");
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                    updateUI(true);
                }
                else if (!is_First && !db.GmailExist(mEmail)){


                    String message = String.format("Gmail has never been registered with UBS. Please fill the registration form.");
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();


                    //save email to database

                    signOut();
                    //go to menu activity
                    Intent mainMenu = new Intent(login_gg.this, firstTimeLogin.class);
                    mainMenu.putExtra("Gmail", mEmail);
                    //mainMenu.putExtras( );
                    startActivity(mainMenu);
                    finish();

                }

            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("login_gg Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( mProgressDialog!=null && mProgressDialog.isShowing() ){
            mProgressDialog.cancel();
        }

    }


}

