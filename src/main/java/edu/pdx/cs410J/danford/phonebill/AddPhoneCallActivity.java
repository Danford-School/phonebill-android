package edu.pdx.cs410J.danford.phonebill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.ParseException;

public class AddPhoneCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_call);

        Button cancel = findViewById(R.id.CancelAndGoBack);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPhoneCallActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        Button makeNewPhoneCall = findViewById(R.id.MakeNewPhoneCall);
        makeNewPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Customer = findViewById(R.id.Customer);
                EditText Caller = findViewById(R.id.Caller);
                EditText Callee = findViewById(R.id.Callee);
                EditText StartDate = findViewById(R.id.StartDate);
                EditText StartTime = findViewById(R.id.StartTime);
                EditText EndDate = findViewById(R.id.EndDate);
                EditText EndTime = findViewById(R.id.EndTime);
                RadioButton StartAMPM = findViewById(R.id.StartAMPM);
                RadioButton EndAMPM = findViewById(R.id.EndAMPM);

                String theCustomer = Customer.getText().toString();
                String theCaller = Caller.getText().toString();
                String theCallee = Callee.getText().toString();
                String theStartDate = StartDate.getText().toString();
                String theStartTime = StartTime.getText().toString();
                String theEndDate = EndDate.getText().toString();
                String theEndTime = EndTime.getText().toString();
                String theStartAMPM;
                if(StartAMPM.isChecked()) {
                    theStartAMPM = "pm";
                } else {
                    theStartAMPM = "am";
                }
                String theEndAMPM;
                if(EndAMPM.isChecked()) {
                    theEndAMPM = "pm";
                } else {
                    theEndAMPM = "am";
                }
                PhoneCall call = null;

                try {
                    call = new PhoneCall(theCustomer, theCaller, theCallee, theStartDate,
                            theStartTime, theStartAMPM, theEndDate, theEndTime, theEndAMPM);
                } catch (ParseException e) {
                    //popup somehow
                }
                Intent intent = new Intent(AddPhoneCallActivity.this, PhoneBillDataBase.class);
                intent.putExtra("PhoneCall", call);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}