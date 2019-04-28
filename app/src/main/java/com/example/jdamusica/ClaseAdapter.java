package com.example.jdamusica;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClaseAdapter extends RecyclerView.Adapter<ClaseAdapter.ClaseViewHolder> implements View.OnClickListener {

    List<Musica> musica;
    Context context;

    private View.OnClickListener listener;



    public ClaseAdapter(List<Musica> musica) {

        this.musica = musica;

    }



    @NonNull

    @Override

    public ClaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.lista_canciones_recycler, viewGroup, false);

        view.setOnClickListener(this);

        return new ClaseViewHolder(view);



    }



    @Override

    public void onBindViewHolder(@NonNull ClaseViewHolder claseViewHolder, int i) {



        Musica m = musica.get(i);

        claseViewHolder.titulo.setText(m.getTitulo());

        claseViewHolder.autor.setText(m.getAutor());

        MiHilo miHilo = new MiHilo(claseViewHolder.portada, context);
        miHilo.execute(m.getUrlPortada());

    }



    @Override
    public int getItemCount() {

        return musica.size();

    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    class ClaseViewHolder extends RecyclerView.ViewHolder{



        TextView titulo, autor;
        ImageView portada;



        public ClaseViewHolder(@NonNull View itemView) {

            super(itemView);



            titulo = itemView.findViewById(R.id.titulo);

            autor = itemView.findViewById(R.id.autor);

            portada = itemView.findViewById(R.id.portada);


        }

    }
}
