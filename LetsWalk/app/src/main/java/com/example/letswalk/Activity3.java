package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Activity3 extends AppCompatActivity implements
        View.OnClickListener,
        TextToSpeech.OnInitListener,
        View.OnLongClickListener {

    TextView adress;
    private Button btnN, btnS;
    private TextToSpeech tts = null;
    final Locale myLocale = new Locale("pt", "BR");
    String txtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        adress = (TextView) findViewById(R.id.txtEnderecoRecebido);
        btnN = (Button) findViewById(R.id.btnNao);
        btnS = (Button) findViewById(R.id.btnSim);

        btnN.setOnClickListener(this);
        btnN.setOnLongClickListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle.containsKey("ENDERECO"))
        {
            String dados = bundle.getString("ENDERECO");
            adress.setText(""+dados);
        }

        }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNao:
                Toast.makeText(getApplicationContext(),"Não",Toast.LENGTH_SHORT).show();
                Intent tela2 = new Intent(this, Activity2.class);
                startActivity(tela2);
                break;
            case R.id.btnSim:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=sao+paulo"));
                break;
            default:
                Toast.makeText(getApplicationContext(),"Id not found",Toast.LENGTH_SHORT).show();
                break;
        }
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
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.btnNao:
                txtBtn = btnN.getText().toString();
                tts.speak(txtBtn,TextToSpeech.QUEUE_FLUSH,null);
                break;
            case R.id.btnSim:
                txtBtn = btnS.getText().toString();
                tts.speak(txtBtn,TextToSpeech.QUEUE_FLUSH,null);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Id not found",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}