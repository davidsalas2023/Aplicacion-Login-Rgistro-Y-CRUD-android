package com.example.adeudados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nombre , contacto , deuda , fecha ;
    Button enviar , ver , a;
    private FirebaseFirestore base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a=findViewById(R.id.volver);
        enviar = findViewById(R.id.enviar);
        ver = findViewById(R.id.ver);
        nombre=(EditText) findViewById(R.id.deudor);
        contacto=(EditText) findViewById(R.id.contacto);
        deuda=(EditText) findViewById(R.id.deuda);
        fecha=(EditText) findViewById(R.id.fecha);
        base = FirebaseFirestore.getInstance();
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(MainActivity.this , Menu.class);
                startActivity(c);
            }
        });


        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , MainActivity2.class);
                startActivity(i);
            }
        });
        String id = getIntent().getStringExtra("id_deuda");

        if (id == null || id == ""){
            enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombred = nombre.getText().toString().trim();
                    String contact = contacto.getText().toString().trim();
                    String deud = deuda.getText().toString().trim();
                    String date = fecha.getText().toString().trim();

                    if (nombred.isEmpty() || contact.isEmpty() || deud.isEmpty() || date.isEmpty()){

                        Toast.makeText(getApplicationContext(), "Ingrese los datos" , Toast.LENGTH_SHORT).show();

                    }
                    else{
                        postDeudor(nombred , contact , deud , date );
                    }
                }
            });




        }else {
            enviar.setText("Actualizar");
            getdeudor(id);
            enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombred = nombre.getText().toString().trim();
                    String contact = contacto.getText().toString().trim();
                    String deud = deuda.getText().toString().trim();
                    String date = fecha.getText().toString().trim();
                    if (nombred.isEmpty() || contact.isEmpty() || deud.isEmpty() || date.isEmpty()){

                        Toast.makeText(getApplicationContext(), "Ingrese los datos" , Toast.LENGTH_SHORT).show();

                    }
                    else{
                        cambiarDeudor(nombred , contact , deud , date , id);
                    }

                }
            });
        }


    }

    private void cambiarDeudor(String nombred, String contact, String deud, String date, String id) {
        Map<String , Object> map = new HashMap<>();
        map.put("Nombre_Deudor",nombred);
        map.put("Contacto",contact);
        map.put("Deuda",deud);
        map.put("Fecha_Limite",date);
        base.collection("Deudores").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void postDeudor(String nombred, String contact, String deud, String date) {

        Map<String , Object> map = new HashMap<>();
        map.put("Nombre_Deudor",nombred);
        map.put("Contacto",contact);
        map.put("Deuda",deud);
        map.put("Fecha_Limite",date);

        base.collection("Deudores").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Exito" , Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void getdeudor(String id){
        base.collection("Deudores").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String Nombre = documentSnapshot.getString("Nombre_Deudor");

                String Contacto = documentSnapshot.getString("Contacto");

                String Deuda = documentSnapshot.getString("Deuda");

                String Fecha = documentSnapshot.getString("Fecha_Limite");

                nombre.setText(Nombre);
                contacto.setText(Contacto);
                deuda.setText(Deuda);
                fecha.setText(Fecha);




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}