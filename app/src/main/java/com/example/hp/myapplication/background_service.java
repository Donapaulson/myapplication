package com.example.hp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class background_service extends Activity implements View.OnClickListener {

    Button bt1, bt2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_service);

        bt1 = findViewById(R.id.buttonForStartService);
        bt2 = findViewById(R.id.buttonForStopService);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (bt1 == view) {
            startService(new Intent(this, MyService.class));
        }

        else if (bt2 == view) {
            stopService(new Intent(this, MyService.class));
        }
    }
}
