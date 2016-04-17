package com.revolutionuc.donut.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.revolutionuc.donut.R;
import com.revolutionuc.donut.View.HomeActivity;

public class LoginActivity extends Activity {


    Button b1,b2;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1=(Button)findViewById(R.id.button);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void login(View view) {
        if(ed1.getText().toString().equals("user") && ed2.getText().toString().equals("user")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(getApplicationContext(), "Wrong Credentials, Try Again",Toast.LENGTH_SHORT).show();
        }
    }
}

