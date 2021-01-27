package com.example.maintransportation.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maintransportation.R;
import com.example.maintransportation.async.Callback;
import com.example.maintransportation.database.model.ClientDB;
import com.example.maintransportation.database.service.ClientDBService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    public static final String PROFILE_SHARED = "profile_shared";
    public static final String NUME = "nume";
    public static final String RB_LOCALITATE = "rb_localitate";
    private SharedPreferences sharedPreferences;

    private TextInputEditText tietNume;
    private RadioGroup rgLocalitate;
    private Button btnSave;
    private Spinner spnFirma;

    private ClientDBService clientDBService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        clientDBService = new ClientDBService(getApplicationContext());
    }

    private void initComponents() {
        tietNume = findViewById(R.id.tiet_profile_nume);
        rgLocalitate = findViewById(R.id.rg_profile_loc_plecare);
        btnSave = findViewById(R.id.btn_profile_save);
        spnFirma = findViewById(R.id.spn_profile_firma);

        sharedPreferences = getSharedPreferences(PROFILE_SHARED, MODE_PRIVATE);

        btnSave.setOnClickListener(btnSaveEventListener());
        readDataFromSharedPreference();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(getApplicationContext(),
                        R.array.add_firma_values,
                        android.R.layout.simple_spinner_dropdown_item);
        spnFirma.setAdapter(adapter);

        spnFirma.setOnItemSelectedListener(selectFirmaEventListener());
    }

    private AdapterView.OnItemSelectedListener selectFirmaEventListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String firma = (String) spnFirma.getAdapter().getItem(position);
                clientDBService.getAllByFirma(firma, getAllByFirmaCallback());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private Callback<List<ClientDB>> getAllByFirmaCallback() {
        return new Callback<List<ClientDB>>() {
            @Override
            public void runResultOnUiThread(List<ClientDB> result) {
                if (result == null || result.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            R.string.profile_error_message_selection,
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(
                            ProfileActivity.this)
                            .setTitle(R.string.profile_alert_title)
                            .setMessage(result.toString())
                            .create();
                    dialog.show();
                }
            }
        };
    }

    private View.OnClickListener btnSaveEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDataToSharedPreference();
                finish();
            }
        };
    }

    private void readDataFromSharedPreference() {
        String nume = sharedPreferences.getString(NUME, "");
        int rbLocalitate = sharedPreferences.getInt(RB_LOCALITATE, R.id.rb_profile_giurgiu);
        if (nume != null) {
            tietNume.setText(nume);
        }
        rgLocalitate.check(rbLocalitate);
    }

    private void writeDataToSharedPreference() {
        String nume = tietNume.getText() != null ?
                tietNume.getText().toString() : null;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUME, nume);
        editor.putInt(RB_LOCALITATE, rgLocalitate.getCheckedRadioButtonId());
        editor.apply();
    }
}