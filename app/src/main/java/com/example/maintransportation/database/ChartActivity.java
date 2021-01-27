package com.example.maintransportation.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.maintransportation.database.model.ClientDB;
import com.example.maintransportation.database.util.ChartView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {
    public static String FIRMA_KEY = "firma_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<ClientDB> listaClienti = (List<ClientDB>) getIntent().getSerializableExtra(FIRMA_KEY);
        Map<String, Integer> source = getSource(listaClienti);
        setContentView(new ChartView(getApplicationContext(), source));
    }

    private Map<String, Integer> getSource(List<ClientDB> listaClienti) {
        if (listaClienti == null || listaClienti.isEmpty()) {
            return null;
        }
        Map<String, Integer> source = new HashMap<>();
        for (ClientDB client : listaClienti) {
            if (source.containsKey(client.getFirma())) {
                Integer currentValue = source.get(client.getFirma());
                Integer newValue = (currentValue != null ? currentValue : 0) + 1;
                source.put(client.getFirma(), newValue);
            } else {
                source.put(client.getFirma(), 1);
            }
        }
        return source;
    }
}