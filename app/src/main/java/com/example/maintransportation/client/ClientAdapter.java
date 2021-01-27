package com.example.maintransportation.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.maintransportation.R;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {

    private Context context;
    private List<Client> listaClienti;
    private LayoutInflater layoutInflater;
    private int resource;

    public ClientAdapter(@NonNull Context context, int resource,
                         List<Client> listaClienti, LayoutInflater layoutInflater) {
        super(context, resource, listaClienti);
        this.context = context;
        this.resource = resource;
        this.listaClienti = listaClienti;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        Client client = listaClienti.get(position);
        if (client != null) {
            addNumeClient(view, client.getNumeClient());
            addLocPlecare(view, client.getLocPlecare());
            addZiPlecare(view, client.getZiPlecare());
            addOraPlecare(view, client.getOraPlecare());
        }
        return view;
    }

    private void addNumeClient(View view, String nume) {
        TextView textView = view.findViewById(R.id.tv_elem_nume);
        populateTextViewContent(textView, nume);
    }

    private void addLocPlecare(View view, String locPlecare) {
        TextView textView = view.findViewById(R.id.tv_elem_locatie);
        populateTextViewContent(textView, locPlecare);
    }

    private void addZiPlecare(View view, String ziPlecare) {
        TextView textView = view.findViewById(R.id.tv_elem_data);
        populateTextViewContent(textView, ziPlecare);
    }

    private void addOraPlecare(View view, String oraPlecare) {
        TextView textView = view.findViewById(R.id.tv_elem_ora);
        populateTextViewContent(textView, oraPlecare);
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_row_view_no_content);
        }
    }
}
