package com.example.maintransportation.database.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.maintransportation.R;
import com.example.maintransportation.database.model.ClientDB;

import java.util.Date;
import java.util.List;

public class ClientDBAdapter extends ArrayAdapter<ClientDB> {

    private Context context;
    private int resource;
    private List<ClientDB> listaClienti;
    private LayoutInflater inflater;

    public ClientDBAdapter(@NonNull Context context, int resource,
                           @NonNull List<ClientDB> objects,
                           LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listaClienti = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        ClientDB clientDB = listaClienti.get(position);
        if (clientDB != null) {
            addNumeClient(view, clientDB.getNumeClient());
            addLocPlecare(view, clientDB.getLocPlecare());
            addZiPlecare(view, clientDB.getZiPlecare());
            addOraPlecare(view, clientDB.getOraPlecare());
            addFirma(view, clientDB.getFirma());
        }
        return view;
    }

    private void addNumeClient(View view, String nume) {
        TextView textView = view.findViewById(R.id.tv_list_item_nume);
        addTextViewContent(textView, nume);
    }

    private void addLocPlecare(View view, String locPlecare) {
        TextView textView = view.findViewById(R.id.tv_list_item_loc);
        addTextViewContent(textView, locPlecare);
    }

    private void addZiPlecare(View view, Date ziPlecare) {
        TextView textView = view.findViewById(R.id.tv_list_item_zi);
        addTextViewContent(textView, DateConverter.fromDate(ziPlecare));

    }

    private void addOraPlecare(View view, String oraPlecare) {
        TextView textView = view.findViewById(R.id.tv_list_item_ora);
        addTextViewContent(textView, oraPlecare);
    }

    private void addFirma(View view, String firma) {
        TextView textView = view.findViewById(R.id.tv_list_item_firma);
        addTextViewContent(textView, firma);
    }

    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_row_view_no_content);
        }
    }
}
