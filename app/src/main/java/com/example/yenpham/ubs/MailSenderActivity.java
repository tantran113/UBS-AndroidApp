package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MailSenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);
        final Button send = (Button) this.findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub


                new AsyncTask<Object, Object, Void>() {
                    @Override
                    public Void doInBackground(Object... arg) {

                        try {

                            Intent intent = getIntent();

                            String utemail = intent.getStringExtra("EMAIL");
                            int code = intent.getIntExtra("CODE", 10000);
                            String content = String.format("Your verification code is %d", code);
                            GmailSender sender = new GmailSender("ubs.team9@gmail.com", "abcdefghik");
                            sender.sendMail("Verification Code",
                                    content, "ubs.team9@gmail.com", utemail);
                            Intent vad = new Intent(MailSenderActivity.this, validation.class);
                            vad.putExtra("Ver_Code", code);
                            startActivity(vad);
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }

                        return null;
                    }
                }.execute();
            }
        });

    }
}
