package com.example.startcalculatorapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String argName = "";

    private final ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    User user = result.getData().getParcelableExtra(SecondActivity.ARG_ACCOUNT);
                    Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);


        if (getIntent() != null) {
            name.setText(getIntent().getStringExtra("name"));
            surname.setText(getIntent().getStringExtra("surname"));
        }

        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(name.getText().toString(), surname.getText().toString());

                argName = user.getName();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(SecondActivity.ARG_NAME, user.getName());
                intent.putExtra(SecondActivity.ARG_SURNAME, user.getSurname());
                intent.putExtra(SecondActivity.ARG_ACCOUNT, user);

                activityResult.launch(intent);

            }
        });

        Button button = findViewById(R.id.btn_link);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.app.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent);
            }
        });

    }




}