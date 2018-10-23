package com.example.hp.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import static android.Manifest.permission.CALL_PHONE;

public class constraint_layout extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    EditText et;
    Button bt,bt2,bt3,bt_location,bt_fragment;
    SharedPreferences sh;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);

        tv = findViewById(R.id.hello_text);
        et = findViewById(R.id.phone);
        bt = findViewById(R.id.button1);
        bt3 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button3);
        bt_location = findViewById(R.id.button_location);
        bt_fragment = findViewById(R.id.button_fragment);


        //SHARED PREFERENCE
        sh = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
        name = sh.getString("name",null);
        tv.setText("Hello " +name+ "!");

        bt.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt_location.setOnClickListener(this);
        bt_fragment.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (bt == view) {

            // GALLERY OPEN
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("content://media/external/images/media/"));
//            startActivity(intent);

            // CAMERA OPEN
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
//            startActivity(intent);

            // CALL LOG OPEN
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("content://call_log/calls/"));
//            startActivity(intent);

            //BROWSER ACTION
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("https://google.com"));
//            startActivity(intent);

            //CONTACT OPEN
//            Intent intent=new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("content://contacts/people/"));
//            startActivity(intent);

            //CALLING
//            Intent intent = new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:" + et.gettext()));
//            startActivity(intent);

            //DIAL ACTION
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:"+et.getText()));
//            startActivity(intent);

            //CALL ACTION
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+et.getText()));
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        }

        else if (bt2 == view) {
            Intent i = new Intent(constraint_layout.this, background_service.class);
            startActivity(i);
        }

        else if (bt3 == view) {
            Intent i = new Intent(constraint_layout.this, recycleview.class);
            startActivity(i);
        }

        else if (bt_location == view){
            Intent i = new Intent(constraint_layout.this, location.class);
            startActivity(i);
        }

        else if (bt_fragment == view){
            Intent i = new Intent(constraint_layout.this,fragment.class);
            startActivity(i);
        }
    }
}
