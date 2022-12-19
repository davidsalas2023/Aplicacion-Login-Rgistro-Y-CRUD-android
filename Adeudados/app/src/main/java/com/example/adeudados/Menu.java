package com.example.adeudados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {
    ImageButton cerrar , aa , l;
    FirebaseAuth a ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        a=FirebaseAuth.getInstance();
        cerrar=findViewById(R.id.Cerrar);
        l=findViewById(R.id.Ingresar);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this , MainActivity.class);
                startActivity(i);
            }
        });
        aa=findViewById(R.id.data);
        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,MainActivity2.class);
                startActivity(i);
            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.signOut();
                finish();
                startActivity(new Intent(Menu.this,login.class));
            }
        });
    }
}