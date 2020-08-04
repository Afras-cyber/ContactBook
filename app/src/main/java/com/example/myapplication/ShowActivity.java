package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView txt_name,txt_number;
    Button btn_pre;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_app:
                showMessage("About","e-ContectBook\nThis Anroid Application Created by"+
                        " Ahamed Afras\nStudent ID:KEG/IT/2018/F/0024");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        DataBase db = new DataBase(this);

        txt_name    =(TextView)findViewById(R.id.txt_name);
        txt_number  =(TextView)findViewById(R.id.txt_number);
        btn_pre     =(Button)findViewById(R.id.btn_home);

        String name= getIntent().getExtras().getString("name");

        Cursor cursor = db.thisContect(name);

        if(cursor.moveToNext()){
            txt_name.setText(cursor.getString(1));
            txt_number.setText(cursor.getString(2));
        }

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ShowActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void showMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}