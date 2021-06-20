package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        View.OnLongClickListener,
        TextToSpeech.OnInitListener {

    private Button btnWalk,btnFound,btnCar = null;
    private TextToSpeech tts = null;
    final Locale myLocale = new Locale("pt", "BR");
    String textBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWalk = (Button) findViewById(R.id.btnWalk);
        btnFound = (Button) findViewById(R.id.btnFound);
        btnCar = (Button) findViewById(R.id.btnCar);

        btnWalk.setOnClickListener(this);
        btnFound.setOnClickListener(this);
        btnCar.setOnClickListener(this);

        btnWalk.setOnLongClickListener(this);
        btnFound.setOnLongClickListener(this);
        btnCar.setOnLongClickListener(this);

        tts = new TextToSpeech(this,this);

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btnWalk:
                textBtn = btnWalk.getText().toString();
                tts.speak(textBtn,TextToSpeech.QUEUE_FLUSH,null);

                break;
            case R.id.btnFound:
                textBtn = btnFound.getText().toString();
                tts.speak(textBtn,TextToSpeech.QUEUE_FLUSH,null);

                break;
            case R.id.btnCar:
                textBtn = btnCar.getText().toString();
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnWalk:
                Intent tela2 = new Intent(this, Activity2.class);
                startActivity(tela2);
                break;
            case R.id.btnFound:
                Intent tela4 = new Intent(this, Activity4.class);
                startActivity(tela4);
                break;
        }
    }

}