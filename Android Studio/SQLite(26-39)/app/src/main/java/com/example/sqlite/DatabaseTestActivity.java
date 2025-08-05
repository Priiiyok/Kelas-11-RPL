package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseTestActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseTest";
    private Database db;
    private TextView tvResults;
    private EditText etNama, etStok, etHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(this);
        title.setText("Database Test Activity");
        layout.addView(title);

        etNama = createEditText("Test Product Name");
        layout.addView(etNama);

        etStok = createEditText("Test Stock");
        layout.addView(etStok);

        etHarga = createEditText("Test Price");
        layout.addView(etHarga);

        Button btnTest = createButton("Run Database Test", v -> runDatabaseTest());
        layout.addView(btnTest);

        Button btnInsert = createButton("Insert Test Data", v -> insertTestData());
        layout.addView(btnInsert);

        tvResults = new TextView(this);
        tvResults.setText("Test Results:\n");
        layout.addView(tvResults);

        setContentView(layout);

        db = new Database(this);
        runDatabaseTest();
    }

    private EditText createEditText(String hint) {
        EditText et = new EditText(this);
        et.setHint(hint);
        return et;
    }

    private Button createButton(String text, View.OnClickListener listener) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setOnClickListener(listener);
        return btn;
    }

    private void runDatabaseTest() {
        StringBuilder results = new StringBuilder("🧪 Database Test Results:\n\n");

        try {
            results.append("1. Init: ").append(db != null ? "✅ SUCCESS\n" : "❌ FAILED\n");
            if (db == null) {
                tvResults.setText(results.toString());
                return;
            }

            results.append("2. Table: ").append(checkTable() ? "✅ EXISTS\n" : "❌ FAILED\n");
            results.append("3. Count: ").append(checkCount() + "\n");
            results.append("4. Data: ").append(checkSampleData() + "\n");
            results.append("5. Class: ").append(checkBarangClass() ? "✅ SUCCESS\n" : "❌ FAILED\n");
        } catch (Exception e) {
            results.append("❌ ERROR: ").append(e.getMessage()).append("\n");
        }

        tvResults.setText(results.toString());
        Log.d(TAG, results.toString());
    }

    private boolean checkTable() {
        try (Cursor cursor = db.select("PRAGMA table_info(tblbarang)")) {
            return cursor != null && cursor.getCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private String checkCount() {
        try (Cursor cursor = db.select("SELECT COUNT(*) FROM tblbarang")) {
            return cursor != null && cursor.moveToFirst() ? "✅ " + cursor.getInt(0) + " records" : "❌ FAILED";
        } catch (Exception e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }

    private String checkSampleData() {
        try (Cursor cursor = db.select("SELECT * FROM tblbarang LIMIT 5")) {
            if (cursor == null) return "❌ FAILED";
            if (cursor.getCount() == 0) return "   No data found";

            StringBuilder sb = new StringBuilder("✅ SUCCESS\n");
            while (cursor.moveToNext()) {
                String nama = cursor.getString(cursor.getColumnIndexOrThrow("barang"));
                sb.append("   - ").append(nama).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "❌ ERROR: " + e.getMessage();
        }
    }

    private boolean checkBarangClass() {
        try {
            Barang b = new Barang("1", "Test", "10", "100");
            return b.getNama().equals("Test");
        } catch (Exception e) {
            return false;
        }
    }

    private void insertTestData() {
        String nama = etNama.getText().toString().trim();
        String stok = etStok.getText().toString().trim();
        String harga = etHarga.getText().toString().trim();

        if (nama.isEmpty()) nama = "Product " + System.currentTimeMillis();
        if (stok.isEmpty()) stok = "10";
        if (harga.isEmpty()) harga = "25000";

        String sql = "INSERT INTO tblbarang (barang, stok, harga) VALUES ('" + nama + "', " + stok + ", " + harga + ")";

        if (db.runSQL(sql)) {
            Toast.makeText(this, "✅ Insert OK", Toast.LENGTH_SHORT).show();
            runDatabaseTest();
        } else {
            Toast.makeText(this, "❌ Insert Failed", Toast.LENGTH_SHORT).show();
        }
    }
}