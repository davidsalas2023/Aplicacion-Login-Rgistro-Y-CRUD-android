package com.example.adeudados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.ActionBar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adeudados.adapter.Adapter;
import com.example.adeudados.modelo.Adeudos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView mRecycler;
    Adapter mAdapter;
    FirebaseFirestore mFirestore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mFirestore= FirebaseFirestore.getInstance();

        mRecycler= findViewById(R.id.Recicla);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query =mFirestore.collection("Deudores");
        FirestoreRecyclerOptions<Adeudos> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Adeudos>().setQuery(query , Adeudos.class).build();
        mAdapter = new Adapter(firestoreRecyclerOptions , this);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);



    }

    public MainActivity2() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}