package com.example.letswalk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Button btnToT2;
    private Button b;
    private TextToSpeech tts;
    private RelativeLayout myLayout = null;
    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = (RelativeLayout) findViewById(R.id.myLayout);
        b = (Button) findViewById(R.id.btnOut);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                b.setX(x);
                b.setY(y);
                }
                return true;
            }
        });

        tts = new TextToSpeech(this,this);

        btnToT2 = findViewById(R.id.btnWalk);

        btnToT2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                openActvity2();
            }
        });
    }

    public void openActvity2(){
        Intent tela2 = new Intent(this, Activity2.class);
        startActivity(tela2);
    }

    @Override
    public void onInit(int status) {
            if(status == TextToSpeech.SUCCESS){

            }
    }
}