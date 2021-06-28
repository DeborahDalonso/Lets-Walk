package com.example.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private Button btnWalk,btnFound,btnCar = null;
    private TextToSpeech tts = null;
    final Locale myLocale = new Locale("pt", "BR");
    String textBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnWalk = (Button) findViewById(R.id.btnWalk);
        btnFound = (Button) findViewById(R.id.btnFound);
        btnCar = (Button) findViewById(R.id.btnCar);

        btnWalk.setOnClickListener(this);
        btnFound.setOnClickListener(this);
        btnCar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnWalk:
                Intent tela2 = new Intent(this, Destino_escolhido.class);
                startActivity(tela2);
                break;
            case R.id.btnFound:
                Intent tela4 = new Intent(this, Opcoes_prontas.class);
                startActivity(tela4);
                break;
        }
    }

}