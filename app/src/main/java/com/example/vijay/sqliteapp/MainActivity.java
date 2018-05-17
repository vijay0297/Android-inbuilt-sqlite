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

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editFeed,editMark,editid;
    Button butAdd,butview,b1,bdelete;
    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        editName=(EditText)findViewById(R.id.editText_name);
        editFeed=(EditText)findViewById(R.id.editText_feed);
        editMark=(EditText)findViewById(R.id.editText_mark);
        butAdd=(Button)findViewById(R.id.button_add);
        butview=(Button)findViewById(R.id.button_view);
        b1=(Button)findViewById(R.id.new1);
        bdelete=(Button)findViewById(R.id.delete);
        AddData();
        viewAll();
        delete();
    }
    public  void delete(){
        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete=myDb.delete(editName.getText().toString());
                if(delete !=0) {
                    Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_LONG).show();
                }
             else{
                    Toast.makeText(MainActivity.this,"data not deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(){


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted = myDb.insertData(editName.getText().toString(),editFeed.getText().toString(),
                        editMark.getText().toString());
             if(isInserted= true)
                 Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
             else
                 Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();
            }
        }




        );
            }

 public void viewAll(){
     butview.setOnClickListener( new View.OnClickListener(){
         @Override
         public  void onClick(View view){
             Cursor res=myDb.getAllData();
             if(res.getCount()==0){
                 ShowMessage("error","no data found");
                 return;
             }

             StringBuffer buffer=new StringBuffer();
             while (res.moveToNext()){
                 buffer.append("Id :"+res.getString(0)+"\n");
                 buffer.append("Name :"+res.getString(1)+"\n");
                 buffer.append("College :"+res.getString(2)+"\n");
                 buffer.append("Events :"+res.getString(3)+"\n\n");
             }
             ShowMessage("Data",buffer.toString());
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



