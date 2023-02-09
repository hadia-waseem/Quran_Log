package com.example.quran_log;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Edit extends AppCompatActivity {
    EditText id,name,sabaq,sabqi,manzil,dateV;
    Button edit,delete;
    DBHandler db;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String nameH = intent.getStringExtra("name");
        String idH = intent.getStringExtra("id");
        String sabaqH = intent.getStringExtra("sabaq");
        String sabqiH = intent.getStringExtra("sabqi");
        String manzilH = intent.getStringExtra("manzil");
        String date = intent.getStringExtra("date");


        id=findViewById(R.id.hafizIDEdit);
        name=findViewById(R.id.hafizNameEdit);
        sabaq=findViewById(R.id.sabaqEdit);
        sabqi=findViewById(R.id.sabqiEdit);
        manzil=findViewById((R.id.manzilEdit));
        edit=findViewById(R.id.edit);
        delete=findViewById(R.id.delete);
        //       tv=findViewById(R.id.changeData);
//        dateV=findViewById(R.id.date);
        tv.setText("Modify data of "+nameH+" For date "+date);
        id.setText(idH);
        name.setText(nameH);
        id.setEnabled(false);
        name.setEnabled(false);

        sabaq.setText(sabaqH);
        sabqi.setText(sabqiH);
        manzil.setText(manzilH);



        db=new DBHandler(this);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log ld=new Log();
                ld.setStd_id(Integer.valueOf(idH));
                ld.setName(name.getText().toString());
                ld.setManzil(manzil.getText().toString());
                ld.setSabaq((sabaq.getText().toString()));
                ld.setSabqi(sabqi.getText().toString());
                ld.setDate(date);


                db.update(ld);
                Intent intent;
                intent = new Intent(Edit.this, showdetails.class);
                startActivity(intent);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(idH,date);
                Intent intent;
                intent = new Intent(Edit.this, showdetails.class);
                startActivity(intent);
            }
        });



    }
}

