package com.example.termometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log; // Import Log untuk melihat output di Logcat
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNilai;
    private Spinner spinner;
    private Button btnKonversi;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen UI
        etNilai = findViewById(R.id.etNilai);
        spinner = findViewById(R.id.spinner);
        btnKonversi = findViewById(R.id.btnKonversi);
        tvHasil = findViewById(R.id.tvHasil);

        // Panggil metode untuk mengisi Spinner
        isiSpinner();

        // Atur OnClickListener untuk tombol Konversi
        btnKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    // Metode untuk mengisi Spinner dengan pilihan konversi
    private void isiSpinner() {
        // Mendapatkan array string dari resources
        String[] pilihanKonversi = getResources().getStringArray(R.array.pilihan_konversi);

        // Membuat ArrayAdapter menggunakan array string dan layout default spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Layout default untuk item spinner
                pilihanKonversi
        );

        // Menentukan layout yang akan digunakan saat daftar pilihan muncul
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Menerapkan adapter ke Spinner
        spinner.setAdapter(adapter);
    }

    // Metode untuk melakukan konversi berdasarkan pilihan Spinner
    private void performConversion() {
        String nilaiInputStr = etNilai.getText().toString();

        if (nilaiInputStr.isEmpty()) {
            Toast.makeText(this, "Harap masukkan nilai suhu", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double nilaiInput = Double.parseDouble(nilaiInputStr);
            String pilihanTerpilih = spinner.getSelectedItem().toString();
            double hasilKonversi = 0.0;

            // Log pilihan yang dipilih (seperti di video)
            Log.d("KonversiSuhu", "Pilihan yang dipilih: " + pilihanTerpilih);

            // Logika konversi (Anda bisa mengembangkan ini lebih lanjut)
            switch (pilihanTerpilih) {
                case "Celsius To Reamur":
                    hasilKonversi = nilaiInput * 0.8;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Reamur");
                    break;
                case "Celsius To Fahrenheit":
                    hasilKonversi = (nilaiInput * 9/5) + 32;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Fahrenheit");
                    break;
                case "Celsius To Kelvin":
                    hasilKonversi = nilaiInput + 273.15;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Kelvin");
                    break;
                case "Reamur To Celsius":
                    hasilKonversi = nilaiInput / 0.8;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Celsius");
                    break;
                case "Fahrenheit To Celsius":
                    hasilKonversi = (nilaiInput - 32) * 5/9;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Celsius");
                    break;
                case "Kelvin To Celsius":
                    hasilKonversi = nilaiInput - 273.15;
                    tvHasil.setText("Hasil Konversi: " + String.format("%.2f", hasilKonversi) + " Celsius");
                    break;
                default:
                    tvHasil.setText("Pilihan konversi tidak valid.");
                    break;
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input tidak valid. Harap masukkan angka.", Toast.LENGTH_SHORT).show();
        }
    }
}