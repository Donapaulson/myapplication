package com.example.hp.myapplication;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class progressbar extends AppCompatActivity implements View.OnClickListener {
    ProgressBar pg;
    ProgressDialog progressDialog;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progressbar);

        pg = findViewById(R.id.pgbar);
        pg.setMax(100);
        bt = findViewById(R.id.bt3);

        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (bt == view) {

            progressDialog = new ProgressDialog(this);
            progressDialog.setMax(100); // Progress Dialog Max Value
            progressDialog.setMessage("Loading..."); // Setting Message
            progressDialog.setTitle("ProgressDialog"); // Setting Title
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // In circularn motion when time is not known
            progressDialog.show(); // Display Progress Dialog
            progressDialog.setCancelable(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (progressDialog.getProgress() <= progressDialog.getMax()) {
                            Thread.sleep(200);
                            progressDialog.incrementProgressBy(2);
                            if (progressDialog.getProgress() == progressDialog.getMax()) {
                                progressDialog.dismiss();
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(progressbar.this,"Error in progress dialogue",Toast.LENGTH_SHORT).show();
                    }
                }
            }).start();
        }
    }
}
