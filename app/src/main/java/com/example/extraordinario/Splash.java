package com.example.extraordinario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class Splash extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

     findViewById(R.id.contador).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    CountDownTimer tiempo = new CountDownTimer(5000, 1000) {

        public void onTick(long milisUntilFinished) {
            TextView tiempo = findViewById(R.id.contador);
            tiempo.setText("" + milisUntilFinished / 1000);
        }

        public void onFinish() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }.start();
}