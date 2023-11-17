package com.example.ontapandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ontapandroid.model.ThiSinh;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvthisinh;
    private Button btncreate;
    private List<ThiSinh> listthisinh ;
    private ThiSinhAdapter thiSinhAdapter;
    private static final int REQUEST_CODE_THEM_THI_SINH = 1;
    private static final int REQUEST_CODE_SUA_THI_SINH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
        listthisinh = GetAll();
        updateListView(listthisinh);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chucnangactivity.class);
                startActivityForResult(intent, REQUEST_CODE_THEM_THI_SINH);
            }
        });
        lvthisinh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ThiSinh selectedThiSinh = listthisinh.get(position);

                Intent intent = new Intent(MainActivity.this, suaxoaactivity.class);
                intent.putExtra("SO_BAO_DANH_TO_UPDATE", selectedThiSinh.getSobaodanh());
                startActivityForResult(intent, REQUEST_CODE_SUA_THI_SINH);

                return true;
            }
        });

        thiSinhAdapter = new ThiSinhAdapter(this, listthisinh);
        lvthisinh.setAdapter(thiSinhAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_THEM_THI_SINH) {
            if (resultCode == RESULT_OK) {
                listthisinh = GetAll();
                thiSinhAdapter.clear();
                thiSinhAdapter.addAll(listthisinh);
                thiSinhAdapter.notifyDataSetChanged();
            }
        }if (requestCode == REQUEST_CODE_SUA_THI_SINH) {
            if (resultCode == RESULT_OK) {
                listthisinh = GetAll();
                thiSinhAdapter.clear();
                thiSinhAdapter.addAll(listthisinh);
                thiSinhAdapter.notifyDataSetChanged();
            }
        }
    }


    private List<ThiSinh> GetAll() {
        List<ThiSinh> ls = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(this);
        ls = dbHelper.GetAll();
        return ls;
    }
    private void updateListView(List<ThiSinh> danhSach) {
        thiSinhAdapter = new ThiSinhAdapter(this, danhSach);
        lvthisinh.setAdapter(thiSinhAdapter);
    }
    public void map(){
        lvthisinh = findViewById(R.id.lvthisinh);
        btncreate = findViewById(R.id.btncreate);
    }
}