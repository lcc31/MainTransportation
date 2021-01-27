package com.example.maintransportation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maintransportation.client.Client;

public class AddClientActivity extends AppCompatActivity {

    public static final String ADD_CLIENT = "adaugaClient";
    public Intent intent;
    private EditText etNumeClient;
    private EditText etLocPlecare;
    private EditText etZiPlecare;
    private EditText etOraPlecare;
    private Button btnSalvareClient;

    private Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        initComponents();
        intent = getIntent();
        if (intent.hasExtra(RezervareActivity.EDIT_CLIENT)) {
            Client client = (Client) intent.getSerializableExtra(RezervareActivity.EDIT_CLIENT);
            updateViewsFromClient(client);
        }
    }

    private View.OnClickListener saveClientEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(ADD_CLIENT, client);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String numeClient = etNumeClient.getText().toString();
        String locPlecare = etLocPlecare.getText().toString();
        String ziPlecare = etZiPlecare.getText().toString();
        String oraPlecare = etOraPlecare.getText().toString();
        if (client == null) {
            client = new Client(numeClient, locPlecare, ziPlecare, oraPlecare);
        } else {
            client.setNumeClient(numeClient);
            client.setLocPlecare(locPlecare);
            client.setZiPlecare(ziPlecare);
            client.setOraPlecare(oraPlecare);
        }
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
                || etZiPlecare.getText().toString().trim().length() < 3) {
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

    private void updateViewsFromClient(Client client) {
        if (client == null) {
            return;
        }
        etNumeClient.setText(client.getNumeClient());
        etLocPlecare.setText(client.getLocPlecare());
        etZiPlecare.setText(client.getZiPlecare());
        etOraPlecare.setText(client.getOraPlecare());
    }

    private void initComponents() {
        etNumeClient = findViewById(R.id.et_rez_nume_client);
        etLocPlecare = findViewById(R.id.et_rez_locatie_plecare);
        etZiPlecare = findViewById(R.id.et_rez_zi_plecare);
        etOraPlecare = findViewById(R.id.et_rez_ora_plecarii);
        btnSalvareClient = findViewById(R.id.btn_rez_salvare_rezervare);
        btnSalvareClient.setOnClickListener(saveClientEventListener());
    }
}