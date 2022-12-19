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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {

    Button reg , log;
    EditText correo , contra;
    FirebaseAuth a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg=findViewById(R.id.Regis);
        log=findViewById(R.id.login);
        correo=findViewById(R.id.Correo);
        contra=findViewById(R.id.contra);
        a=FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = correo.getText().toString().trim();
                String co = contra.getText().toString().trim();
                if (c.isEmpty() || co.isEmpty()){
                    Toast.makeText(login.this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();

                }else{
                    log(c , co);

                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(login.this , registro.class);
                startActivity(i);
            }
        });
    }

    private void log(String c, String co) {
        a.signInWithEmailAndPassword(c , co).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                finish();
                startActivity(new Intent(login.this,Menu.class));
                Toast.makeText(login.this, "Welcome", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this, "Error de inicio de sesion", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= a.getCurrentUser();
        if (user != null){
            startActivity(new Intent(login.this, Menu.class));
            finish();
        }
    }
}