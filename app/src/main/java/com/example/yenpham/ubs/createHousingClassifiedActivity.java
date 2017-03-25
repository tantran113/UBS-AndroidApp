package com.example.yenpham.ubs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class createHousingClassifiedActivity extends AppCompatActivity {
    EditText housingInputTitle,housingInputNumBedroom,housingInputNumBathroom,housingInputLocation,
            housingInputDescription,housingInputPrice,housingInputSize,housingInputAvailability,
            housingInputZipcode;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button createHousingButton;
    Boolean availability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_housing_activity);
        createHousingButton = (Button)findViewById(R.id.createHousingButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if(radioButton.getText().toString().equals("yes"))
                    availability = true;
                else
                    availability = false;
              //  Toast.makeText(createHousingClassifiedActivity.this, availability.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        housingInputTitle = (EditText)findViewById(R.id.housingInputTitle);
        housingInputNumBedroom = (EditText)findViewById(R.id.housingInputNumBedroom);
        housingInputNumBathroom= (EditText)findViewById(R.id.housingInputNumBathroom);
        housingInputLocation= (EditText)findViewById(R.id.housingInputLocation);
        housingInputZipcode = (EditText)findViewById(R.id.housingInputZipcode);
        housingInputDescription= (EditText)findViewById(R.id.housingInputDescription);
        housingInputPrice = (EditText)findViewById(R.id.housingInputPrice);
        housingInputSize= (EditText)findViewById(R.id.housingInputSize);

        createHousingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle database
            }
        });


    }

}
