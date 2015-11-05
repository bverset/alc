package com.android.agendacontactos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by Bernard on 05/11/2015.
 */
public class VolleyRequest {

    public VolleyRequest(Context context) {
        try {

            JSONObject jObject=new JSONObject(jSonreadAPIWeather(context));
            Log.d("VOLLEY", jObject.toString());
        }catch (Exception e) {
            Log.d("VOLLEYERROR","Error "+e.getMessage());
        }
    }

    private String jSonreadAPIWeather(Context context) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q=Montreal&APPID=482579f45f1dc55ae8bd36deafedc58b",
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("VOLLEY", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEYERROR", error.toString());
                    }
                }
        );
        requestQueue.add(request);
        requestQueue.start();
        return requestQueue.toString();
    }

}


