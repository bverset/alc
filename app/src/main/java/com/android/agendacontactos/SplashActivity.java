package com.android.agendacontactos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.agendacontactos.preferences.CacheManager;

public class SplashActivity extends AppCompatActivity {

    private CacheManager cacheManager;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent i;
        i = new Intent(SplashActivity.this, ImageActivity.class);
        startActivity(i);
        finish();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        cacheManager = new CacheManager(this);

        tarea();  //methode

        new MiTarea().execute();  //class


        
/*        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                *//*if(cacheManager.isLoggin()){
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }else{
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                *//*
                i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);*/
    }

    class MiTarea extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
            progressBar.setMax(100);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(SplashActivity.this, "Tarea2 Finalizada", Toast.LENGTH_LONG).show();
            Intent i;
            i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int p = values[0].intValue();

            progressBar.setProgress(p);
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            Toast.makeText(SplashActivity.this, "Se cancelo", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            for (int i = 1; i <= 10; i++) {
                tareaLarga();
                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }
    }

    private void tarea(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    tareaLarga();
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.incrementProgressBy(10);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this, "Tarea Finalizada", Toast.LENGTH_LONG).show();

                    }
                });


            }
        }).start();
    }

    private void tareaLarga(){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            Log.d("ERRRR", e.getLocalizedMessage());

        }
    }

}
