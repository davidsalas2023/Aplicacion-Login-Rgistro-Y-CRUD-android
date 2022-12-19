package com.example.adeudados.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adeudados.MainActivity;
import com.example.adeudados.R;
import com.example.adeudados.modelo.Adeudos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class Adapter extends FirestoreRecyclerAdapter<Adeudos , Adapter . Viewholder> {
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;


    /**
     *
     *
     *
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirestoreRecyclerOptions<Adeudos> options , Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull Adeudos Adeudos ) {
        DocumentSnapshot d = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = d.getId();
        holder.Nombre.setText(Adeudos.getNombre_Deudor());
        holder.Deuda.setText(Adeudos.getDeuda());
        holder.Contacto.setText(Adeudos.getContacto());
        holder.Fecha.setText(Adeudos.getFecha_Limite());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity , MainActivity.class);
                i.putExtra("id_deuda", id);
                activity.startActivity(i);
            }
        });
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar(id);
                

            }
        });
    }

    private void borrar(String id) {
        mFirestore.collection("Deudores").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Exito", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_deuda , parent , false);
        return new Viewholder(v);
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView Nombre , Deuda , Contacto , Fecha;
        ImageView borrar, editar;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.deudor);
            Deuda = itemView.findViewById(R.id.deuda);
            Contacto = itemView.findViewById(R.id.contacto);
            Fecha = itemView.findViewById(R.id.fecha);
            editar=itemView.findViewById(R.id.editar);
            borrar = itemView.findViewById(R.id.eliminar);
        }
    }
}
