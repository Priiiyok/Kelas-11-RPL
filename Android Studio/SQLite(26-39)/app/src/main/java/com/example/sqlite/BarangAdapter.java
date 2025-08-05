package com.example.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    private Context context;
    private List<Barang> barangList;

    public BarangAdapter(Context context, List<Barang> barangList) {
        this.context = context;
        this.barangList = barangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Safe check for list and position
        if (barangList == null || position >= barangList.size() || barangList.get(position) == null) {
            return;
        }

        Barang barang = barangList.get(position);

        holder.tvNama.setText(barang.getNama() != null ? barang.getNama() : "Nama tidak tersedia");
        holder.tvStok.setText("Stok: " + (barang.getStok() != null ? barang.getStok() : "0"));
        holder.tvHarga.setText("Rp " + (barang.getHarga() != null ? barang.getHarga() : "0"));

        holder.tvMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.tvMenu);
            popupMenu.inflate(R.menu.menu_item);

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.ubah) {
                    showUpdateDialog(barang);
                    return true;
                } else if (itemId == R.id.edit_form) {
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).populateFormForUpdate(
                                barang.getId(), barang.getNama(), barang.getStok(), barang.getHarga()
                        );
                    }
                    return true;
                } else if (itemId == R.id.hapus) {
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).deleteData(barang.getId(), barang.getNama());
                    }
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return barangList != null ? barangList.size() : 0;
    }

    private void showUpdateDialog(Barang barang) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Update Data Barang");

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 40, 50, 10);

            final EditText etNama = new EditText(context);
            etNama.setHint("Nama Barang");
            etNama.setText(barang.getNama() != null ? barang.getNama() : "");
            layout.addView(etNama);

            final EditText etStok = new EditText(context);
            etStok.setHint("Stok");
            etStok.setText(barang.getStok() != null ? String.valueOf(barang.getStok()) : "");
            etStok.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(etStok);

            final EditText etHarga = new EditText(context);
            etHarga.setHint("Harga");
            etHarga.setText(barang.getHarga() != null ? String.valueOf(barang.getHarga()) : "");
            etHarga.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            layout.addView(etHarga);

            builder.setView(layout);

            builder.setPositiveButton("Update", (dialog, which) -> {
                String newNama = etNama.getText().toString().trim();
                String newStok = etStok.getText().toString().trim();
                String newHarga = etHarga.getText().toString().trim();
                if (context instanceof MainActivity) {
                    ((MainActivity) context).updateData(barang.getId(), newNama, newStok, newHarga);
                }
            });

            builder.setNegativeButton("Batal", (dialog, which) -> dialog.dismiss());

            builder.show();
        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvStok, tvHarga, tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvBarang);
            tvStok = itemView.findViewById(R.id.tvStock);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }
}