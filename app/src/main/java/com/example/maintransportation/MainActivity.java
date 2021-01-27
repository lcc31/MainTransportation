package com.example.maintransportation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.maintransportation.database.DBActivity;
import com.example.maintransportation.orar.BucurestiActivity;
import com.example.maintransportation.orar.GiurgiuActivity;

public class MainActivity extends AppCompatActivity {

    public static final int opt1 = 0;
    public static final int opt2 = 1;
    public static final int opt3 = 2;

    private Button btnGr;
    private Button btnBuc;
    private Button btnRezervare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        btnGr = findViewById(R.id.btn_main_giurgiu);
        btnGr.setOnClickListener(btnOrarGiurgiuEventListener());

        btnBuc = findViewById(R.id.btn_main_bucuresti);
        btnBuc.setOnClickListener(btnOrarBucurestiEventListener());

        btnRezervare = findViewById(R.id.btn_main_rezervare);
        btnRezervare.setOnClickListener(addRezervareEventListener());
    }

    private View.OnClickListener addRezervareEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), RezervareActivity.class);
                startActivity(intent3);
            }
        };
    }

    private View.OnClickListener btnOrarBucurestiEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), BucurestiActivity.class);
                startActivity(intent2);
            }
        };
    }

    private View.OnClickListener btnOrarGiurgiuEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GiurgiuActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, opt1, 0, R.string.menu_choose_bd);
        menu.add(0, opt2, 1, R.string.menu_choose_about);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case opt1:
                Intent intent = new Intent(getApplicationContext(), DBActivity.class);
                startActivity(intent);
                break;
            case opt2:
                Intent intent2 = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }
}