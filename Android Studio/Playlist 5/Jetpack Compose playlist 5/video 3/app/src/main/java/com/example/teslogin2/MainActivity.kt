package com.example.teslogin2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan Activity dengan file layout XML
        setContentView(R.layout.activity_main)

        // Mendapatkan referensi ke elemen-elemen UI
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Menetapkan listener untuk Tombol Login
        btnLogin.setOnClickListener {
            // Mengambil teks dari input
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Logika Verifikasi Login Sederhana
            // Ganti "admin" dan "password123" dengan kredensial yang Anda inginkan
            if (username == "admin" && password == "password123") {
                // Login Berhasil
                Toast.makeText(this, "Login Berhasil! Selamat datang, $username.", Toast.LENGTH_SHORT).show()

// Di sini, Anda dapat menambahkan kode untuk berpindah ke Activity lain (misalnya, HomeActivity)
// val intent = Intent(this, HomeActivity::class.java)
            } else {
                // Login Gagal
                Toast.makeText(this, "Login Gagal. Username atau Password salah.", Toast.LENGTH_SHORT).show()
            }
            }
        }
}