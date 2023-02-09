package com.example.quran_log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class adminView extends AppCompatActivity {
    EditText id,name,sabaq,sabqi,manzil;
    Button insert,commit,show;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        id=findViewById(R.id.hafizID);
        name=findViewById(R.id.hafizName);
        sabaq=findViewById(R.id.sabaq);
        sabqi=findViewById(R.id.sabqi);
        manzil=findViewById((R.id.manzil));
        insert=findViewById(R.id.Insert);
        commit=findViewById(R.id.Commit);
        show=findViewById(R.id.Show);
        db=new DBHandler(this);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://github.com/hadia-waseem/Quran_Log.git");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(adminView.this, showdetails.class);
                startActivity(intent);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Integer LogId=Integer.parseInt(id.getText().toString());
                String Name=name.getText().toString();
                String sbq=sabaq.getText().toString();
                String sbqi=sabqi.getText().toString();
                String manzl=manzil.getText().toString();
                if(Name.isEmpty()||sbq.isEmpty()||sbqi.isEmpty()||manzl.isEmpty())
                {
                    Toast.makeText(adminView.this, "Please enter all credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log l1=new Log(LogId,Name,sbq,sbqi,manzl,date);
                try{
                    Integer res=db.insertLOG(l1);
                    android.util.Log.d("Insert", res.toString());
                    if(res==-1)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(adminView.this);
                        builder.setMessage("Either Student doesn't exist in system or  already been logged Today");
                        builder.setTitle("Invalid Entry");
                        builder.setCancelable(true);
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int asdf)
                                    { dialog.cancel();;}
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                    android.util.Log.d("LogA", "Id " +LogId+Name+sbq+sbqi+manzl+date);
                    id.setText("");
                    name.setText("");

                }
                catch (Exception e)
                {
                    Toast.makeText(adminView.this, "Log Data couldn't be Inserted", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });



    }
}