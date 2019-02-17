package com.animetv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
private Context context;
    private Chronometer mChronometer;
int a =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
context = this;

        Toast.makeText(this, "Make by @DevForYou", Toast.LENGTH_LONG).show();

        Timer timer = new Timer();
        class UpdateTimeTask extends TimerTask {
            public void run() {
a++;
if(a==5){
    Intent intent = new Intent(context, MainActivity.class);
    startActivity(intent);
    finish();

}
            }
        }
        timer.schedule(new UpdateTimeTask(), 0, 1000); //тикаем каждую секунду без задержки




    }

}