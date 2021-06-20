package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    int clickCount = 0;
    long startTime;
    long duration;
    static final int MAX_DURATION = 500;
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

        btnN.setOnLongClickListener(this);
        btnS.setOnLongClickListener(this);

        if(bundle.containsKey("ENDERECO"))
        {
            String dados = bundle.getString("ENDERECO");
            d = dados;
            adress.setText(""+dados);
        }

        }

        /*

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                clickCount++;
                break;
            case MotionEvent.ACTION_UP:
                long time = System.currentTimeMillis() - startTime;
                duration = duration + time;
                if (clickCount == 2) {
                    if (duration <= MAX_DURATION) {
                        Toast.makeText(getApplicationContext(), "double tap", Toast.LENGTH_LONG).show();
                        String doubletap = "double tap";
                        tts.speak(doubletap,TextToSpeech.QUEUE_FLUSH,null);
                        openMaps();
                    }
                    clickCount = 0;
                    duration = 0;
                }
                else if(duration > MAX_DURATION){
                    String doubletap = "Max duration";
                    tts.speak(doubletap,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;

        }
        return false;
    }
*/


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
        openMaps();
    }
}