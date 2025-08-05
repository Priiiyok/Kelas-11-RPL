package com.example.sqlite;

public class Barang {
    private String id;
    private String nama;
    private String stok;
    private String harga;

    public Barang(String id, String nama, String stok, String harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public Barang() {}

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getStok() { return stok; }
    public String getHarga() { return harga; }

    public void setId(String id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setStok(String stok) { this.stok = stok; }
    public void setHarga(String harga) { this.harga = harga; }
}