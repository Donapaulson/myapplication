package com.example.hp.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    EditText et;
    Button bt1, bt2,bt3;
    ImageView iv;

    SharedPreferences sh;
    String name, welcome_name, query, selectquery;
    DBHelper dbHelper;
    Cursor cursor;

    //NOTIFICATION
    NotificationCompat.Builder notification;
    private static final int UniqueId = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textview);
        et = findViewById(R.id.editText);
        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        iv = findViewById(R.id.image);

        //Notification
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


//        Using picasso image viewer
        Picasso.with(this)
                .load(R.drawable.img5)  //name of the image to load.
                .into(iv);


//        Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.placeholder).into(iv);

//        Picasso.with(this).load("http://www.nkfu.com/wp-content/uploads/2015/10/mor-masaustu-resimleri-3.jpg").error(R.mipmap.ic_launcher).into(iv, new Callback() {
//            @Override
//            public void onSuccess() {
//                Log.d("TAG", "onSuccess");
//            }

//            @Override
//            public void onError() {
//                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Picasso.with(this)
//                .load(R.drawable.img1)
//                .resize(40, 20) //resizing width and height of image
//                .centerCrop()  // transform image.
//                .into(iv);


//        iv.setImageResource(R.drawable.img1);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (bt1 == view) {

            welcome_name = et.getText().toString();
            if (welcome_name.equals("")) {

                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show();
            } else {

                //DATABASE DATA INSERTION
                dbHelper = new DBHelper(this);
                dbHelper.openConnection();
                name = et.getText().toString();
                //Insertion query
                query = "insert into users(name) values" + "('" + name + "')";
                boolean bool = dbHelper.executeQuery(query);
                //selection query
                selectquery = "select * from users";
                cursor = dbHelper.selectData(selectquery);
                if (cursor.moveToFirst()) {
                    do {
                        String data = cursor.getString(cursor.getColumnIndex("name"));
                        // do what ever you want here
                        Log.d("abc", data);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                dbHelper.closeConnection();
                if (bool == true) {
//                    Toast.makeText(MainActivity.this, "value insertion success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "value insertion failed", Toast.LENGTH_SHORT).show();
                }

                //SHARED PREFERENCE VALUE SETTING
                sh = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("name", name);
                editor.commit();
//                Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();

                //EXPLICIT INTENT
                Intent i = new Intent(MainActivity.this, constraint_layout.class);
                startActivity(i);

            }
        } else if (bt2 == view) {

            //ALERT DIALOG
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Do you want to download?");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Download",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent i = new Intent(MainActivity.this, progressbar.class);
                            startActivity(i);

                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        else if (bt3 == view){

            //Build notification
            notification.setSmallIcon(R.drawable.ic_launcher_foreground);
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("You have a new notification");
            notification.setContentText("Hello");

            Intent i = new Intent(this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            //Issue notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(UniqueId,notification.build());
        }
    }
}


