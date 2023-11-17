package com.example.ontapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class chucnangactivity extends AppCompatActivity {
  private  EditText ettsobaodanh, ettHoten,ettDiemToan,ettDiemLy,ettDiemHoa;
  private Button btnback,btnthem,btnsua,btnxoa;
  private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chucnang_activity);
        map();
        dbHelper = new DBHelper(this);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themThiSinh();
            }
        });

    }

    private void themThiSinh() {
        String soBaoDanh = ettsobaodanh.getText().toString().trim();
        String tenTS = ettHoten.getText().toString().trim();
        double diemToan = Double.parseDouble(ettDiemToan.getText().toString().trim());
        double diemLy = Double.parseDouble(ettDiemLy.getText().toString().trim());
        double diemHoa = Double.parseDouble(ettDiemHoa.getText().toString().trim());

        dbHelper.themThiSinh(soBaoDanh, tenTS, diemToan, diemLy, diemHoa);

        // Cập nhật lại danh sách và thông báo thành công
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        ettsobaodanh.setText("");
        ettHoten.setText("");
        ettDiemToan.setText("");
        ettDiemLy.setText("");
        ettDiemHoa.setText("");

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
    }
    public  void map(){
        ettsobaodanh = findViewById(R.id.ettsobaodanh);
        ettHoten = findViewById(R.id.ettHoten);
        ettDiemToan = findViewById(R.id.ettDiemToan);
        ettDiemLy = findViewById(R.id.ettDiemLy);
        ettDiemHoa = findViewById(R.id.ettDiemHoa);
        btnback = findViewById(R.id.btnback);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gửi kết quả về khi quay về
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
