package com.example.adeudados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
    Button back , registro;
    EditText correo , contra;
    FirebaseFirestore mFirestor;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        registro=findViewById(R.id.reg);
        correo= findViewById(R.id.Correo);
        contra=findViewById(R.id.contra);
        back=findViewById(R.id.back);
        mFirestor= FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = correo.getText().toString().trim();
                String co = contra.getText().toString().trim();
                if (c.isEmpty() || co.isEmpty()){
                    Toast.makeText(registro.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();

                }else{
                    reg(c ,co);

                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registro.this , login.class);
                startActivity(i);
            }
        });
    }

    private void reg(String c, String co) {
        mAuth.createUserWithEmailAndPassword(c,co).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object>map = new HashMap<>();
                map.put("reg",id);
                map.put("Correo", c);
                map.put("pass",co);

                mFirestor.collection("User").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(registro.this, "Registro completado", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registro.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registro.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });

    }
}