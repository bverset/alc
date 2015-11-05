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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Bernard on 04/11/2015.
 */


public class JSon {
    String jSonText = "{\n" +
            "   \"contacts\":[\n" +
            "      {\n" +
            "         \"id\":\"c200\",\n" +
            "         \"name\":\"Ravi Tamada\",\n" +
            "         \"email\":\"ravi@gmail.com\",\n" +
            "         \"address\":\"xx-xx-xxxx,x - street, x - country\",\n" +
            "         \"gender\":\"male\",\n" +
            "         \"phone\":{\n" +
            "            \"mobile\":\"+91 0000000000\",\n" +
            "            \"home\":\"00 000000\",\n" +
            "            \"office\":\"00 000000\"\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"c201\",\n" +
            "         \"name\":\"Johnny Depp\",\n" +
            "         \"email\":\"johnny_depp@gmail.com\",\n" +
            "         \"address\":\"xx-xx-xxxx,x - street, x - country\",\n" +
            "         \"gender\":\"male\",\n" +
            "         \"phone\":{\n" +
            "            \"mobile\":\"+91 0000000000\",\n" +
            "            \"home\":\"00 000000\",\n" +
            "            \"office\":\"00 000000\"\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}";


    public JSon(){
        Log.d("JSON","JSon");
        //Log.d("JSON",input);
        try {
            JSONObject jObject=new JSONObject(jSonText);
            //String name = jObject.getString("name");
            //Log.d("JSON", "Name "+name);

            JSONArray arraycontacts = jObject.getJSONArray("contacts");
            ArrayList<String> arraycontactsList = new ArrayList<>();
            for (int i=0;i<arraycontacts.length();i++){
                //Log.d("JSON", "Element "+arraycontacts.get(i).toString());


                JSONObject jContact=new JSONObject(arraycontacts.getJSONObject(i).toString());

                String id = jContact.getString("id");
                Log.d("JSON", "ID "+id);
                String name = jContact.getString("name");
                Log.d("JSON", "Name "+name);
                String email = jContact.getString("email");
                Log.d("JSON", "Email "+email);
                String gender = jContact.getString("gender");
                Log.d("Gender", "ID "+gender);
                JSONObject jphone = jContact.getJSONObject("phone");
                Log.d("JSON", "Phones :");
                String office = jphone.getString("office");
                Log.d("JSON", "Office "+office);
                String home = jphone.getString("home");
                Log.d("JSON", "Home "+home);
                String mobile = jphone.getString("mobile");
                Log.d("JSON", "Mobile " + mobile);
                String address = jContact.getString("address");
                Log.d("JSON", "Address " + address);
                arraycontactsList.add(id);

                //Reeconstruir
                JSONObject invJContact=new JSONObject();
                invJContact.accumulate("id",id);
                invJContact.accumulate("name", name);
            }



        }catch (Exception e) {
            Log.d("JSON","Error "+e.getMessage());
        }

    }

    public String readApiWeather() {
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL("api.openweathermap.org/data/2.5/weather?lat=35&lon=139");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("JSON",in.toString());
            }
        catch (Exception e) {
            }


        return builder.toString();
    }


}
