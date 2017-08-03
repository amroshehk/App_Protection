package com.applefish.protectingapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    private Thread splashThread;
    private final int splashtime =3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //no title bar
       // requestWindowFeature( Window.FEATURE_NO_TITLE );

        //full screen
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, 0 );

        setContentView(R.layout.activity_splash_screen);

        splashThread = new Thread(){

            public  void run(){

                try{
                    synchronized (this) {
                        wait(splashtime);
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                finally {

                    finish();

                    Intent i = new Intent();
                        i.setClass( getApplicationContext(), MainActivity.class );
                        startActivity(i);

                }
            }
        };

        splashThread.start();
    }
}
