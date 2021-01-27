package com.example.maintransportation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maintransportation.async.AsyncTaskRunner;
import com.example.maintransportation.async.Callback;
import com.example.maintransportation.client.Client;
import com.example.maintransportation.client.ClientAdapter;
import com.example.maintransportation.client.ClientJsonParser;
import com.example.maintransportation.network.HttpManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RezervareActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_CLIENT = 210;
    public static final String EDIT_CLIENT = "editClient";
    public static final int REQUEST_CODE_PROFILE = 300;
    private static final String URL_CLIENT = "https://jsonkeeper.com/b/GFVN";

    public int poz;
    private FloatingActionButton fabAddClient;

    public Intent intent;
    private ListView lvPrincipal;

    private List<Client> listaClienti = new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervare);

        initComponents();
        getClientsFromHttp();
    }

    private void getClientsFromHttp() {
        Callable<String> asyncOperation = new HttpManager(URL_CLIENT);
        Callback<String> mainThreadOperation = receiveClientsFromHttp();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveClientsFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                listaClienti.addAll(ClientJsonParser.fromJson(result));
                notifyAdapter();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CLIENT &&
                resultCode == RESULT_OK && data != null) {
            Client client = (Client) data.getSerializableExtra(AddClientActivity.ADD_CLIENT);
            if (client != null) {
                listaClienti.add(client);
                ClientAdapter adapter = new ClientAdapter(getApplicationContext(),
                        R.layout.lv_row_client,
                        listaClienti, getLayoutInflater());
                lvPrincipal.setAdapter(adapter);
            }
        } else if (requestCode == REQUEST_CODE_PROFILE &&
                resultCode == RESULT_OK && data != null) {
            Client client = (Client) data.getSerializableExtra(AddClientActivity.ADD_CLIENT);
            if (client != null) {
                listaClienti.get(poz).setNumeClient(client.getNumeClient());
                listaClienti.get(poz).setLocPlecare(client.getLocPlecare());
                listaClienti.get(poz).setZiPlecare(client.getZiPlecare());
                listaClienti.get(poz).setOraPlecare(client.getOraPlecare());
                ClientAdapter adapter = new ClientAdapter(getApplicationContext(),
                        R.layout.lv_row_client,
                        listaClienti, getLayoutInflater());
                lvPrincipal.setAdapter(adapter);
            }
        }
    }

    private void initComponents() {
        fabAddClient = findViewById(R.id.fab_main_add_client);
        lvPrincipal = findViewById(R.id.lv_rezervare);
        addClientAdapter();
        fabAddClient.setOnClickListener(addNewClientEventListener());
    }


    private View.OnClickListener addNewClientEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddClientActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CLIENT);
            }
        };
    }

    private void addClientAdapter() {
        ClientAdapter adapter = new ClientAdapter(getApplicationContext(),
                R.layout.lv_row_client,
                listaClienti, getLayoutInflater());
        lvPrincipal.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvPrincipal.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt1:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}