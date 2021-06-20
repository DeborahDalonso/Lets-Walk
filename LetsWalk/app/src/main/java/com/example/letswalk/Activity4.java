package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity4 extends AppCompatActivity implements View.OnClickListener {

    private Button policia, farmacia, hospital, mercado, cinema, restaurante;
    String btntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

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
            break;
            case R.id.btnFarmarcia:
                btntxt = farmacia.getText().toString();
            break;
            case R.id.btnHospital:
                btntxt = hospital.getText().toString();
            break;
            case R.id.btnCinema:
                btntxt = cinema.getText().toString();
            break;
            case R.id.btnRestaurante:
                btntxt = restaurante.getText().toString();
            break;
            case R.id.btnMercado:
                btntxt = mercado.getText().toString();
            break;
        }
    }

    public void listaMap(){
        Intent list = new Intent(this, Activity3.class);
        list.putExtra("ENDERECO", btntxt);
        startActivity(list);
    }
}