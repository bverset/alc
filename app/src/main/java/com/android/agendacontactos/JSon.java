package com.android.agendacontactos;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Bernard on 04/11/2015.
 */


public class JSon {
    String input = "{\"coord\":{\"lon\":-122.09,\"lat\":37.39},\n" +
            "\"sys\":{\"type\":3,\"id\":168940,\"message\":0.0297,\"country\":\"US\",\"sunrise\":1427723751,\"sunset\":1427768967},\n" +
            "\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\n" +
            "\"base\":\"stations\",\n" +
            "\"main\":{\"temp\":285.68,\"humidity\":74,\"pressure\":1016.8,\"temp_min\":284.82,\"temp_max\":286.48},\n" +
            "\"wind\":{\"speed\":0.96,\"deg\":285.001},\n" +
            "\"clouds\":{\"all\":0},\n" +
            "\"dt\":1427700245,\n" +
            "\"id\":0,\n" +
            "\"name\":\"Mountain View\",\n" +
            "\"cod\":200}";


    public JSon(){
        Log.d("JSON","JSon");
        Log.d("JSON",input);
        try {
            JSONArray jonA = new JSONArray(input);
           // Log.d("JSON","Number of entries " + jonA.length());
        }catch (Exception e) {
        }
    }


    public String apiWeather() {
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL("api.openweathermap.org/data/2.5/weather?lat=35&lon=139");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("JSON",in.toString());
            }catch (Exception e) {
            }


        return builder.toString();
    }


}
