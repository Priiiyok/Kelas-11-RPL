package com.example.counter; // Package sesuai dengan yang Anda berikan

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge; // Menggunakan EdgeToEdge yang Anda sebutkan
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCounter; // Deklarasi TextView untuk menampilkan angka
    private int counter = 0; // Variabel untuk menyimpan nilai counter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Mengaktifkan mode edge-to-edge

        setContentView(R.layout.activity_main); // Menghubungkan layout XML

        // Ini adalah bagian dari setup EdgeToEdge. Biarkan saja jika Anda ingin efek padding otomatis.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi elemen UI dari layout
        textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonUp = findViewById(R.id.buttonUp);
        Button buttonDown = findViewById(R.id.buttonDown);

        // Mengatur listener untuk tombol "Counter Up"
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++; // Tambah nilai counter
                textViewCounter.setText(String.valueOf(counter)); // Perbarui teks
            }
        });

        // Mengatur listener untuk tombol "Counter Down"
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--; // Kurangi nilai counter
                textViewCounter.setText(String.valueOf(counter)); // Perbarui teks
            }
        });
    }
}