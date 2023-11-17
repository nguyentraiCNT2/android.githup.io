package com.example.ontapandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ontapandroid.model.ThiSinh;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ThiSinhDB";
    public static final String TABLE_NAME = "ThiSinh";
    public static final String COL_SO_BAO_DANH = "sobaodanh";
    public static final String COL_TEN_THI_SINH = "Tenthisnh";
    public static final String COL_DIEM_TOAN = "diemtoan";
    public static final String COL_DIEM_LY = "diemly";
    public static final String COL_DIEM_HOA = "diemhoa";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_SO_BAO_DANH + " TEXT, " +
                COL_TEN_THI_SINH + " TEXT, " +
                COL_DIEM_TOAN + " DOUBLE, " +
                COL_DIEM_LY + " DOUBLE, " +
                COL_DIEM_HOA + " DOUBLE) " ;
        db.execSQL(createTable);

        themDuLieuMau(db, "HHS132131", "Nguyen Van A", 5, 7.5,8.5);
        themDuLieuMau(db, "HHS132133", "Nguyen Van B", 6.7, 8,9);
    }



    private void themDuLieuMau(SQLiteDatabase db, String sobaodanh, String TenTS, double diemtoan, double diemly, double diemhoa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SO_BAO_DANH, sobaodanh);
        contentValues.put(COL_TEN_THI_SINH, TenTS);
        contentValues.put(COL_DIEM_TOAN, diemtoan);
        contentValues.put(COL_DIEM_LY, diemly);
        contentValues.put(COL_DIEM_HOA, diemhoa);
        db.insert(TABLE_NAME, null, contentValues);
    }
    public void themThiSinh(String soBaoDanh, String tenTS, double diemToan, double diemLy, double diemHoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SO_BAO_DANH, soBaoDanh);
        contentValues.put(COL_TEN_THI_SINH, tenTS);
        contentValues.put(COL_DIEM_TOAN, diemToan);
        contentValues.put(COL_DIEM_LY, diemLy);
        contentValues.put(COL_DIEM_HOA, diemHoa);

        db.insert(TABLE_NAME, null, contentValues);

    }
    public List<ThiSinh> GetAll() {
        List<ThiSinh> ls = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String soBaoDanh = cursor.getString(cursor.getColumnIndex(COL_SO_BAO_DANH));
                @SuppressLint("Range") String tenTS = cursor.getString(cursor.getColumnIndex(COL_TEN_THI_SINH));
                @SuppressLint("Range") double diemToan = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_TOAN));
                @SuppressLint("Range") double diemLy = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_LY));
                @SuppressLint("Range") double diemHoa = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_HOA));

                ls.add(new ThiSinh(soBaoDanh, tenTS, diemToan, diemLy, diemHoa));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ls;
    }
    public ThiSinh getThiSinh(String soBaoDanh) {
        ThiSinh thiSinh = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_SO_BAO_DANH + "=?";
            cursor = db.rawQuery(query, new String[]{soBaoDanh});

            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String sobaodanh = cursor.getString(cursor.getColumnIndex(COL_SO_BAO_DANH));
                @SuppressLint("Range") String tenTS = cursor.getString(cursor.getColumnIndex(COL_TEN_THI_SINH));
                @SuppressLint("Range") double diemtoan = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_TOAN));
                @SuppressLint("Range") double diemly = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_LY));
                @SuppressLint("Range") double diemhoa = cursor.getDouble(cursor.getColumnIndex(COL_DIEM_HOA));
                thiSinh = new ThiSinh(sobaodanh, tenTS, diemtoan, diemly, diemhoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return thiSinh;
    }
    public void suaThiSinh(String oldSoBaoDanh, String newSoBaoDanh, String tenTS, double diemToan, double diemLy, double diemHoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SO_BAO_DANH, newSoBaoDanh);
        values.put(COL_TEN_THI_SINH, tenTS);
        values.put(COL_DIEM_TOAN, diemToan);
        values.put(COL_DIEM_LY, diemLy);
        values.put(COL_DIEM_HOA, diemHoa);

        try {
            db.update(TABLE_NAME, values, COL_SO_BAO_DANH + " = ?", new String[]{oldSoBaoDanh});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    public void xoaThiSinh(String soBaoDanh) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(TABLE_NAME, COL_SO_BAO_DANH + " = ?", new String[]{soBaoDanh});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
