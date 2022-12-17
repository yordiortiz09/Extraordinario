package com.example.extraordinario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.NumericShaper;

public class MainActivity extends AppCompatActivity {
    Button botonpedirnumero, botonenviardatos, botonresultados;
    ImageView corona, diablito, corazon, sandia;
    TextView textnum;
    int numero;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    Singleton.getInstance(MainActivity.this).getRequestQueue();
        requestQueue = Singleton.getInstance(MainActivity.this).getRequestQueue();

        corona = findViewById(R.id.corona);
        diablito = findViewById(R.id.diablito);
        corazon = findViewById(R.id.corazon);
        sandia = findViewById(R.id.sandia);
        textnum = (TextView) findViewById(R.id.numero);

        botonresultados = findViewById(R.id.r);
        botonpedirnumero = findViewById(R.id.solicitar);
        botonenviardatos = findViewById(R.id.enviar);

        botonresultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VistaResultados.class);
                startActivity(intent);
            }
        });

        botonpedirnumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "solicitando", Toast.LENGTH_SHORT).show();
                String url = "https://ramiro.uttics.com/api/numero";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            numero = Integer.parseInt(response.getString("Numero"));
                            textnum.setText(response.getString("Numero"));
                            if (numero == 47) {
                                corona.setImageDrawable(getDrawable(R.drawable.frijol));
                            } else if (numero == 27) {
                                corazon.setImageDrawable(getDrawable(R.drawable.frijol));
                            } else if (numero == 28) {
                                sandia.setImageDrawable(getDrawable(R.drawable.frijol));
                            } else if (numero == 2) {
                                diablito.setImageDrawable(getDrawable(R.drawable.frijol));
                            } else {
                                Toast.makeText(MainActivity.this, "" + numero, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        //ENVIAR DATOS
        botonenviardatos = (Button) findViewById(R.id.enviar);
        botonenviardatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ramiro.uttics.com/api/enviarnumero";
                JSONObject jsonBody = new JSONObject();
                JSONObject array = new JSONObject();
                try {
                    jsonBody.put("nombre", "Yordi");
                    jsonBody.put("numero", " " + textnum.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    array.put("persona", jsonBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.POST, url, array, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "Numero enviado ", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "HUBO ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(peticion);

            }
        });


    }
}

