package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Confirmacao_destinho extends AppCompatActivity implements
        View.OnClickListener{

    private Button btnN, btnS = null;
    private TextToSpeech tts = null;
    final Locale myLocale = new Locale("pt", "BR");
    String textBtn;
    TextView adress;
    String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_destino);

        adress = (TextView) findViewById(R.id.txtEnderecoRecebido);

        Bundle bundle = getIntent().getExtras();

        btnN = (Button) findViewById(R.id.btnNao);
        btnS = (Button) findViewById(R.id.btnSim);

        btnS.setOnClickListener(this);
        btnN.setOnClickListener(this);

        if(bundle.containsKey("ENDERECO"))
        {
            String dados = bundle.getString("ENDERECO");
            d = dados;
            adress.setText(""+dados);
        }

        }


    public void openMaps(){
        Intent tela4 = new Intent(this, MapsActivity.class);
        tela4.putExtra("ENDERECO", d);
        startActivity(tela4);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNao:
                Intent tela2 = new Intent(this, Destino_escolhido.class);
                startActivity(tela2);
                break;
            case R.id.btnSim:
                openMaps();
                break;
        }
    }
}