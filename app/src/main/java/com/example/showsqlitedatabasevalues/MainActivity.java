package com.example.showsqlitedatabasevalues;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editname,editsurname,editmarks;

    Button addButton;

    Button viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);


        editname = findViewById(R.id.editname);
        editsurname = findViewById(R.id.editsurname);
        editmarks = findViewById(R.id.editmarks);
        addButton = findViewById(R.id.addButton);

        viewButton = findViewById(R.id.viewButton);
        addData();
        viewAll();



    }
    public void addData() {
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertdata(editname.getText().toString(),
                                editsurname.getText().toString(), editmarks.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data is Inserted ", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data is not Inserted ", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void viewAll(){
        viewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =  mydb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer  = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id : " +res.getString(0)+"\n");
                            buffer.append("Name : " +res.getString(1)+"\n");
                            buffer.append("SurName : " +res.getString(2)+"\n");
                            buffer.append("Marks : " +res.getString(3)+"\n\n");

                        }
                        showMessage("Data",buffer.toString());
                    }
                }

        );
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}


