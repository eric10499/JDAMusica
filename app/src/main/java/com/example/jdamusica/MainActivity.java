package com.example.jdamusica;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListaCancionesFragment.OnFragmentInteractionListener, CancionSeleccionadaFragment.OnFragmentInteractionListener {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Toolbar toolbar;
    DataSnapshot dataSnapshot;
    List<String> canciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);




        FragmentManager fragmentManager = getSupportFragmentManager();
        ListaCancionesFragment listaCancionesFragment = new ListaCancionesFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, listaCancionesFragment, "ListaCanciones").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    @Nullable
    public void recogerDatosFirebase() {
            databaseReference = database.getInstance().getReference().child("musica").child("canciones");
            databaseReference.child("The Avengers").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   String autor = dataSnapshot.child("autor").getValue(String.class);


                   String titulo = dataSnapshot.child("titulo").getValue(String.class);
                   String urlCancion = dataSnapshot.child("urlCancion").getValue(String.class);
                   String urlPortada = dataSnapshot.child("urlPortada").getValue(String.class);

                   Musica datosCancion = new Musica(titulo, autor, urlPortada, urlCancion);
                   Log.i("AUTOR", dataSnapshot.child("autor").getValue(String.class));


                   ListaCancionesFragment listaCancionesFragment = (ListaCancionesFragment) getSupportFragmentManager().findFragmentByTag("ListaCanciones");

                   listaCancionesFragment.addMusicToList(datosCancion);

               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });

        databaseReference.child("Star Wars").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String autor = dataSnapshot.child("autor").getValue(String.class);


                String titulo = dataSnapshot.child("titulo").getValue(String.class);
                String urlCancion = dataSnapshot.child("urlCancion").getValue(String.class);
                String urlPortada = dataSnapshot.child("urlPortada").getValue(String.class);

                Musica datosCancion = new Musica(titulo, autor, urlPortada, urlCancion);
                Log.i("AUTOR", datosCancion.getAutor());


                ListaCancionesFragment listaCancionesFragment = (ListaCancionesFragment) getSupportFragmentManager().findFragmentByTag("ListaCanciones");

                listaCancionesFragment.addMusicToList(datosCancion);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Jurassic Park").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String autor = dataSnapshot.child("autor").getValue(String.class);


                String titulo = dataSnapshot.child("titulo").getValue(String.class);
                String urlCancion = dataSnapshot.child("urlCancion").getValue(String.class);
                String urlPortada = dataSnapshot.child("urlPortada").getValue(String.class);

                Musica datosCancion = new Musica(titulo, autor, urlPortada, urlCancion);
                Log.i("AUTOR", dataSnapshot.child("autor").getValue(String.class));


                ListaCancionesFragment listaCancionesFragment = (ListaCancionesFragment) getSupportFragmentManager().findFragmentByTag("ListaCanciones");

                listaCancionesFragment.addMusicToList(datosCancion);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }



}
