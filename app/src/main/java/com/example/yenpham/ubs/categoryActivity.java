package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class categoryActivity extends AppCompatActivity {
Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        nextButton=(Button) findViewById(R.id.nextButton);

    }

    public void radioButtonClick(View v){
        Boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
            case R.id.housingRadioButton:
                if (checked) {
                   // Toast.makeText(categoryActivity.this, "createHousingClassifiedActivity", Toast.LENGTH_SHORT).show();
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(categoryActivity.this,createHousingClassifiedActivity.class));
                        }
                    });
                }
                else{
                     Toast.makeText(categoryActivity.this, "Please select a categoryActivity", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bookRadioButton:

                if(checked){
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(categoryActivity.this,createForSaleClassifiedActivity.class));
                        }
                    });
                }
                else
                Toast.makeText(categoryActivity.this, "Please select a categoryActivity", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
