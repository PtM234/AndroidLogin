package com.android.tapia.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText edtNombre, edtUsuario, edtContra, edtEdad;
    Button btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = findViewById(R.id.registro_nombre);
        edtUsuario = findViewById(R.id.registro_user);
        edtEdad = findViewById(R.id.registro_edad);
        edtContra = findViewById(R.id.registro_pass);
        btn_registrar = findViewById(R.id.registro_boton);

        btn_registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String name = edtNombre.getText().toString();
        final String user = edtUsuario.getText().toString();
        final String pass = edtContra.getText().toString();
        final int age = Integer.parseInt(edtEdad.getText().toString());
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success){
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        Registro.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error en el registro").setNegativeButton("Retry", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, user, pass, age, respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);

    }
}
