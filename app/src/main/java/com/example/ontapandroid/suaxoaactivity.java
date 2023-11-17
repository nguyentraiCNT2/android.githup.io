package com.example.ontapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ontapandroid.model.ThiSinh;

public class suaxoaactivity extends AppCompatActivity {
    private EditText ettsobaodanh, ettHoten,ettDiemToan,ettDiemLy,ettDiemHoa;
    private Button btnback,btnthem,btnsua,btnxoa;
    private DBHelper dbHelper;
    private ThiSinh thiSinhToUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suaxoa_activity);
        map();
        dbHelper = new DBHelper(this);

        // Nhận thông tin thí sinh từ Intent
        String soBaoDanhToUpdate = getIntent().getStringExtra("SO_BAO_DANH_TO_UPDATE");
        if (soBaoDanhToUpdate != null) {
            thiSinhToUpdate = dbHelper.getThiSinh(soBaoDanhToUpdate);
            loadThiSinhData();
        }

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaThiSinh();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaThiSinh();
            }
        });

    }
    private void loadThiSinhData() {
        // Hiển thị thông tin thí sinh trên giao diện
        ettsobaodanh.setText(thiSinhToUpdate.getSobaodanh());
        ettHoten.setText(thiSinhToUpdate.getTenTS());
        ettDiemToan.setText(String.valueOf(thiSinhToUpdate.getDiemtoan()));
        ettDiemLy.setText(String.valueOf(thiSinhToUpdate.getDiemly()));
        ettDiemHoa.setText(String.valueOf(thiSinhToUpdate.getDiemhoa()));
    }

    private void suaThiSinh() {
        // Lấy thông tin từ EditText
        String soBaoDanh = ettsobaodanh.getText().toString().trim();
        String tenTS = ettHoten.getText().toString().trim();
        double diemToan = Double.parseDouble(ettDiemToan.getText().toString().trim());
        double diemLy = Double.parseDouble(ettDiemLy.getText().toString().trim());
        double diemHoa = Double.parseDouble(ettDiemHoa.getText().toString().trim());

        // Cập nhật thông tin thí sinh trong cơ sở dữ liệu
        dbHelper.suaThiSinh(thiSinhToUpdate.getSobaodanh(), soBaoDanh, tenTS, diemToan, diemLy, diemHoa);

        // Hiển thị thông báo thành công và kết thúc activity
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
    private void xoaThiSinh() {
        if (thiSinhToUpdate != null) {
            String soBaoDanhToDelete = thiSinhToUpdate.getSobaodanh();
            dbHelper.xoaThiSinh(soBaoDanhToDelete);
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }
public void map(){
    ettsobaodanh = findViewById(R.id.ettsobaodanh);
    ettHoten = findViewById(R.id.ettHoten);
    ettDiemToan = findViewById(R.id.ettDiemToan);
    ettDiemLy = findViewById(R.id.ettDiemLy);
    ettDiemHoa = findViewById(R.id.ettDiemHoa);
    btnback = findViewById(R.id.btnback);
    btnthem = findViewById(R.id.btnthem);
    btnsua = findViewById(R.id.btnsua);
    btnxoa = findViewById(R.id.btnxoa);
}

}
