package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "Eps27";
    private static final int VERSION = 4;
    SQLiteDatabase db;

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tblbarang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "barang TEXT, " +
                "stok INTEGER, " +
                "harga REAL" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblbarang");
        onCreate(db);
    }

    public boolean runSQL(String sql) {
        try {
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor select(String SQL) {
        try {
            return db.rawQuery(SQL, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cursor selectWhere(String whereClause) {
        return select("SELECT * FROM tblbarang WHERE " + whereClause + " ORDER BY barang ASC");
    }

    public Cursor searchByName(String itemName) {
        return select("SELECT * FROM tblbarang WHERE barang LIKE '%" + itemName + "%' ORDER BY barang ASC");
    }

    public Cursor filterByStockRange(int minStock, int maxStock) {
        return select("SELECT * FROM tblbarang WHERE stok >= " + minStock + " AND stok <= " + maxStock + " ORDER BY stok ASC");
    }

    public Cursor filterByPriceRange(double minPrice, double maxPrice) {
        return select("SELECT * FROM tblbarang WHERE harga >= " + minPrice + " AND harga <= " + maxPrice + " ORDER BY harga ASC");
    }
}