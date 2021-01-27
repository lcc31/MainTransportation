package com.example.maintransportation.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maintransportation.R;
import com.example.maintransportation.database.model.ClientDB;
import com.example.maintransportation.database.util.DateConverter;

import java.util.Date;

public class AddDBClientActivity extends AppCompatActivity {

    public static final String DB_CLIENT_KEY = "addDbClientKey";
    private EditText etNumeClient;
    private EditText etLocPlecare;
    private EditText etZiPlecare;
    private EditText etOraPlecare;
    private Spinner spnFirma;
    private Button btnSalvareClientDb;

    public Intent intent;
    private ClientDB clientDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_db_client);

        initComponents();
        intent = getIntent();

        if (intent.hasExtra(DB_CLIENT_KEY)) {
            clientDB = (ClientDB) intent.getSerializableExtra(DB_CLIENT_KEY);
            updateViewsFromExpense(clientDB);
        }
    }

    private void updateViewsFromExpense(ClientDB clientDB) {
        if (clientDB == null) {
            return;
        }
        etNumeClient.setText(clientDB.getNumeClient());
        etLocPlecare.setText(clientDB.getLocPlecare());
        if (clientDB.getZiPlecare() != null) {
            etZiPlecare.setText(DateConverter.fromDate(clientDB.getZiPlecare()));
        }
        etOraPlecare.setText(clientDB.getOraPlecare());
        selectFirma(clientDB);
    }

    private void selectFirma(ClientDB clientDB) {
        ArrayAdapter<CharSequence> adapter =
                (ArrayAdapter<CharSequence>) spnFirma.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            String item = (String) adapter.getItem(i);
            if (item != null && item.equals(clientDB.getFirma())) {
                spnFirma.setSelection(i);
                break;
            }
        }
    }

    private void initComponents() {
        etNumeClient = findViewById(R.id.et_addDB_nume_client);
        etLocPlecare = findViewById(R.id.et_addDB_locatie_plecare);
        etZiPlecare = findViewById(R.id.et_addDB_zi_plecare);
        etOraPlecare = findViewById(R.id.et_addDB_ora_plecarii);
        spnFirma = findViewById(R.id.spn_addDB_firma);
        btnSalvareClientDb = findViewById(R.id.btn_addDB_salvare_rezervare);
        addFirmaAdapter();
        btnSalvareClientDb.setOnClickListener(saveClientDbEventListener());
    }


    private View.OnClickListener saveClientDbEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(DB_CLIENT_KEY, clientDB);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String nume = etNumeClient.getText().toString();
        String loc = etLocPlecare.getText().toString();
        Date zi = DateConverter.fromString(etZiPlecare.getText().toString());
        String ora = etOraPlecare.getText().toString();
        String firma = spnFirma.getSelectedItem().toString();
        if (clientDB == null) {
            clientDB = new ClientDB(nume, loc, zi, ora, firma);
        } else {
            clientDB.setNumeClient(nume);
            clientDB.setLocPlecare(loc);
            clientDB.setZiPlecare(zi);
            clientDB.setOraPlecare(ora);
            clientDB.setFirma(firma);
        }
    }

    private void addFirmaAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(getApplicationContext(),
                        R.array.add_firma_values,
                        android.R.layout.simple_spinner_dropdown_item);
        spnFirma.setAdapter(adapter);
    }

    private boolean validate() {
        if (etNumeClient.getText() == null ||
                etNumeClient.getText().toString().isEmpty()
                || etNumeClient.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_clientdb_nume_error_message,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etLocPlecare.getText() == null ||
                etLocPlecare.getText().toString().isEmpty()
                || etLocPlecare.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_clientdb_loc_error_message,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etZiPlecare.getText() == null ||
                etZiPlecare.getText().toString().trim().isEmpty()
                || DateConverter.fromString(etZiPlecare.getText().toString()) == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_clientdb_date_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etOraPlecare.getText() == null ||
                etOraPlecare.getText().toString().isEmpty()
                || etOraPlecare.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_clientdb_ora_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}