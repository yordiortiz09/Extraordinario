package com.example.extraordinario;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class VistaResultados extends AppCompatActivity {
    RecyclerView recyclerview;
    List<Ganadores> ganadores;
    AdaptadorGanadores adaptador;
    Singleton rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);
        recyclerview = findViewById(R.id.recycler);
        rQueue = Singleton.getInstance(this);
       getListResults();
    }
        public void getListResults()
        {
            String url = "https://ramiro.uttics.com/api/ganadores";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    Ganadores r = gson.fromJson(response.toString(), Ganadores.class);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adaptador = new AdaptadorGanadores(r.getData());
                    recyclerview.setAdapter(adaptador);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            rQueue.addToRequestQueue(request);
        }
}