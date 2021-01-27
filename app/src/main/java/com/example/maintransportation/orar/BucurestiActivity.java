package com.example.maintransportation.orar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maintransportation.R;
import com.example.maintransportation.firma.Firma;
import com.example.maintransportation.firma.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class BucurestiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Firma> listaFirme;
    private RVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucuresti);

        initData();
        initRecycler();
    }

    private void initData() {
        listaFirme = new ArrayList<>();

        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_yellow, getString(R.string.sofer_ioan),  getString(R.string.masina_31_mai), getString(R.string.zi_luni), getString(R.string.ora_14_50), getString(R.string.firma_millenium)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_green, getString(R.string.sofer_andrei),   getString(R.string.masina_99_ami),  getString(R.string.ora_17_40), getString(R.string.ora_17_50),  getString(R.string.firma_omerta)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_red, getString(R.string.sofer_pavel), getString(R.string.masina_13_mia),  getString(R.string.ora_17_40), getString(R.string.ora_19_50), getString(R.string.firma_nelma)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_yellow,  getString(R.string.ora_17_40),  getString(R.string.masina_31_mai),  getString(R.string.ora_17_40), getString(R.string.ora_14_50), getString(R.string.firma_millenium)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_green,  getString(R.string.ora_17_40), getString(R.string.masina_99_ami),  getString(R.string.ora_17_40),  getString(R.string.ora_17_50), getString(R.string.firma_omerta)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_red, getString(R.string.sofer_pavel), getString(R.string.masina_13_mia),  getString(R.string.ora_17_40), getString(R.string.ora_19_50), getString(R.string.firma_nelma)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_yellow,  getString(R.string.ora_17_40),  getString(R.string.masina_31_mai),  getString(R.string.ora_17_40), getString(R.string.ora_14_50), getString(R.string.firma_millenium)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_green,  getString(R.string.ora_17_40), getString(R.string.masina_99_ami),  getString(R.string.ora_17_40),  getString(R.string.ora_17_50), getString(R.string.firma_omerta)));
        listaFirme.add(new Firma(R.drawable.ic_baseline_directions_bus_24_red,  getString(R.string.sofer_pavel), getString(R.string.masina_13_mia),  getString(R.string.ora_17_40), getString(R.string.ora_19_50), getString(R.string.firma_nelma)));
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.rv_lv_activity_orar_bucuresti);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RVAdapter(listaFirme);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}