package com.example.cardviewbaru;
// MainActivity.java
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cardviewbaru.SiswaAdapter;
import com.example.cardviewbaru.model.Siswa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SiswaAdapter siswaAdapter;
    private List<Siswa> siswaList;
    private Button myButton;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        myButton = findViewById(R.id.myButton);
        btnTambah = findViewById(R.id.btnTambah);

        // Inisialisasi daftar siswa
        siswaList = new ArrayList<>();
        // Tambahkan beberapa data contoh
        siswaList.add(new Siswa("Budi Santoso", "Jl. Merdeka No. 10"));
        siswaList.add(new Siswa("Siti Aminah", "Jl. Sudirman No. 25"));
        siswaList.add(new Siswa("Joko Susilo", "Jl. Pahlawan No. 3"));
        siswaList.add(new Siswa("Dewi Lestari", "Jl. Raya No. 100"));
        siswaList.add(new Siswa("Agus Salim", "Jl. Kebon Jeruk No. 7"));


        // Inisialisasi adapter
        siswaAdapter = new SiswaAdapter(this, siswaList);

        // Atur LayoutManager untuk RecyclerView (misalnya, LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Atur adapter ke RecyclerView
        recyclerView.setAdapter(siswaAdapter);

        // Tambahkan OnClickListener untuk myButton
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "My Button diklik!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metode untuk tombol "Tambah" (sesuai atribut android:onClick="btnTambah" di XML)
    public void btnTambah(View view) {
        // Tambahkan data baru ke daftar
        siswaList.add(new Siswa("Joni Rambo", "Jakarta"));
        // Beri tahu adapter bahwa data telah berubah
        siswaAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Data baru ditambahkan!", Toast.LENGTH_SHORT).show();
    }
}