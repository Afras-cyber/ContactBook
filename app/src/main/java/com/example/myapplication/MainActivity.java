package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fbtn;
    EditText edt_seachbar;
    ArrayAdapter adapter;

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
        setContentView(R.layout.activity_main);

        final DataBase dataBase = new DataBase(this);


        listView=(ListView) findViewById(R.id.listView);
        fbtn    =(FloatingActionButton)findViewById(R.id.fbtn);

        final ArrayList<String> contectlist= new ArrayList<>();

        Cursor cursor = dataBase.allData();
        while(cursor.moveToNext()){
            contectlist.add(cursor.getString(1));
        }
        if(cursor.getCount()==0){
    //            showMessage("Error","Contect List Empty");
            Toast.makeText(this, "Contect List is Empty", Toast.LENGTH_LONG).show();
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contectlist);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        seachBar();
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ReciveContect.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, contectlist.get(i)+" selected", Toast.LENGTH_SHORT).show();
                Intent transer_int= new Intent(MainActivity.this,ShowActivity.class);
                String name=contectlist.get(i);
                transer_int.putExtra("name",name);
                startActivity(transer_int);
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
    public void seachBar(){
        edt_seachbar=(EditText)findViewById(R.id.edt_search);
        edt_seachbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MainActivity.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}