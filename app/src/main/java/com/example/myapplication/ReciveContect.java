package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReciveContect extends AppCompatActivity {

    EditText edt_name,edt_number;
    Button btn_saveme;
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
        setContentView(R.layout.activity_recive_contect);

        final DataBase db = new DataBase(this);

        edt_name    =(EditText)findViewById(R.id.edt_name);
        edt_number  =(EditText)findViewById(R.id.edt_number);
        btn_saveme  =(Button)findViewById(R.id.btn_save_me);



        btn_saveme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_name.getText().toString().trim().isEmpty()){
                    Toast.makeText(ReciveContect.this, "Empty Name", Toast.LENGTH_SHORT).show();
                }
                else if(edt_number.getText().toString().trim().isEmpty()){
                    Toast.makeText(ReciveContect.this, "Empty Number", Toast.LENGTH_SHORT).show();
                }else{
                    String number_value=edt_number.getText().toString().trim();
                    int number_val= Integer.parseInt(number_value);

                   int finalvalue= countDigit(number_val);

                    if (finalvalue <= 10 ){
                        Toast.makeText(ReciveContect.this, "Number Should be 10 degit", Toast.LENGTH_SHORT).show();
                    }else{

                        boolean test= db.insertData(edt_name.getText().toString(),edt_number.getText().toString());
                        if(test){
                            Toast.makeText(ReciveContect.this,"Contect Saved",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(ReciveContect.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(ReciveContect.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                }



            }
        });

    }
    private void showMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.show();
    }
    public int countDigit(int n)
    {
        return (int)Math.floor(Math.log10(n) + 1);
    }
}