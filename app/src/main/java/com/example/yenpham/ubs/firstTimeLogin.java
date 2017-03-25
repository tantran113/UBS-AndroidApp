package com.example.yenpham.ubs;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;


import java.lang.String;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static android.R.attr.duration;


public class firstTimeLogin extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText phone;
    private EditText UTAEmail;
    private EditText mavID;
    private EditText BOB;
    private EditText major;
    private Button submit;
    private Dialog check;
    private String utemail;
    private int code;
    private Button cancel;
    private String fN;
    private String lN;
    private String mav;
    private String phoneNum;
    private String dob;
    private String maj;
    private String gmail;

    private UBSdb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_login);
        cancelRegistration ();
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        phone = (EditText) findViewById(R.id.phone);
        UTAEmail = (EditText) findViewById(R.id.utaEmail);
        mavID = (EditText) findViewById(R.id.mavId);
        BOB = (EditText) findViewById(R.id.dob);
        major = (EditText) findViewById(R.id.major);
        submit = (Button) findViewById(R.id.submit);
        db = new UBSdb(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = true;
                 fN = fName.getText().toString();
                if (fN.length() == 0) {
                    fName.setError("Missing Last Name");
                    success = false;
                }

                 lN = lName.getText().toString();
                if (lN.length() == 0) {
                    lName.setError("Missing Last Name");
                    success = false;

                }

                  phoneNum = phone.getText().toString();
                int phoneLeght = phoneNum.length();
                if (phoneLeght == 0) {
                    phone.setError("Missing Phone Number");
                    success = false;

                } else if (!check_phone(phoneNum, phoneLeght)) {

                    phone.setError("Invalid Phone Number");
                    success = false;

                }
                 utemail = UTAEmail.getText().toString();
                if (utemail.length() == 0) {
                    UTAEmail.setError("Missing UTA email");
                    success = false;
                }
                 else if (!isUTAEmail(utemail)) {
                    UTAEmail.setError("Invalid UTA email");
                    success = false;

              }else if (db.MavMailExist(utemail)){
                    UTAEmail.setError("UTA email has been used to register.");
                    success = false;
                }
                 mav = mavID.getText().toString();
                if (mav.length() == 0) {
                    mavID.setError("missing mavID");
                    success = false;


                } else if (!check_MavID(mav, mav.length())) {
                    mavID.setError("Invalid mavID");
                    success = false;

                }
                 String dobStr = BOB.getText().toString();
                if (dobStr.length() == 0) {
                    BOB.setError("Missing DOB");
                    success = false;

                }else if(!validDate(dobStr, dobStr.length())) {
                    BOB.setError("Invalid Day");
                    success = false;

                    //convert to date of database
                }
                        MyDate d = new MyDate();
                        d.setDate(dobStr);
                        dob= d.getYear()+"-"+d.getMonth()+"-"+d.getDay();


                 maj = major.getText().toString();

                if (success) {
                     code = generatecode();
                    String message = "Check your UTA Email for verification code";
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                    new AsyncTask<Object, Object, Void>() {
                        @Override
                        public Void doInBackground(Object... arg) {

                            try {

                                String content = String.format("Your verification code is %d", code);
                                GmailSender sender = new GmailSender("ubs.team9@gmail.com", "abcdefghik");
                                sender.sendMail("Verification Code",
                                        content, "ubs.team9@gmail.com", utemail);

                                Intent vad = new Intent(firstTimeLogin.this, validation.class);
                                vad.putExtra("Lname",lN);
                                vad.putExtra("Fname",fN);
                                vad.putExtra("Phone",phoneNum);
                                vad.putExtra("Ver_Code", code);
                                vad.putExtra("utemail",utemail);
                                vad.putExtra("mavId",mav);
                                vad.putExtra("dob",dob);
                                vad.putExtra("major",maj);
                                SharePref sharePref = SharePref.getInstance();
                                if(sharePref.getISLogged_IN(firstTimeLogin.this)){
                                    Bundle bundle = getIntent().getExtras();
                                    gmail =  bundle.getString("Gmail");
                                    vad.putExtra("Gmail",gmail);
                                }

                                startActivity(vad);
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }

                            return null;
                        }
                    }.execute();


                }


            }
        });
    }

    private int generatecode() {
        Random r = new Random();
        int i = r.nextInt(99999 - 10000) + 10000;
        return i;
    }

    private static boolean check_phone(String str, int leght) {
        int i = 0;
        if (leght!=10)
            return false;
        for (i = 0; i < leght; i++) {
            if ("1234567890".indexOf(str.substring(i,i+1)) < 0 ) {
               return false;
            }
        }
        return true;
    }

    private static boolean check_MavID(String str, int leght) {
        int i = 0;
        if (leght!=10)
            return false;
        for (i = 0; i < leght; i++) {
           if ("1234567890".indexOf(str.substring(i,i+1)) < 0) {
                return false;
            }
        }
        return true;
    }
    private Boolean validDate(String date, int length){

        MyDate d = new MyDate();
        d.setDate(date);
        if (d.isValid())
            return true;
        return false;
    }
    private boolean isUTAEmail(String email) {
        boolean result = false;

        String temp[] = email.split("@");
        if (temp.length >= 2) {


            if (temp[1] != null && temp[1].equals("mavs.uta.edu"))
                result = true;
        }
        return result;
    }

    public void cancelRegistration (){
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent cancelReg= new Intent(firstTimeLogin.this, welcom_class.class);
                startActivity(cancelReg);
                finish();
            }

        });
    }

}

