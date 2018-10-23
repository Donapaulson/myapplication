package com.example.hp.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class fragment extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;
    TextView tv;
//    ImageView iv1,iv2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        tv = findViewById(R.id.textview);
        iv = findViewById(R.id.img);
//        iv1 = findViewById(R.id.img1);
//        iv2 = findViewById(R.id.img2);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

//        android.support.v4.app.Fragment fr1;
//        android.support.v4.app.Fragment fr2;
        android.support.v4.app.Fragment fr = null;

        if (b1 == view){

//            fr1 = new FragmentOne();
            fr = new FragmentOne();
            Picasso.with(this)
                    .load(R.drawable.img5)  //name of the image to load.
                    .into(iv);
        }

        else if (b2 == view){

//            fr2 = new FragmentTwo();
            fr = new FragmentTwo();
            Picasso.with(this)
                    .load(R.drawable.img6)  //name of the image to load.
                    .into(iv);

        }

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment1,fr);
        fragmentTransaction.commit();

    }
}

