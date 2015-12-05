package com.android.agendacontactos;



import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


/**
 * Created by Bernard on 05/11/2015.
 */
public class VolleyRequest{
    String response;
    public VolleyRequest(Context context) {
        try {
            response = jSonreadAPIWeather(context);
            //JSONObject jObject=new JSONObject(jSonreadAPIWeather(context));
            //Log.d("VOLLEY", jObject.toString());
        }catch (Exception e) {
            Log.d("VOLLEYERROR", "Error " + e.getMessage());
        }
    }

    private String jSonreadAPIWeather(Context context) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,"http://api.openweathermap.org/data/2.5/weather?q=Medellin&APPID=482579f45f1dc55ae8bd36deafedc58b",
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("VOLLEY", response.toString());
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            Log.d("VOLLEY", "Coord. :");
                            String lon = coord.getString("lon");
                            Log.d("VOLLEY", "Lon. " + lon);
                            String lat = coord.getString("lat");
                            Log.d("VOLLEY", "lat. " + lat);
                            String name = response.getString("name");
                            Log.d("VOLLEY", "Name " + name);
                        }catch (Exception e) {
                            Log.d("VOLLEY","Error "+e.getMessage());
                        }
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
        //Log.d("VOLLEY", "R " + Request.class.toString());
        return requestQueue.   toString();
    }

}


