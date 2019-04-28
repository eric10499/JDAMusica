package com.example.jdamusica;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CancionSeleccionadaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CancionSeleccionadaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CancionSeleccionadaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int contador;

    private OnFragmentInteractionListener mListener;

    private TextView titulo, autor;
    private ImageView portada;
    private ImageButton play;


    public CancionSeleccionadaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CancionSeleccionadaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CancionSeleccionadaFragment newInstance(String param1, String param2) {
        CancionSeleccionadaFragment fragment = new CancionSeleccionadaFragment();
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
        View view =  inflater.inflate(R.layout.fragment_cancion_seleccionada, container, false);
        final Bundle bundle = getArguments();
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Log.i("Bundle", String.valueOf(getArguments()));
        titulo = view.findViewById(R.id.titulo);
        titulo.setText(bundle.getString("titulo"));
        autor = view.findViewById(R.id.autor);
        autor.setText(bundle.getString("autor"));
        portada = view.findViewById(R.id.portada);
        MiHilo miHilo = new MiHilo(portada);
        miHilo.execute(bundle.getString("utlPortada"));
        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.animate().alpha(1).setDuration(2000);
                portada.animate().scaleXBy((float) -0.6).scaleYBy((float) -0.8).setDuration(2000);
                mediaPlayer.pause();

            }
        });

        play = view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador < 1) {
                    String url = bundle.getString("urlCancion"); // your URL here

                    try {
                        mediaPlayer.setDataSource(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    portada.animate().scaleXBy((float) 0.6).scaleYBy((float) 0.8).setDuration(2000);
                    play.animate().alpha(0).setDuration(1000);
                    mediaPlayer.start();
                    contador += 1;
                } else {
                    portada.animate().scaleXBy((float) 0.6).scaleYBy((float) 0.8).setDuration(2000);
                    play.animate().alpha(0).setDuration(1000);
                    mediaPlayer.start();
                }

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
    }
}
