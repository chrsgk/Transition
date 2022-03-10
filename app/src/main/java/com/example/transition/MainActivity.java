package com.example.transition;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton1;
    Button boton2;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1=(Button) findViewById(R.id.button);
        boton2 = (Button) findViewById(R.id.button2);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder= new NotificationCompat.Builder( this, "myCh")
                    .setSmallIcon(android.R.drawable.stat_notify_sync)
                    .setContentTitle("Mi primera notificacion")
                    .setContentText("Este es el cuerpo(body) del mensaje");

            notification = builder.build();
            notificationManagerCompat =NotificationManagerCompat.from(this);
        }


    }
     public void go(View view){
        Intent i  = new Intent(this, MainActivity2.class);
        Bundle a = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

        startActivity(i, a);
     }

     public void push(View view){
        notificationManagerCompat.notify(1, notification);

     }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button: go(view);
            case R.id.button2: push(view);
            break;
        }
    }
}