package com.example.messagedialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog; // Tambahkan ini
import android.content.DialogInterface; // Tambahkan ini

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Metode untuk menampilkan Toast
    public void btnToast(View view) {
        showToast("Belajar membuat pesan");
    }

    // Metode untuk menampilkan Alert Dialog sederhana
    public void btnAlert(View view) {
        showAlert("Silakan Dicoba!");
    }

    // Metode untuk menampilkan Alert Dialog dengan tombol
    public void btnAlertDialogButton(View view) {
        showAlertButton("Yakin Akan Menghapus?");
    }

    // Fungsi pembantu untuk menampilkan Toast
    public void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    // Fungsi pembantu untuk menampilkan Alert Dialog sederhana
    public void showAlert(String pesan) {
        AlertDialog.Builder buatAlert = new AlertDialog.Builder(this);
        buatAlert.setTitle("PERHATIAN"); // Judul dialog
        buatAlert.setMessage(pesan); // Pesan dialog
        buatAlert.show(); // Tampilkan dialog
    }

    // Fungsi pembantu untuk menampilkan Alert Dialog dengan tombol Ya/Tidak
    public void showAlertButton(String pesan) {
        AlertDialog.Builder showAlert = new AlertDialog.Builder(this);
        showAlert.setTitle("PERINGATAN"); // Judul dialog
        showAlert.setMessage(pesan); // Pesan dialog

        // Tombol "YA"
        showAlert.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showToast("Data Sudah Dihapus !"); // Aksi jika tombol YA ditekan
            }
        });

        // Tombol "TIDAK"
        showAlert.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showToast("Data Tidak Dihapus"); // Aksi jika tombol TIDAK ditekan
            }
        });
        showAlert.show(); // Tampilkan dialog
    }
}