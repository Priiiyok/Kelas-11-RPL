package com.example.intentactivity; // Pastikan package sesuai dengan nama package Anda

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast; // Untuk menampilkan pesan singkat

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton; // Import MaterialButton
import com.google.android.material.textfield.TextInputEditText; // Import TextInputEditText

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextNamaBarang; // Menggunakan TextInputEditText
    private MaterialButton buttonBarang;
    private MaterialButton buttonPenjualan;
    private MaterialButton buttonPembelian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Menyesuaikan padding untuk System Bars (StatusBar, NavigationBar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi Views dari layout
        editTextNamaBarang = findViewById(R.id.editTextNamaBarang);
        buttonBarang = findViewById(R.id.buttonBarang);
        buttonPenjualan = findViewById(R.id.buttonPenjualan);
        buttonPembelian = findViewById(R.id.buttonPembelian);

        // Listener untuk tombol "Barang"
        buttonBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaBarang = editTextNamaBarang.getText().toString().trim(); // Ambil teks dan hapus spasi di awal/akhir

                if (namaBarang.isEmpty()) {
                    // Tampilkan pesan jika input kosong
                    Toast.makeText(MainActivity.this, "Silakan masukkan nama barang!", Toast.LENGTH_SHORT).show();
                } else {
                    // Buat Intent untuk membuka BarangActivity
                    Intent intent = new Intent(MainActivity.this, Barang.class);
                    // Kirim data "nama barang" ke BarangActivity
                    intent.putExtra("KEY_NAMA_BARANG", namaBarang); // KEY_NAMA_BARANG adalah kunci untuk mengambil data
                    startActivity(intent); // Mulai Activity baru
                }
            }
        });

        // Listener untuk tombol "Penjualan" (contoh, belum ada Activity Penjualan)
        buttonPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tombol Penjualan Diklik!", Toast.LENGTH_SHORT).show();
                // TODO: Tambahkan Intent untuk membuka PenjualanActivity di sini
                // Intent intent = new Intent(MainActivity.this, PenjualanActivity.class);
                // startActivity(intent);
            }
        });

        // Listener untuk tombol "Pembelian" (contoh, belum ada Activity Pembelian)
        buttonPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tombol Pembelian Diklik!", Toast.LENGTH_SHORT).show();
                // TODO: Tambahkan Intent untuk membuka PembelianActivity di sini
                // Intent intent = new Intent(MainActivity.this, PembelianActivity.class);
                // startActivity(intent);
            }
        });
    }
}