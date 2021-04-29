package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Activity2 extends AppCompatActivity {

    EditText edtDestino;
    private ImageButton btnFalar;
    private TextView txtEnderecoToAct3;
    private final int ID_TEXTO_PARA_VOZ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        edtDestino = (EditText) findViewById(R.id.edtDestino);
        btnFalar = (ImageButton) findViewById(R.id.btnFalar);
        txtEnderecoToAct3 = (TextView) findViewById(R.id.txtEnderecoRecebido);

        btnFalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVoz = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                iVoz.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale agora");/*Fazer o app ler para o usuario*/

                startActivityForResult(iVoz, ID_TEXTO_PARA_VOZ);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {
        if (requestCode == ID_TEXTO_PARA_VOZ && resultCode == RESULT_OK) {
            List<String> results = dados.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Toast.makeText(getApplicationContext(), spokenText, Toast.LENGTH_SHORT).show();
            edtDestino.setText(spokenText);
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            openActvity3();
        }
        super.onActivityResult(requestCode, resultCode, dados);
    }
    public void openActvity3(){
        Intent tela3 = new Intent(this, Activity3.class);
        tela3.putExtra("ENDERECO", edtDestino.getText().toString());
        startActivity(tela3);
    }
}