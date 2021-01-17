package com.example.getabed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class Menu extends AppCompatActivity {
    private Button bsignOut;
    private ProgressBar psignOut;
    private FirebaseAuth mAuth;
    private GoogleSignInOptions gso;
    private TextView saludo;
    private ImageView imagenPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth= FirebaseAuth.getInstance();
        bsignOut = (Button) findViewById(R.id.button_cerrarsesion);
        psignOut = (ProgressBar) findViewById(R.id.progressBar2);
       saludo= (TextView) findViewById(R.id.saludo);
       imagenPerfil= (ImageView) findViewById(R.id.fotoPerfil);
       Intent intent= getIntent();
       saludo.setText(intent.getStringExtra("nombre"));
       String foto= intent.getStringExtra("imagen");
        Log.d("Imagen", "onCreate: "+foto);
        Picasso.with(getApplicationContext()).load(foto).into(imagenPerfil);


        bsignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                psignOut.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent= new Intent(Menu.this,MainActivity.class);
                intent.putExtra("msg", "cerrarSesion");
                startActivity(intent);
                //EpsignOut.setVisibility(View.GONE);
                startActivity(intent);
                Toast.makeText(Menu.this, "Cierre de sesión", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void verCamasDisponibles(View view){
        Intent intent= new Intent(getBaseContext(),EstadoCamas.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.button_cerrarsesion) {
            cerrarSesion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cerrarSesion(){
        Intent intent= new Intent(getBaseContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}