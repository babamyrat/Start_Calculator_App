package com.example.startcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

        public static final String ARG_NAME = "ARG_NAME";
        public static final String ARG_SURNAME = "ARG_SURNAME";
        public static final String ARG_ACCOUNT = "ARG_ACCOUNT";

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_second);

                TextView name = findViewById(R.id.name);
                TextView surname = findViewById(R.id.surname);

                EditText email = findViewById(R.id.email);

                Intent launchIntent = getIntent();

                User user = launchIntent.getParcelableExtra(ARG_ACCOUNT);

                name.setText(launchIntent.getStringExtra(ARG_NAME));
                surname.setText(launchIntent.getStringExtra(ARG_SURNAME));

                Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();

                findViewById(R.id.btn_action_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent data = new Intent();
                        user.setEmail(email.getText().toString());
                        data.putExtra(ARG_ACCOUNT, user);
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }
                });
            }
        }
