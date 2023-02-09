package com.example.quran_log;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String Admin = "hadia";
    private static final String passwordAdmin = "hadia";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userName, pword;
        Button login,show;
        DBHandler db;
        login=findViewById(R.id.login);
        userName=findViewById(R.id.user_name);
        pword=findViewById(R.id.password);
        show=findViewById(R.id.showUser);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this, showUser.class);
                startActivity(intent);

            }
        });

        db=new DBHandler(this);
        login.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {
                String name=userName.getText().toString();
                String psword=pword.getText().toString();

                if(name.isEmpty()||psword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(name.equals(Admin)&&psword.equals(passwordAdmin))
                {
                    Intent intent;
                    intent = new Intent(MainActivity.this, adminView.class);
                    startActivity(intent);

                }
                else
                { Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    return;

                }

            }
        });




    }

}
