package com.example.maintransportation.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maintransportation.R;
import com.example.maintransportation.async.Callback;
import com.example.maintransportation.database.model.ClientDB;
import com.example.maintransportation.database.service.ClientDBService;
import com.example.maintransportation.database.util.ClientDBAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBActivity extends AppCompatActivity {
    private static final int ADD_CLIENTDB_REQUEST_CODE = 201;
    private static final int UPDATE_CLIENTDB_REQUEST_CODE = 222;

    private ListView lvClientDb;
    private FloatingActionButton fabAddDbClient;
    private FloatingActionButton fabProfile;
    private FloatingActionButton fabChart;

    private List<ClientDB> listaClientiDB = new ArrayList<>();

    private ClientDBService clientDBService;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        initComponents();
        clientDBService = new ClientDBService(getApplicationContext());
        clientDBService.getAll(getAllCallback());
    }

    private Callback<List<ClientDB>> getAllCallback() {
        return new Callback<List<ClientDB>>() {
            @Override
            public void runResultOnUiThread(List<ClientDB> result) {
                if (result != null) {
                    listaClientiDB.clear();
                    listaClientiDB.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<ClientDB> insertCallback() {
        return new Callback<ClientDB>() {
            @Override
            public void runResultOnUiThread(ClientDB result) {
                if (result != null) {
                    listaClientiDB.add(result);
                    notifyAdapter();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.insert_failed_message,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Callback<ClientDB> updateCallback() {
        return new Callback<ClientDB>() {
            @Override
            public void runResultOnUiThread(ClientDB result) {
                if (result != null) {
                    for (ClientDB clientDB : listaClientiDB) {
                        if (clientDB.getId() == result.getId()) {
                            clientDB.setNumeClient(result.getNumeClient());
                            clientDB.setLocPlecare(result.getLocPlecare());
                            clientDB.setZiPlecare(result.getZiPlecare());
                            clientDB.setOraPlecare(result.getOraPlecare());
                            clientDB.setFirma(result.getFirma());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    listaClientiDB.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CLIENTDB_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            ClientDB clientDB = (ClientDB) data
                    .getSerializableExtra(AddDBClientActivity.DB_CLIENT_KEY);
            clientDBService.insert(clientDB, insertCallback());
        } else if (requestCode == UPDATE_CLIENTDB_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            ClientDB clientDB = (ClientDB) data
                    .getSerializableExtra(AddDBClientActivity.DB_CLIENT_KEY);
            clientDBService.update(clientDB, updateCallback());
        }
    }

    private void initComponents() {
        lvClientDb = findViewById(R.id.lv_db_client);
        fabAddDbClient = findViewById(R.id.fab_db_add_clientdb);
        fabProfile = findViewById(R.id.fab_db_profile);
        fabChart = findViewById(R.id.fab_db_chart);
        addAdapter();
        fabAddDbClient.setOnClickListener(addClientDbEventListener());
        lvClientDb.setOnItemClickListener(updateClientDbEventListener());
        lvClientDb.setOnItemLongClickListener(deleteClientDbEventListener());

        fabProfile.setOnClickListener(profileEventListener());
        fabChart.setOnClickListener(chartEventListener());

        sharedPreferences = getSharedPreferences(ProfileActivity.PROFILE_SHARED, MODE_PRIVATE);
        displayMessage();
    }

    private View.OnClickListener chartEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ChartActivity.class);
                intent2.putExtra(ChartActivity.FIRMA_KEY, (Serializable) listaClientiDB);
                startActivity(intent2);
            }
        };
    }

    private void displayMessage() {
        String nume = sharedPreferences
                .getString(ProfileActivity.NUME, null);
        if (nume != null) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.display_param_message,
                            nume), Toast.LENGTH_SHORT).show();

        }
    }

    private View.OnClickListener profileEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ProfileActivity.class);
                startActivity(intent);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteClientDbEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                clientDBService.delete(listaClientiDB.get(position),
                        deleteCallback(position));
                return true;
            }
        };
    }

    private AdapterView.OnItemClickListener updateClientDbEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        AddDBClientActivity.class);
                intent.putExtra(AddDBClientActivity.DB_CLIENT_KEY,
                        listaClientiDB.get(position));
                startActivityForResult(intent, UPDATE_CLIENTDB_REQUEST_CODE);
            }
        };
    }

    private View.OnClickListener addClientDbEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDBClientActivity.class);
                startActivityForResult(intent, ADD_CLIENTDB_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {
        ClientDBAdapter adapter = new ClientDBAdapter(getApplicationContext(),
                R.layout.list_item,
                listaClientiDB, getLayoutInflater());
        lvClientDb.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ClientDBAdapter adapter = (ClientDBAdapter) lvClientDb.getAdapter();
        adapter.notifyDataSetChanged();
    }
}