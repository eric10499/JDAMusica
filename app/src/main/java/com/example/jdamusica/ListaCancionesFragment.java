package com.example.jdamusica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCancionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCancionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCancionesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Musica> musicListRecycler = new ArrayList<Musica>();

    ClaseAdapter claseAdapter = new ClaseAdapter(musicListRecycler);

    ImageView imagen;


    private OnFragmentInteractionListener mListener;

    public ListaCancionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCancionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCancionesFragment newInstance(String param1, String param2) {
        ListaCancionesFragment fragment = new ListaCancionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lista_canciones, container, false);

       mListener.recogerDatosFirebase();

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_musica);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        recyclerView.setAdapter(claseAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        claseAdapter.notifyDataSetChanged();

        claseAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recyclerView.getChildPosition(v));
                Bundle bundle = new Bundle();
                bundle.putString("titulo", musicListRecycler.get(recyclerView.getChildPosition(v)).getTitulo());
                Log.i("titulo", musicListRecycler.get(recyclerView.getChildPosition(v)).getTitulo());
                bundle.putString("autor", musicListRecycler.get(recyclerView.getChildPosition(v)).getAutor());
                bundle.putString("utlPortada", musicListRecycler.get(recyclerView.getChildPosition(v)).getUrlPortada());
                bundle.putString("urlCancion", musicListRecycler.get(recyclerView.getChildPosition(v)).getUrlCancion());
                FragmentManager fragmentManager = getFragmentManager();
                CancionSeleccionadaFragment cancionSeleccionadaFragment = new CancionSeleccionadaFragment();
                cancionSeleccionadaFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, cancionSeleccionadaFragment, "cancionSeleccionada").commit();
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void recogerDatosFirebase();
    }

    public void addMusicToList(Musica musica){
        musicListRecycler.add(musica);

        Log.i("lista", String.valueOf(musicListRecycler.size()));


        claseAdapter.notifyDataSetChanged();

    }


}
