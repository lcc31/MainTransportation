package com.example.maintransportation.firma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maintransportation.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<com.example.maintransportation.firma.Firma> listaFirme;

    public RVAdapter(List<com.example.maintransportation.firma.Firma> listaFirme) {
        this.listaFirme = listaFirme;
    }

//    private List<Firma> listaFirme;
//    Context context;
//    public RecyclerViewAdapter(List<Firma> listaFirme, Context context) {
//        this.listaFirme = listaFirme;
//        this.context = context;
//    }

    @NonNull
    @Override
    public com.example.maintransportation.firma.RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lv_row_firma, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.iv_lv_row_picture.setImageDrawable(listaFirme.get(position).);
//        Firma f = listaFirme.get(position);


        int resource = listaFirme.get(position).getImagineBus();
        String numeSofer = listaFirme.get(position).getNumeSofer();
        String nrMasina = listaFirme.get(position).getNrMasina();
        String ziPlecare = listaFirme.get(position).getZiPlecare();
        String oraPlecare = listaFirme.get(position).getOraPlecare();
        String numeFirma = listaFirme.get(position).getNumeFirma();

//        holder.iv_row_picture.setImageResource(listaFirme.get(position).getImagineBus());
//        holder.tv_nume_sofer.setText(listaFirme.get(position).getNumeSofer());
//        holder.tv_nr_masina.setText(listaFirme.get(position).getNrMasina());
//        holder.tv_zi_plecare.setText(listaFirme.get(position).getZiPlecare());
//        holder.tv_ora_plecare.setText(listaFirme.get(position).getOraPlecare());
//        holder.tv_nume_firma.setText(listaFirme.get(position).getNumeFirma());

        holder.setData(resource, numeSofer, nrMasina, ziPlecare, oraPlecare, numeFirma);
    }

    @Override
    public int getItemCount() {
        return listaFirme.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_row_picture;
        private TextView tv_nume_sofer;
        private TextView tv_nr_masina;
        private TextView tv_zi_plecare;
        private TextView tv_ora_plecare;
        private TextView tv_nume_firma;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            iv_row_picture = itemView.findViewById(R.id.iv_item_design_bus);
//            tv_nume_sofer = itemView.findViewById(R.id.tv_item_nume_sofer);
//            tv_nr_masina = itemView.findViewById(R.id.tv_item_nr_masina);
//            tv_zi_plecare = itemView.findViewById(R.id.tv_item_zi_plecare);
//            tv_ora_plecare = itemView.findViewById(R.id.tv_item_ora_plecare);
//            tv_nume_firma = itemView.findViewById(R.id.tv_item_nume_firma);
            iv_row_picture = itemView.findViewById(R.id.iv_lv_row_picture);
            tv_nume_sofer = itemView.findViewById(R.id.tv_row_nume_sofer);
            tv_nr_masina = itemView.findViewById(R.id.tv_row_nr_masina);
            tv_zi_plecare = itemView.findViewById(R.id.tv_row_zi_plecare);
            tv_ora_plecare = itemView.findViewById(R.id.tv_row_ora_plecare);
            tv_nume_firma = itemView.findViewById(R.id.tv_row_nume_firma);
        }

        public void setData(int resource, String numeSofer, String nrMasina,
                            String ziPlecare, String oraPlecare, String numeFirma) {
            iv_row_picture.setImageResource(resource);
            tv_nume_sofer.setText(numeSofer);
            tv_nr_masina.setText(nrMasina);
            tv_zi_plecare.setText(ziPlecare);
            tv_ora_plecare.setText(oraPlecare);
            tv_nume_firma.setText(numeFirma);

        }
    }
}
