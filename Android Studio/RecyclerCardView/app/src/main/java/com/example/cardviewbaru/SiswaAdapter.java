package com.example.cardviewbaru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardviewbaru.model.Siswa;
import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private Context context;
    private List<Siswa> siswaList;

    public SiswaAdapter(Context context, List<Siswa> siswaList) {
        this.context = context;
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);

        holder.tvNama.setText(siswa.getNama());
        holder.tvAlamat.setText(siswa.getAlamat());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Klik: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
        });

        holder.tvMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.tvMenu);
            popupMenu.inflate(R.menu.menu_siswa); // gunakan menu baru

            popupMenu.setOnMenuItemClickListener(item -> {
                int pos = holder.getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return false;

                int itemId = item.getItemId();
                if (itemId == R.id.menu_detail) {
                    Toast.makeText(context, "Detail: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.menu_edit) {
                    Toast.makeText(context, "Edit: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.menu_delete) {
                    siswaList.remove(pos);
                    notifyItemRemoved(pos);
                    Toast.makeText(context, "Hapus: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.menu_share) {
                    Toast.makeText(context, "Bagikan: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.menu_favorite) {
                    Toast.makeText(context, "Favoritkan: " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat, tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }
}
