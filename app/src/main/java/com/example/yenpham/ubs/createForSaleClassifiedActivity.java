package com.example.yenpham.ubs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createForSaleClassifiedActivity extends AppCompatActivity {
    EditText forsaleInputProductName, forsaleInputPrice,forsaleInputCondition
            ,forsaleInputCategoryName,forsaleInputDescription;
    Button forsaleCreateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_forsale_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        forsaleInputProductName = (EditText)findViewById(R.id.forsaleInputProductName);
        forsaleInputPrice = (EditText)findViewById(R.id.forsaleInputPrice);
        forsaleInputCondition =(EditText)findViewById(R.id.forsaleInputCondition);
        forsaleInputCategoryName = (EditText)findViewById(R.id.forsaleInputCategoryName);
        forsaleInputDescription = (EditText)findViewById(R.id.forsaleInputDescription);

        forsaleCreateButton = (Button)findViewById(R.id.forsaleCreateButton);
        forsaleCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // hnadle database
            }
        });
    }
}
