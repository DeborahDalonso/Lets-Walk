package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Activity3 extends AppCompatActivity implements
        TextToSpeech.OnInitListener,
        View.OnClickListener,
        View.OnLongClickListener {

    private Button btnN, btnS = null;
    private TextToSpeech tts = null;
    final Locale myLocale = new Locale("pt", "BR");
    String textBtn;
    TextView adress;
    String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        adress = (TextView) findViewById(R.id.txtEnderecoRecebido);

        Bundle bundle = getIntent().getExtras();

        btnN = (Button) findViewById(R.id.btnNao);
        btnS = (Button) findViewById(R.id.btnSim);

        btnS.setOnClickListener(this);
        btnN.setOnClickListener(this);

        btnN.setOnLongClickListener(this);
        btnS.setOnLongClickListener(this);

        if(bundle.containsKey("ENDERECO"))
        {
            String dados = bundle.getString("ENDERECO");
            d = dados;
            adress.setText(""+dados);
        }

        }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.btnNao:
                textBtn = btnN.getText().toString();
                tts.speak(textBtn,TextToSpeech.QUEUE_FLUSH,null);
                break;
            case R.id.btnSim:
                textBtn = btnS.getText().toString();
                tts.speak(textBtn,TextToSpeech.QUEUE_FLUSH,null);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Id not found",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(myLocale);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getApplicationContext(),"Language not supported", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Initialization failed", Toast.LENGTH_SHORT).show();
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
                Intent tela2 = new Intent(this, Activity2.class);
                startActivity(tela2);
                break;
            case R.id.btnSim:
                Intent map = new Intent(this, MapsActivity.class);
                startActivity(map);
                break;
        }
    }
}