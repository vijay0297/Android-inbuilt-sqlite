package com.example.vijay.sqliteapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editFeed,editMark;
    Button butAdd,butview,b1;
    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb=new DatabaseHelper(this);
        editName=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.get1);
        viewAll();
    }

    public void viewAll(){


        b1.setOnClickListener( new View.OnClickListener(){
                                        @Override
                                        public  void onClick(View view){
                                            Cursor res1=myDb.getAllData1(editName.getText().toString());
                                            if(res1.getCount()==0){
                                                ShowMessage("error","no data found");
                                                return;
                                            }

                                            StringBuffer buffer=new StringBuffer();
                                            while (res1.moveToNext()){

                                                buffer.append("Id :"+res1.getString(0)+"\n");
                                                buffer.append("Name :"+res1.getString(1)+"\n");
                                                buffer.append("College :"+res1.getString(2)+"\n");
                                                buffer.append("Events :"+res1.getString(3)+"\n\n");
                                            }
                                            ShowMessage("Queried Data",buffer.toString());
                                        }
                                    }
        );
    }
    public void ShowMessage(String title, String Mes){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Mes);
        builder.show();

    }
}



