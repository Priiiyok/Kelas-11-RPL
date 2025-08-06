package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edt_nama, edt_stok;
    private TextView tv_hasil;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NAMA_BARANG = "nama_barang";
    private static final String STOK_BARANG = "stok_barang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nama = findViewById(R.id.edt_nama);
        edt_stok = findViewById(R.id.edt_stok);
        tv_hasil = findViewById(R.id.tv_hasil);

        loadData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nama = sharedPreferences.getString(NAMA_BARANG, "");
        int stok = sharedPreferences.getInt(STOK_BARANG, 0);
        tv_hasil.setText("Hasil: Nama Barang - " + nama + ", Stok Barang - " + stok);
    }

    public void simpan(View view) {
        String nama = edt_nama.getText().toString();
        String stokStr = edt_stok.getText().toString();

        if (nama.isEmpty() || stokStr.isEmpty()) {
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        int stok = Integer.parseInt(stokStr);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAMA_BARANG, nama);
        editor.putInt(STOK_BARANG, stok);
        editor.apply();

        Toast.makeText(this, "Data Sudah di Simpan", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    public void tampil(View view) {
        loadData();
    }

    private void clearFields() {
        edt_nama.setText("");
        edt_stok.setText("");
    }
}