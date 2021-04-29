package com.example.letswalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    TextView adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        adress = (TextView) findViewById(R.id.txtEnderecoRecebido);

        Bundle bundle = getIntent().getExtras();

        if(bundle.containsKey("ENDERECO"))
        {
            String dados = bundle.getString("ENDERECO");
            adress.setText(""+dados);
        }

        }
    }