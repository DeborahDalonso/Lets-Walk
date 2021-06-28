package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opcoes_prontas extends AppCompatActivity implements View.OnClickListener {

    private Button policia, farmacia, hospital, mercado, cinema, restaurante;
    String btntxt;
    String pc = " Poços de Caldas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcoes_prontas);

        policia = (Button) findViewById(R.id.btnPolicia);
        farmacia = (Button) findViewById(R.id.btnFarmarcia);
        hospital = (Button) findViewById(R.id.btnHospital);
        cinema = (Button) findViewById(R.id.btnCinema);
        restaurante = (Button) findViewById(R.id.btnRestaurante);
        mercado = (Button) findViewById(R.id.btnMercado);

        policia.setOnClickListener(this);
        farmacia.setOnClickListener(this);
        hospital.setOnClickListener(this);
        cinema.setOnClickListener(this);
        restaurante.setOnClickListener(this);
        mercado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPolicia:
                btntxt = policia.getText().toString();
                listMaps();
            break;
            case R.id.btnFarmarcia:
                btntxt = farmacia.getText().toString();
                listMaps();
            break;
            case R.id.btnHospital:
                btntxt = hospital.getText().toString();
                listMaps();
            break;
            case R.id.btnCinema:
                btntxt = cinema.getText().toString();
                listMaps();
            break;
            case R.id.btnRestaurante:
                btntxt = restaurante.getText().toString();
                listMaps();
            break;
            case R.id.btnMercado:
                btntxt = mercado.getText().toString();
                listMaps();
            break;
        }
    }

    public void listMaps(){
        Intent list = new Intent(this, ListaMaps.class);
        list.putExtra("LOCAL", btntxt+pc);
        startActivity(list);
    }
}